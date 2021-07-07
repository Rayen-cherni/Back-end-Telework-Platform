package com.telework.demo.controller;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.services.IDeveloperService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.DEVELOPER_ENDPOINT;

@RestController
@Api(DEVELOPER_ENDPOINT)
public class DeveloperController {

    private final IDeveloperService service;

    public DeveloperController(IDeveloperService service) {
        this.service = service;
    }

    @PostMapping(value = DEVELOPER_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeveloperDto save(@RequestBody DeveloperDto dto) {
        return service.save(dto);
    }

    @GetMapping(value = DEVELOPER_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeveloperDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = DEVELOPER_ENDPOINT + "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<DeveloperDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(DEVELOPER_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
