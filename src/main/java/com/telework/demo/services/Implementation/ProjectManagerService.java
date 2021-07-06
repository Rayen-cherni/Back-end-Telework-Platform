package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.services.IProjectManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectManagerService implements IProjectManagerService {


    private final IProjectManagerRepository repository;

    public ProjectManagerService(IProjectManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectManagerDto save(ProjectManagerDto projectManagerDto) {
        //TODO test d'existance
        // if(pole exist) we can't add him again
        return ProjectManagerDto.fromEntity(repository.save(ProjectManagerDto.toEntity(projectManagerDto)));
    }

    @Override
    public ProjectManagerDto findById(Integer id) {
        return repository.findById(id).map(ProjectManagerDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessages.PROJECT_MANAGER_NOT_FOUND, id)
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
            throw new EntityNotFoundException(ErrorMessages.PROJECT_MANAGER_NOT_FOUND, id);
        }
        repository.deleteById(id);
    }
}
