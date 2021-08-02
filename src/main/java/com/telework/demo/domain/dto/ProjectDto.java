package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<DeveloperDto> developers;

    @JsonIgnore
    private ProjectManagerDto projectManager;

    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper

}
