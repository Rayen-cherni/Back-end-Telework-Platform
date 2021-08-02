package com.telework.demo.services.implementation;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.Historique;
import com.telework.demo.domain.entity.enumeration.Decision;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.CreateHistoriqueForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import com.telework.demo.repository.IHistoriqueRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IHistoriqueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class HistoriqueService implements IHistoriqueService {

    private final IHistoriqueRepository repository;

    private final ModelMapper modelMapper;
    private final IDeveloperRepository developerRepository;

    public HistoriqueService(IHistoriqueRepository repository,
                             ModelMapper modelMapper,
                             IDeveloperRepository developerRepository,
                             IUserRepository userRepository) {
        this.repository = repository;
        this.developerRepository = developerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public HistoriqueDto save(CreateHistoriqueForm historiqueForm) {

        Optional<Developer> optionalDeveloper = developerRepository.findById(historiqueForm.getDeveloperId());
        if (optionalDeveloper.isEmpty()) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        checkDeveloperStatus(optionalDeveloper.get().getId());
        HistoriqueDto historiqueDto = CreateHistoriqueForm
                .convertToHistoriqueDto(historiqueForm, modelMapper.map(optionalDeveloper.get(), DeveloperDto.class));

        return modelMapper
                .map(repository
                        .save(modelMapper
                                .map(historiqueDto, Historique.class)), HistoriqueDto.class);
    }

    @Override
    public HistoriqueDto findById(Integer id) {
        return repository.findById(id).map(historique -> modelMapper.map(historique, HistoriqueDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(HISTORIQUE_NOT_FOUND + id)
                );
    }

    @Override
    public List<HistoriqueDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(historique -> modelMapper
                        .map(historique, HistoriqueDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        HistoriqueDto historiqueDto = findById(id);
        if (historiqueDto == null) {
            throw new EntityNotFoundException(HISTORIQUE_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public HistoriqueDto updateDecisionProjectManager(Integer idHistorique, Integer idDeveloper, Decision decision) {
        HistoriqueDto historiqueDto = findById(idHistorique);
        checkDeveloperStatus(idDeveloper);
        historiqueDto.setProjectManagerDecision(decision);

        return modelMapper
                .map(repository
                        .save(modelMapper
                                .map(historiqueDto, Historique.class)), HistoriqueDto.class);
    }

    @Override
    @Transactional
    public HistoriqueDto updateDecisionPoleManager(Integer idHistorique, Integer idDeveloper, Decision decision) {
        HistoriqueDto historiqueDto = findById(idHistorique);
        checkDeveloperStatus(idDeveloper);
        historiqueDto.setPoleManagerDecision(decision);

        Optional<Developer> optionalDeveloper = developerRepository.findById(idDeveloper);
        if (optionalDeveloper.isEmpty()) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        if (decision == Decision.PRESENTIAL) {
            Integer presential = optionalDeveloper.get().getPresential();
            presential++;
            optionalDeveloper.get().setPresential(presential);
        } else {
            Integer remote = optionalDeveloper.get().getRemote();
            remote++;
            optionalDeveloper.get().setRemote(remote);
        }
        developerRepository.save(optionalDeveloper.get());
        return modelMapper
                .map(repository
                        .save(modelMapper
                                .map(historiqueDto, Historique.class)), HistoriqueDto.class);
    }

    // Just to check the developer status and inform the project manager in case he is out of service
    private void checkDeveloperStatus(Integer idDeveloper) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(idDeveloper);
        if (optionalDeveloper.isEmpty()) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        WithHoldingType withHoldingTypeDeveloper = optionalDeveloper.get().getWithHoldingType();
        if (withHoldingTypeDeveloper == WithHoldingType.IN_VACATION ||
                withHoldingTypeDeveloper == WithHoldingType.SICK_DAYS ||
                withHoldingTypeDeveloper == WithHoldingType.SUSPENSION) {
            throw new InvalidOperationException(DEVELOPER_OUT_OF_SERVICE);
        }
    }
}
