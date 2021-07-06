package com.telework.demo.domain.dto;

import com.telework.demo.domain.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto extends UserDto {

    private Integer id;

    public static AdminDto fromEntity(Admin admin) {
        if (admin == null) {
            return null;
        }
        return AdminDto.builder()
                .id(admin.getId())
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .adress(admin.getAdress())
                .telNum(admin.getTelNum())
                .userStatus(admin.getUserStatus())
                .presential(admin.getPresential())
                .remote(admin.getRemote())
                .withHoldingType(admin.getWithHoldingType())
                .role(RoleDto.fromEntity(admin.getRole()))
                .build();
    }

    public static Admin toEntity(AdminDto dto) {
        if (dto == null) {
            return null;
        }
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setFirstname(dto.getFirstname());
        admin.setLastname(dto.getLastname());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setAdress(dto.getAdress());
        admin.setTelNum(dto.getTelNum());
        admin.setUserStatus(dto.getUserStatus());
        admin.setPresential(dto.getPresential());
        admin.setRemote(dto.getRemote());
        admin.setWithHoldingType(dto.getWithHoldingType());
        admin.setRole(RoleDto.toEntity(dto.getRole()));
        return admin;
    }
}
