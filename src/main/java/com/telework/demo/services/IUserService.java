package com.telework.demo.services;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.entity.enumeration.UserStatus;

import java.util.List;

public interface IUserService {

    UserDto findById(Integer id);

    List<UserDto> findAll();

    UserDto updateStatus(Integer id, UserStatus userStatus);

    List<UserDto> getAllUserByUserStatus(UserStatus userStatus);


}
