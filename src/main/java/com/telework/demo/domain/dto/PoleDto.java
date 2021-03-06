package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PoleDto {

    private Integer id;

    private String name;

    private String description;

    private Integer capacity;

    private Integer reserved;

    @JsonIgnore
    private PoleManagerDto poleManager;

}
