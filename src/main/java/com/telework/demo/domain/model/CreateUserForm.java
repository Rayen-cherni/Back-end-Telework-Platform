package com.telework.demo.domain.model;

import com.telework.demo.domain.dto.RoleDto;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserForm {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String telNum;

    private String adress;

    private UserStatus userStatus;

    private WithHoldingType withHoldingType;

    private String role;
}
