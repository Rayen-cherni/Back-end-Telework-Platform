package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ProjectManagerDto extends UserDto {

    private Integer id;

    @JsonIgnore
    private List<ProjectDto> projects;

    /*
    public static ProjectManagerDto fromEntity(ProjectManager projectManager) {
        if (projectManager == null) {
            return null;
        }

        return ProjectManagerDto.builder()
                .id(projectManager.getId())
                .firstname(projectManager.getFirstname())
                .lastname(projectManager.getLastname())
                .email(projectManager.getEmail())
                .password(projectManager.getPassword())
                .telNum(projectManager.getTelNum())
                .adress(projectManager.getAdress())
                .userStatus(projectManager.getUserStatus())
                .role(RoleDto.fromEntity(projectManager.getRole()))
                .presential(projectManager.getPresential())
                .remote(projectManager.getRemote())
                .withHoldingType(projectManager.getWithHoldingType())
                .build();
    }

    public static ProjectManager toEntity(ProjectManagerDto dto) {
        if (dto == null) {
            return null;
        }
        ProjectManager projectManager = new ProjectManager();

        projectManager.setId(dto.getId());
        projectManager.setFirstname(dto.getFirstname());
        projectManager.setLastname(dto.getLastname());
        projectManager.setEmail(dto.getEmail());
        projectManager.setPassword(dto.getPassword());
        projectManager.setTelNum(dto.getTelNum());
        projectManager.setAdress(dto.getAdress());
        projectManager.setUserStatus(dto.getUserStatus());
        projectManager.setRole(RoleDto.toEntity(dto.getRole()));
        projectManager.setPresential(dto.getPresential());
        projectManager.setRemote(dto.getRemote());
        projectManager.setWithHoldingType(dto.getWithHoldingType());
        return projectManager;
    }

     */
}
