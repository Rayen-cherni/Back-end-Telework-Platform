package com.telework.demo.controller;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;
import com.telework.demo.services.IDeveloperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.DEVELOPER_ENDPOINT;

@RestController
@RequestMapping(DEVELOPER_ENDPOINT)
@Api(DEVELOPER_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
public class DeveloperController {

    private final IDeveloperService service;

    public DeveloperController(IDeveloperService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeveloperDto save(@RequestBody DeveloperDto dto) {
        return service.save(dto);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeveloperDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<DeveloperDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PatchMapping(value = "/update/withHoldingType/{id}/{withHoldingType}")
    @ApiOperation(value = "To update the holding status ")
    DeveloperDto updateWithHoldingStatus(@PathVariable Integer id,
                                         @PathVariable WithHoldingType withHoldingType) {
        return service.updateWithHoldingStatus(id, withHoldingType);
    }

    @PatchMapping(value = "/update/developerProfile")
    DeveloperDto updateProfile(@RequestHeader(name = "Authorization") String token, @RequestBody UpdateUserForm updateUserForm) {
        return service.updateProfile(token, updateUserForm);
    }

    @PatchMapping(value = "/update/password")
    public DeveloperDto changePassword(@RequestBody ChangePasswordRequest request) {
        return service.changePassword(request);
    }

    @PostMapping(value = "/update/role/{idDeveloper}")
    @ApiOperation(value = "We can change his role only to Project Manager")
    public ProjectManagerDto changeRoleToProjectManager(@PathVariable Integer idDeveloper) {
        return service.changeRoleToProjectManager(idDeveloper);
    }


}
