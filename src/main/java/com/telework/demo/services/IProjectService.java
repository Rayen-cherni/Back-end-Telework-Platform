package com.telework.demo.services;

import com.telework.demo.domain.dto.ProjectDto;

import java.util.List;

public interface IProjectService {

    ProjectDto save(ProjectDto projectDto);

    ProjectDto findById(Integer id);

    List<ProjectDto> findAll();

    void deleteById(Integer id);
}
