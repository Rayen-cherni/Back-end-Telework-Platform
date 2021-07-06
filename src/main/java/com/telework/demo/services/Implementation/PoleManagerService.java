package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.services.IPoleManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoleManagerService implements IPoleManagerService {

    private final IPoleManagerRepository repository;

    public PoleManagerService(IPoleManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public PoleManagerDto save(PoleManagerDto dto) {
        boolean isExist = repository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.POLE_MANAGER_ALREADY_EXISTS);
        }
        return PoleManagerDto.fromEntity(repository.save(PoleManagerDto.toEntity(dto)));
    }

    @Override
    public PoleManagerDto findById(Integer id) {
        return repository.findById(id).map(PoleManagerDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessages.POLE_MANAGER_NOT_FOUND, id)
        );
    }

    @Override
    public List<PoleManagerDto> findAll() {
        return repository.findAll().stream()
                .map(PoleManagerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        PoleManagerDto poleManagerDto = findById(id);
        if (poleManagerDto == null) {
            throw new EntityNotFoundException(ErrorMessages.POLE_MANAGER_NOT_FOUND, id);
        }
        repository.deleteById(id);
    }
}
