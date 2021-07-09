package com.telework.demo.controller;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.services.IPoleManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.POLE_MANAGER_ENDPOINT;

@RestController
@RequestMapping(POLE_MANAGER_ENDPOINT)
@Api(POLE_MANAGER_ENDPOINT)
public class PoleManagerController {


    private final IPoleManagerService service;

    public PoleManagerController(IPoleManagerService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    PoleManagerDto save(@RequestBody PoleManagerDto dto) {
        return service.save(dto);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    PoleManagerDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PoleManagerDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PatchMapping(value = "/update/withHoldingType/{id}/{withHoldingType}")
    @ApiOperation(value = "To update the holding status ")
    PoleManagerDto updateWithHoldingStatus(@PathVariable Integer id,
                                           @PathVariable WithHoldingType withHoldingType) {
        return service.updateWithHoldingStatus(id, withHoldingType);
    }
}
