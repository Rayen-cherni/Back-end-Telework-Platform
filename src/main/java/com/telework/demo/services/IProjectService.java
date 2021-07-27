package com.telework.demo.services;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.model.CreateProjectForm;

import java.util.List;

public interface IProjectService {

    ProjectDto save(CreateProjectForm projectDto);

    ProjectDto findById(Integer id);

    List<ProjectDto> findAll();

    void delete(Integer id);

    ProjectDto assignementOfDeveloper(Integer idProject, Integer idDeveloper);

    List<ProjectDto> findByProjectManager(Integer idProjectManager);
}
