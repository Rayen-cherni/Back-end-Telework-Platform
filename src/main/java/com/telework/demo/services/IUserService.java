package com.telework.demo.services;

import com.telework.demo.domain.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
}
