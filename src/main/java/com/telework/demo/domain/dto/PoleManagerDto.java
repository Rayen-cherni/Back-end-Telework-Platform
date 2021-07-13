package com.telework.demo.domain.dto;

import com.telework.demo.domain.entity.PoleManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoleManagerDto extends UserDto {

    private Integer id;

    private PoleDto pole;

}
