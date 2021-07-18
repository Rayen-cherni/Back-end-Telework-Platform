package com.telework.demo.controller;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;
import com.telework.demo.services.IProjectManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.PROJECT_MANAGER_ENDPOINT;

@RestController
@RequestMapping(PROJECT_MANAGER_ENDPOINT)
@Api(PROJECT_MANAGER_ENDPOINT)
public class ProjectManagerController {


    private final IProjectManagerService service;

    public ProjectManagerController(IProjectManagerService service) {
        this.service = service;
    }


    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectManagerDto save(@RequestBody ProjectManagerDto projectManagerDto) {
        return service.save(projectManagerDto);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectManagerDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProjectManagerDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PatchMapping(value = "/update/withHoldingType/{id}/{withHoldingType}")
    @ApiOperation(value = "To update the holding status ")
    ProjectManagerDto updateWithHoldingStatus(@PathVariable Integer id,
                                              @PathVariable WithHoldingType withHoldingType) {
        return service.updateWithHoldingStatus(id, withHoldingType);
    }

    @GetMapping(value = "/findProjectsByIdProjectManager/{id}")
    @ApiOperation(value = "To get all developers by project manager ")
    public List<List<DeveloperDto>> getAllDevelopersByProjectManager(@PathVariable("id") Integer idProjectManager) {
        return service.getAllDevelopersByProjectManager(idProjectManager);
    }

    @PatchMapping(value = "/update/projectManagerProfile")
    ProjectManagerDto updateProfile(@RequestHeader(name = "Authorization") String token, @RequestBody UpdateUserForm updateUserForm) {
        return service.updateProfile(token, updateUserForm);
    }

    @PatchMapping(value = "/update/password")
    public ProjectManagerDto changePassword(@RequestBody ChangePasswordRequest request) {
        return service.changePassword(request);
    }

    @PostMapping(value = "/update/role/{idProjectManager}")
    @ApiOperation(value = "We can change his role only to Pole Manager")
    public PoleManagerDto changeRoleToProjectManager(@PathVariable Integer idProjectManager) {
        return service.changeRoleToPoleManager(idProjectManager);
    }
}
