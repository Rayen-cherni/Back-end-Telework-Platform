package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    @JsonIgnore
    private String password;

    private String telNum;

    private String adress;

    private UserStatus userStatus;

    private Integer presential;

    private Integer remote;

    private WithHoldingType withHoldingType;

    private RoleDto role;


}
