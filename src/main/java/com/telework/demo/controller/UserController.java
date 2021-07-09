package com.telework.demo.controller;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.services.IUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.USER_ENDPOINT;

@RestController
@RequestMapping(USER_ENDPOINT)
@Api(USER_ENDPOINT)
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping(value = "/findAll")
    List<UserDto> findAll() {
        return service.findAll();
    }

    @PatchMapping(value = "/update/userStatus/{id}/{userStatus}")
    UserDto updateStatus(@PathVariable Integer id,
                         @PathVariable UserStatus userStatus) {

        return service.updateStatus(id, userStatus);
    }

    @GetMapping(value = "/filterByUserStatus/{userStatus}")
    public List<UserDto> getAllUserByUserStatus(@PathVariable UserStatus userStatus) {
        return service.getAllUserByUserStatus(userStatus);
    }

}
