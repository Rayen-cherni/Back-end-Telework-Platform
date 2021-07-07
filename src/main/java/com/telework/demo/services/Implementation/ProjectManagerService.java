package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IProjectManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class ProjectManagerService implements IProjectManagerService {


    private final IProjectManagerRepository repository;
    private final IUserRepository userRepository;

    public ProjectManagerService(IProjectManagerRepository repository,IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectManagerDto save(ProjectManagerDto projectManagerDto) {
        boolean isExist = userRepository.existsByEmail(projectManagerDto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        } else {
            return ProjectManagerDto.fromEntity(repository.save(ProjectManagerDto.toEntity(projectManagerDto)));

        }
    }

    @Override
    public ProjectManagerDto findById(Integer id) {
        return repository.findById(id).map(ProjectManagerDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id)
        );
    }

    @Override
    public List<ProjectManagerDto> findAll() {
        return repository.findAll().stream()
                .map(ProjectManagerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        ProjectManagerDto projectManagerDto = findById(id);
        if (projectManagerDto == null) {
            throw new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}
