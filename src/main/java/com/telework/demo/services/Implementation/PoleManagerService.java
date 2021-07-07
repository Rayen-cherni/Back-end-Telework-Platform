package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IPoleManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class PoleManagerService implements IPoleManagerService {

    private final IPoleManagerRepository repository;
    private final IUserRepository userRepository;

    public PoleManagerService(IPoleManagerRepository repository, IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public PoleManagerDto save(PoleManagerDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        }
        return PoleManagerDto.fromEntity(repository.save(PoleManagerDto.toEntity(dto)));
    }

    @Override
    public PoleManagerDto findById(Integer id) {
        return repository.findById(id).map(PoleManagerDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id)
        );
    }

    @Override
    public List<PoleManagerDto> findAll() {
        return repository.findAll().stream()
                .map(PoleManagerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        PoleManagerDto poleManagerDto = findById(id);
        if (poleManagerDto == null) {
            throw new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}
