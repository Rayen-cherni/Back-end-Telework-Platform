package com.telework.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeveloperDto extends UserDto {

    private List<ProjectDto> projects;

    private List<HistoriqueDto> historiques;

}
