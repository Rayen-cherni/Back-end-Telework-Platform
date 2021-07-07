package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.model.CreatePoleForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IPoleRepository;
import com.telework.demo.services.IPoleService;
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

    public PoleService(IPoleRepository repository, IPoleManagerRepository poleManagerRepository) {
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

        PoleManagerDto poleManagerDto = PoleManagerDto.fromEntity(optionalPoleManager.get());

        PoleDto poleDto = PoleDto.builder()
                .name(poleForm.getName())
                .capacity(poleForm.getCapacity())
                .poleManager(poleManagerDto)
                .reserved(poleForm.getReserved())
                .description(poleForm.getDescription())
                .build();


        boolean isExist = repository.existsByName(poleDto.getName());
        if (isExist) {
            throw new InvalidOperationException(POLE_ALREADY_EXISTS);
        } else {
            return PoleDto.fromEntity(repository.save(PoleDto.toEntity(poleDto)));
        }

    }

    @Override
    public PoleDto findById(Integer id) {
        return repository.findById(id).map(PoleDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(POLE_NOT_FOUND + id)
        );
    }

    @Override
    public List<PoleDto> findAll() {
        return repository.findAll().stream()
                .map(PoleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        PoleDto poleDto = findById(id);
        if (poleDto == null) {
            throw new EntityNotFoundException(POLE_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}
