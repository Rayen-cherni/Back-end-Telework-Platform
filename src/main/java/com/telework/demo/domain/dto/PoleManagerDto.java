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

    public static PoleManagerDto fromEntity(PoleManager poleManager) {
        if (poleManager == null) {
            return null;
        }

        return PoleManagerDto.builder()
                .id(poleManager.getId())
                .firstname(poleManager.getFirstname())
                .lastname(poleManager.getLastname())
                .email(poleManager.getEmail())
                .password(poleManager.getPassword())
                .telNum(poleManager.getTelNum())
                .adress(poleManager.getAdress())
                .userStatus(poleManager.getUserStatus())
                .presential(poleManager.getPresential())
                .remote(poleManager.getRemote())
                .withHoldingType(poleManager.getWithHoldingType())
                .role(RoleDto.fromEntity(poleManager.getRole()))
                .pole(PoleDto.fromEntity(poleManager.getPole()))
                .build();
    }

    public static PoleManager toEntity(PoleManagerDto dto) {
        if (dto == null) {
            return null;
        }
        PoleManager poleManager = new PoleManager();

        poleManager.setId(dto.getId());
        poleManager.setFirstname(dto.getFirstname());
        poleManager.setLastname(dto.getLastname());
        poleManager.setEmail(dto.getEmail());
        poleManager.setPassword(dto.getPassword());
        poleManager.setTelNum(dto.getTelNum());
        poleManager.setAdress(dto.getAdress());
        poleManager.setUserStatus(dto.getUserStatus());
        poleManager.setRole(RoleDto.toEntity(dto.getRole()));
        poleManager.setPresential(dto.getPresential());
        poleManager.setRemote(dto.getRemote());
        poleManager.setWithHoldingType(dto.getWithHoldingType());
        poleManager.setPole(PoleDto.toEntity(dto.getPole()));
        return poleManager;
    }
}
