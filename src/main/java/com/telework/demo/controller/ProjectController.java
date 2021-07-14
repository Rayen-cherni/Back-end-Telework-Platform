package com.telework.demo.controller;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.model.CreateProjectForm;
import com.telework.demo.services.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.PROJECT_ENDPOINT;

@RestController
@RequestMapping(PROJECT_ENDPOINT)
@Api(PROJECT_ENDPOINT)
public class ProjectController {


    private final IProjectService service;

    public ProjectController(IProjectService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectDto save(@RequestBody CreateProjectForm projectForm) {
        return service.save(projectForm);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProjectDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PatchMapping(value = "/updateAssignementOfDeveloper/{idProject}/{idDeveloper}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    void assignementOfDeveloper(@PathVariable Integer idProject,
                                @PathVariable Integer idDeveloper) {
         service.assignementOfDeveloper(idProject, idDeveloper);
    }
}
