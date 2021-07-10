package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleDto {

    private Integer id;

    private String roleName;

    @JsonIgnore
    private List<UserDto> users;

    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper
        /*
    public static RoleDto fromEntity(Role role) {

        if (role == null) {
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }

    public static Role toEntity(RoleDto dto) {

        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        return role;
    }

         */
}
