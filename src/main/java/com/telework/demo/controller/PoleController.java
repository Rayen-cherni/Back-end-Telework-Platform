package com.telework.demo.controller;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.model.CreatePoleForm;
import com.telework.demo.services.IPoleService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.POLE_ENDPOINT;

@RestController(POLE_ENDPOINT)
@Api(POLE_ENDPOINT)
public class PoleController {

    private final IPoleService service;

    public PoleController(IPoleService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PoleDto save(@RequestBody CreatePoleForm poleForm) {
        return service.save(poleForm);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    PoleDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PoleDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
