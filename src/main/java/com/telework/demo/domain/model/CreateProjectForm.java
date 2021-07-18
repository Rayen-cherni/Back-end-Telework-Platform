package com.telework.demo.domain.model;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateProjectForm {

    private String name;

    private List<DeveloperDto> developers;

    private Integer projectManagerId;


    public static ProjectDto convertToProjectDto(CreateProjectForm projectForm, ProjectManagerDto projectManagerDto) {

        ProjectDto projectDto = ProjectDto.builder()
                .name(projectForm.getName())
                .projectManager(projectManagerDto)
                .developers(projectForm.getDevelopers())
                .build();
        ;
        return projectDto;
    }
}
