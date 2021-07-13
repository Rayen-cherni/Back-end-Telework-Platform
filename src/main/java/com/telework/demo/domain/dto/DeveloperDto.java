package com.telework.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeveloperDto extends UserDto {

    private Integer id;

    private List<ProjectDto> projects;

    private List<HistoriqueDto> historiques;


}
