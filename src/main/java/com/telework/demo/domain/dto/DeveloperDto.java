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

    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper

    /*
    public static DeveloperDto fromEntity(Developer developer) {
        if (developer == null) {
            return null;
        }
        return DeveloperDto.builder()
                .id(developer.getId())
                .firstname(developer.getFirstname())
                .lastname(developer.getLastname())
                .email(developer.getEmail())
                .password(developer.getPassword())
                .telNum(developer.getTelNum())
                .adress(developer.getAdress())
                .userStatus(developer.getUserStatus())
                .presential(developer.getPresential())
                .remote(developer.getRemote())
                .withHoldingType(developer.getWithHoldingType())
                .role(RoleDto.fromEntity(developer.getRole()))
                .build();
    }

    public static Developer toEntity(DeveloperDto dto) {
        if (dto == null) {
            return null;
        }

        Developer developer = new Developer();
        developer.setId(dto.getId());
        developer.setFirstname(dto.getFirstname());
        developer.setLastname(dto.getLastname());
        developer.setEmail(dto.getEmail());
        developer.setPassword(dto.getPassword());
        developer.setTelNum(dto.getTelNum());
        developer.setAdress(dto.getAdress());
        developer.setUserStatus(dto.getUserStatus());
        developer.setRole(RoleDto.toEntity(dto.getRole()));
        developer.setPresential(dto.getPresential());
        developer.setRemote(dto.getRemote());
        developer.setWithHoldingType(dto.getWithHoldingType());
        return developer;
    }

     */
}
