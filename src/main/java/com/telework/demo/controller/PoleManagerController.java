package com.telework.demo.controller;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.services.IPoleManagerService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.POLE_MANAGER_ENDPOINT;

@RestController
@Api(POLE_MANAGER_ENDPOINT)
public class PoleManagerController {


    private final IPoleManagerService service;

    public PoleManagerController(IPoleManagerService service) {
        this.service = service;
    }

    @PostMapping(value = POLE_MANAGER_ENDPOINT + "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    PoleManagerDto save(@RequestBody PoleManagerDto dto) {
        return service.save(dto);
    }

    @GetMapping(value = POLE_MANAGER_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    PoleManagerDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = POLE_MANAGER_ENDPOINT + "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PoleManagerDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = POLE_MANAGER_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
