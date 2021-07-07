package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IDeveloperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.DEVELOPER_NOT_FOUND;

@Service
public class DeveloperService implements IDeveloperService {

    private final IDeveloperRepository repository;
    private final IUserRepository userRepository;

    public DeveloperService(IDeveloperRepository repository, IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public DeveloperDto save(DeveloperDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.USER_ALREADY_EXISTS);
        } else {
            return DeveloperDto.fromEntity(repository.save(DeveloperDto.toEntity(dto)));

        }
    }

    @Override
    public DeveloperDto findById(Integer id) {
        return repository.findById(id).map(DeveloperDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(DEVELOPER_NOT_FOUND + id)
        );
    }

    @Override
    public List<DeveloperDto> findAll() {
        return repository.findAll().stream()
                .map(DeveloperDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        DeveloperDto developerDto = findById(id);
        if (developerDto == null) {
            throw new EntityNotFoundException(DEVELOPER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}
