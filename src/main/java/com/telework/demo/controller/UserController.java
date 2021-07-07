package com.telework.demo.controller;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.services.IUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.telework.demo.utils.Constants.USER_ENDPOINT;

@RestController
@Api(USER_ENDPOINT)
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping(USER_ENDPOINT + "/findAll")
    List<UserDto> findAll() {
        return service.findAll();
    }
}
