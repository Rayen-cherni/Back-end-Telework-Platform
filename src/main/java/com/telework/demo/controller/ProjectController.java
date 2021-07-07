package com.telework.demo.controller;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.services.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.PROJECT_ENDPOINT;

@RestController
@Api(PROJECT_ENDPOINT)
public class ProjectController {


    private final IProjectService service;

    public ProjectController(IProjectService service) {
        this.service = service;
    }

    @PostMapping(value = PROJECT_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectDto save(@RequestBody ProjectDto projectDto) {
        return service.save(projectDto);
    }

    @GetMapping(value = PROJECT_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = PROJECT_ENDPOINT + "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProjectDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(PROJECT_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
