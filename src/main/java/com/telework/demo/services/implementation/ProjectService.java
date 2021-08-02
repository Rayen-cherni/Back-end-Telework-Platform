package com.telework.demo.services.implementation;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.Project;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.model.CreateProjectForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
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

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository repository;
    @Autowired
    private IProjectManagerRepository projectManagerRepository;
    @Autowired
    private IDeveloperRepository developerRepository;
    @Autowired
    private ModelMapper modelMapper;


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
                        () -> new EntityNotFoundException(PROJECT_NOT_FOUND)
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
            throw new EntityNotFoundException(PROJECT_NOT_FOUND);
        }
        repository.deleteById(id);
    }


    @Transactional
    @Override
    public ProjectDto assignementOfDeveloper(Integer idProject, Integer idDeveloper) {
        ProjectDto projectDto = findById(idProject);
        Optional<Developer> optionalDeveloper = developerRepository.findById(idDeveloper);

        if (optionalDeveloper.isEmpty()) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        DeveloperDto developerDto = modelMapper.map(optionalDeveloper.get(), DeveloperDto.class);
        List<DeveloperDto> developerDtosList = projectDto.getDevelopers();

        //README
        // if (developerDtosList.contains(developerDto)) doesn't work!!
        // Reason : because u need to override the hashCode method in DeveloperDto class!
        // Solution : you can override them ( hashcode method && equals method using @EqualsAndHashCode
        for (DeveloperDto localDeveloper : developerDtosList
        ) {
            if (developerDto.getId() == localDeveloper.getId()) {
                throw new InvalidOperationException(DEVELOPER_ALREADY_EXISTS);
            }
        }

        developerDtosList.add(developerDto);
        projectDto.setDevelopers(developerDtosList);
        Project pg = repository.save(modelMapper.map(projectDto, Project.class));
        return projectDto;
        //return modelMapper
             //   .map(pg, ProjectDto.class);
    }

    @Transactional
    @Override
    public List<ProjectDto> findByProjectManager(Integer idProjectManager) {
        Optional<ProjectManager> optionalProjectManager = projectManagerRepository.findById(idProjectManager);
        if (optionalProjectManager.isEmpty()) {
            throw new InvalidOperationException(PROJECT_MANAGER_NOT_FOUND);
        }
        ProjectManager projectManager = optionalProjectManager.get();
        return repository.findByProjectManager(projectManager)
                .stream()
                .map((project -> modelMapper.map(project, ProjectDto.class)))
                .collect(Collectors.toList());
    }
}

