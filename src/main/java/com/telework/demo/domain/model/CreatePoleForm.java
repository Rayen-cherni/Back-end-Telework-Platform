package com.telework.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePoleForm {

    private Integer id;

    private String name;

    private String description;

    private Integer capacity;

    private Integer reserved;

    private Integer poleManagerId;

}
