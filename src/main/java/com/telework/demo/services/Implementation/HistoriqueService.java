package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.domain.entity.Historique;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.repository.IHistoriqueRepository;
import com.telework.demo.services.IHistoriqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.HISTORIQUE_NOT_FOUND;

@Service
public class HistoriqueService implements IHistoriqueService {

    private final IHistoriqueRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public HistoriqueService(IHistoriqueRepository repository) {
        this.repository = repository;

    }

    @Override
    public HistoriqueDto save(HistoriqueDto dto) {

        return modelMapper.map(repository.save(modelMapper.map(dto, Historique.class)), HistoriqueDto.class);
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
        return repository.findAll().stream()
                .map(historique -> modelMapper.
                        map(historique, HistoriqueDto.class))
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
}
