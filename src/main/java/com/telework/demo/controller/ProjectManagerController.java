package com.telework.demo.controller;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.services.IProjectManagerService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.PROJECT_MANAGER_ENDPOINT;

@RestController
@Api(PROJECT_MANAGER_ENDPOINT)
public class ProjectManagerController {


    private final IProjectManagerService service;

    public ProjectManagerController(IProjectManagerService service) {
        this.service = service;
    }


    @PostMapping(value = PROJECT_MANAGER_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectManagerDto save(@RequestBody ProjectManagerDto projectManagerDto) {
        return service.save(projectManagerDto);
    }

    @GetMapping(value = PROJECT_MANAGER_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectManagerDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = PROJECT_MANAGER_ENDPOINT + "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProjectManagerDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(PROJECT_MANAGER_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
