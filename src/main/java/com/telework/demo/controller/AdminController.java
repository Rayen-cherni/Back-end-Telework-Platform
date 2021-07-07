package com.telework.demo.controller;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.services.IAdminService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.telework.demo.utils.Constants.ADMIN_ENDPOINT;

@RestController
@Api(ADMIN_ENDPOINT)
public class AdminController {

    private final IAdminService service;

    public AdminController(IAdminService service) {
        this.service = service;
    }

    @PostMapping(value = ADMIN_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AdminDto save(@RequestBody AdminDto adminDto) {
        return service.save(adminDto);
    }

    @GetMapping(value = ADMIN_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    AdminDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping(value = ADMIN_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
