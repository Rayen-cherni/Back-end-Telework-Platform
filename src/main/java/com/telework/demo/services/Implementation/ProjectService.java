package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.Project;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.model.CreateProjectForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IProjectRepository;
import com.telework.demo.services.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.PROJECT_MANAGER_NOT_FOUND;
import static com.telework.demo.exception.ErrorMessages.PROJECT_NOT_FOUND;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository repository;
    private final IProjectManagerRepository projectManagerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProjectService(IProjectRepository repository, IProjectManagerRepository projectManagerRepository) {
        this.repository = repository;
        this.projectManagerRepository = projectManagerRepository;
    }

    @Override
    @Transactional
    public ProjectDto save(CreateProjectForm projectForm) {

        Optional<ProjectManager> optionalProjectManager = projectManagerRepository
                .findById(projectForm.getProjectManagerId());
        if (optionalProjectManager.isEmpty()) {
            throw new InvalidOperationException(PROJECT_MANAGER_NOT_FOUND);
        }

        boolean isExist = repository.existsByName(projectForm.getName());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.PROJECT_ALREADY_EXISTS);
        }
        ProjectManagerDto projectManagerDto = modelMapper.map(optionalProjectManager.get(), ProjectManagerDto.class);
        ProjectDto projectDto = CreateProjectForm.convertToProjectDto(projectForm, projectManagerDto);

        return modelMapper.map(repository.save(modelMapper.map(projectDto, Project.class)), ProjectDto.class);

    }

    @Override
    public ProjectDto findById(Integer id) {
        return repository.findById(id).map(project -> modelMapper
                .map(project, ProjectDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(PROJECT_NOT_FOUND + id)
                );
    }

    @Override
    public List<ProjectDto> findAll() {
        return repository.findAll().stream()
                .map((project -> modelMapper
                        .map(project, ProjectDto.class)))
                .collect(Collectors.toList());
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

