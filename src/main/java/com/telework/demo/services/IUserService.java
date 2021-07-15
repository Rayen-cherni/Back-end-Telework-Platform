package com.telework.demo.services;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.model.CreateUserForm;

import java.util.List;

public interface IUserService {

    UserDto findById(Integer id);

    List<UserDto> findAll();

    UserDto findByEmail(String email);

   // UserDto updateStatus(Integer id, UserStatus userStatus);

    List<UserDto> getAllUserByUserStatus(UserStatus userStatus);

}
