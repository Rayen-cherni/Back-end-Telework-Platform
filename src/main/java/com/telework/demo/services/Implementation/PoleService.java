package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleRepository;
import com.telework.demo.services.IPoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoleService implements IPoleService {

    private final IPoleRepository repository;

    public PoleService(IPoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PoleDto save(PoleDto poleDto) {
        boolean isExist = repository.existsByName(poleDto.getName());
        if (isExist){
            throw new InvalidOperationException(ErrorMessages.POLE_ALREADY_EXISTS);
        }else {
            return PoleDto.fromEntity(repository.save(PoleDto.toEntity(poleDto)));
        }

    }

    @Override
    public PoleDto findById(Integer id) {
        return repository.findById(id).map(PoleDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessages.POLE_NOT_FOUND, id)
        );
    }

    @Override
    public List<PoleDto> findAll() {
        return repository.findAll().stream()
                .map(PoleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        PoleDto poleDto = findById(id);
        if (poleDto == null) {
            throw new EntityNotFoundException(ErrorMessages.POLE_NOT_FOUND, id);
        }
        repository.deleteById(id);
    }
}
