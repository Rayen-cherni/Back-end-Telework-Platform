package com.telework.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectDto {
    private Integer id;


    private String name;

    private List<DeveloperDto> developers;

    private ProjectManagerDto projectManager;
    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper

    /*
    public static ProjectDto fromEntity(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .projectManager(ProjectManagerDto.fromEntity(project.getProjectManager()))
                .build();
    }

    public static Project toEntity(ProjectDto dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setProjectManager(ProjectManagerDto.toEntity(dto.getProjectManager()));

        return project;
    }

     */
}
