package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectRepository;
import com.telework.demo.services.IProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.PROJECT_NOT_FOUND;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository repository;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        boolean isExist = repository.existsByName(projectDto.getName());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.ROLE_ALREADY_EXISTS);
        } else {
            return ProjectDto.fromEntity(repository.save(ProjectDto.toEntity(projectDto)));

        }
    }

    @Override
    public ProjectDto findById(Integer id) {
        return repository.findById(id).map(ProjectDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(PROJECT_NOT_FOUND + id)
        );
    }

    @Override
    public List<ProjectDto> findAll() {
        return repository.findAll().stream()
                .map(ProjectDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        ProjectDto projectDto = findById(id);
        if (projectDto == null) {
            throw new EntityNotFoundException(PROJECT_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}

