package com.telework.demo.controller;

import com.telework.demo.domain.dto.RoleDto;
import com.telework.demo.services.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.ROLE_ENDPOINT;

@RestController
@RequestMapping(ROLE_ENDPOINT)
@Api(ROLE_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final IRoleService service;

    public RoleController(IRoleService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    RoleDto save(@RequestBody RoleDto roleDto) {
        return service.save(roleDto);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value ="/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoleDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
