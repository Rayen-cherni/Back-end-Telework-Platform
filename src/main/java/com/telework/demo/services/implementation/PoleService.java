package com.telework.demo.services.implementation;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.Pole;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.model.CreatePoleForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IPoleRepository;
import com.telework.demo.services.IPoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class PoleService implements IPoleService {

    private final IPoleRepository repository;
    private final IPoleManagerRepository poleManagerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PoleService(IPoleRepository repository,
                       IPoleManagerRepository poleManagerRepository,
                       ModelMapper modelMapper) {
        this.repository = repository;
        this.poleManagerRepository = poleManagerRepository;

    }

    @Override
    @Transactional
    public PoleDto save(CreatePoleForm poleForm) {

        Optional<PoleManager> optionalPoleManager = poleManagerRepository.findById(poleForm.getPoleManagerId());

        if (optionalPoleManager.isEmpty()) {
            throw new InvalidOperationException(POLE_MANAGER_NOT_FOUND);
        }

        if (optionalPoleManager.get().getPole() != null) {
            throw new InvalidOperationException(POLE_MANAGER_ALREADY_IN_USE);
        }
        PoleManagerDto poleManagerDto = modelMapper.map(optionalPoleManager.get(), PoleManagerDto.class);
        PoleDto poleDto = CreatePoleForm.convertToPoleDto(poleForm, poleManagerDto);

        boolean isExist = repository.existsByName(poleDto.getName());
        if (isExist) {
            throw new InvalidOperationException(POLE_ALREADY_EXISTS);
        } else {

            return modelMapper.map(repository.save(modelMapper.map(poleDto, Pole.class)), PoleDto.class);
        }

    }

    @Override
    public PoleDto findById(Integer id) {
        return repository.findById(id).map((pole) -> modelMapper.map(pole, PoleDto.class)).orElseThrow(
                () -> new EntityNotFoundException(POLE_NOT_FOUND + id)
        );
    }

    @Override
    public List<PoleDto> findAll() {

        return repository.findAll().stream().map((pole) -> modelMapper.map(pole, PoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        PoleDto poleDto = findById(id);
        if (poleDto == null) {
            throw new EntityNotFoundException(POLE_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    public PoleDto updateName(Integer id, String name) {

        PoleDto poleDto = findById(id);

        boolean isExist = repository.existsByName(name);

        if (isExist) {
            throw new InvalidOperationException(POLE_ALREADY_EXISTS);
        }

        if (poleDto == null) {
            throw new InvalidOperationException(POLE_NOT_FOUND);
        }
        poleDto.setName(name);

        return modelMapper
                .map(repository.save(modelMapper
                        .map(poleDto, Pole.class)), PoleDto.class);
    }

    @Override
    public PoleDto updateDescription(Integer id, String description) {

        PoleDto poleDto = findById(id);

        if (poleDto == null) {
            throw new InvalidOperationException(POLE_NOT_FOUND);
        }
        poleDto.setDescription(description);

        return modelMapper
                .map(repository.save(modelMapper
                        .map(poleDto, Pole.class)), PoleDto.class);
    }

    @Override
    public PoleDto updateCapacity(Integer id, Integer capacity) {
        PoleDto poleDto = findById(id);

        if (poleDto == null) {
            throw new InvalidOperationException(POLE_NOT_FOUND);
        }
        poleDto.setCapacity(capacity);

        return modelMapper
                .map(repository.save(modelMapper
                        .map(poleDto, Pole.class)), PoleDto.class);
    }

    @Override
    public PoleDto updateReserved(Integer id, Integer reserved) {
        PoleDto poleDto = findById(id);

        if (poleDto == null) {
            throw new InvalidOperationException(POLE_NOT_FOUND);
        }
        poleDto.setReserved(reserved);

        return modelMapper
                .map(repository.save(modelMapper
                        .map(poleDto, Pole.class)), PoleDto.class);
    }

    @Override
    @Transactional
    public PoleDto updatePoleManager(Integer id, Integer idPoleManager) {

        PoleDto poleDto = findById(id);
        Optional<PoleManager> optionalPoleManager = poleManagerRepository.findById(id);
        if (optionalPoleManager.isEmpty()) {
            throw new InvalidOperationException(POLE_NOT_FOUND);
        }
        if (optionalPoleManager.get().getPole() != null) {
            throw new InvalidOperationException(POLE_MANAGER_ALREADY_IN_USE);
        }
        poleDto.setPoleManager(modelMapper.map(optionalPoleManager.get(), PoleManagerDto.class));

        return modelMapper.map(repository
                .save(modelMapper
                        .map(poleDto, Pole.class)), PoleDto.class);
    }


}
