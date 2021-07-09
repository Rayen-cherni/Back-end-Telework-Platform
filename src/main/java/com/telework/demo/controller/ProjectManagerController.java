package com.telework.demo.controller;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
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
}
