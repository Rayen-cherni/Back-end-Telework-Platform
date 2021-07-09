package com.telework.demo.domain.dto;

import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String telNum;

    private String adress;

    private UserStatus userStatus;

    private Integer presential;

    private Integer remote;

    private WithHoldingType withHoldingType;

    private RoleDto role;

    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper
    /*
    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .telNum(user.getTelNum())
                .adress(user.getAdress())
                .userStatus(user.getUserStatus())
                .presential(user.getPresential())
                .remote(user.getRemote())
                .withHoldingType(user.getWithHoldingType())
                .role(RoleDto.fromEntity(user.getRole()))
                .build();
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();

        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setTelNum(dto.getTelNum());
        user.setAdress(dto.getAdress());
        user.setUserStatus(dto.getUserStatus());
        user.setPresential(dto.getPresential());
        user.setRemote(dto.getRemote());
        user.setWithHoldingType(dto.getWithHoldingType());
        user.setRole(RoleDto.toEntity(dto.getRole()));
        return user;
    }

     */
}
