package com.telework.demo.domain.model;

import com.telework.demo.domain.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

    private String accessToken;
    private UserDto user;
}
