package com.telework.demo.controller;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.model.CreatePoleForm;
import com.telework.demo.services.IPoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.POLE_ENDPOINT;

@RestController
@RequestMapping(POLE_ENDPOINT)
@Api(POLE_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
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

    @PatchMapping(value = "/update/name/{id}/{name}")
    PoleDto updateName(@PathVariable Integer id,
                       @PathVariable String name) {
        return service.updateName(id, name);
    }

    @PatchMapping(value = "/update/description/{id}/{description}")
    PoleDto updateDescription(@PathVariable Integer id,
                              @PathVariable String description) {
        return service.updateDescription(id, description);
    }

    @PatchMapping(value = "/update/capacity/{id}/{capacity}")
    @ApiOperation(value = " For updating the number of total places in the pole")
    PoleDto updateCapacity(@PathVariable Integer id,
                           @PathVariable Integer capacity) {
        return service.updateCapacity(id, capacity);
    }

    @PatchMapping(value = "/update/reserved/{id}/{reserved}")
    @ApiOperation(value = " For updating the number of reserved places")
    PoleDto updateReserved(@PathVariable Integer id,
                           @PathVariable Integer reserved) {
        return service.updateReserved(id, reserved);
    }

    @PatchMapping(value = "/update/poleManager/{id}/{idPoleManager}")
    PoleDto updatePoleManager(@PathVariable Integer id,
                              @PathVariable Integer idPoleManager) {
        return service.updatePoleManager(id, idPoleManager);
    }
}
