package com.telework.demo.domain.dto;

import com.telework.demo.domain.entity.Pole;
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

    private PoleManagerDto poleManager;


    public static PoleDto fromEntity(Pole pole) {
        if (pole == null) {
            return null;
        }
        return PoleDto.builder()
                .id(pole.getId())
                .name(pole.getName())
                .description(pole.getDescription())
                .capacity(pole.getCapacity())
                .reserved(pole.getReserved())
                .poleManager(PoleManagerDto.fromEntity(pole.getPoleManager()))
                .build();
    }

    public static Pole toEntity(PoleDto dto) {
        if (dto == null) {
            return null;

        }

        Pole pole = new Pole();
        pole.setId(dto.getId());
        pole.setName(dto.getName());
        pole.setDescription(dto.getDescription());
        pole.setCapacity(dto.getCapacity());
        pole.setReserved(dto.getReserved());
        pole.setPoleManager(PoleManagerDto.toEntity(dto.getPoleManager()));
        return pole;
    }
}
