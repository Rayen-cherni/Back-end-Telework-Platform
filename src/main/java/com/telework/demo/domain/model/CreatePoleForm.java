package com.telework.demo.domain.model;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePoleForm {

    private String name;

    private String description;

    private Integer capacity;

    private Integer reserved;

    private Integer poleManagerId;

    public static PoleDto convertToPoleDto(CreatePoleForm poleForm, PoleManagerDto poleManagerDto) {

        PoleDto poleDto = PoleDto.builder()
                .name(poleForm.getName())
                .capacity(poleForm.getCapacity())
                .poleManager(poleManagerDto)
                .reserved(poleForm.getReserved())
                .description(poleForm.getDescription())
                .build();
        ;
        return poleDto;
    }


}
