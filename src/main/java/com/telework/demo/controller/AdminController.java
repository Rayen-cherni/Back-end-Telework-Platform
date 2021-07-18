package com.telework.demo.controller;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.services.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.telework.demo.utils.Constants.ADMIN_ENDPOINT;

@RestController
@RequestMapping(ADMIN_ENDPOINT)
@Api(ADMIN_ENDPOINT)
public class AdminController {

    private final IAdminService service;

    public AdminController(IAdminService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AdminDto save(@RequestBody AdminDto adminDto) {
        return service.save(adminDto);
    }

    @GetMapping(value = "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    AdminDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PatchMapping(value = "/update/withHoldingType/{id}/{withHoldingType}")
    @ApiOperation(value = "To update the holding status ")
    AdminDto updateWithHoldingStatus(@PathVariable Integer id,
                                     @PathVariable WithHoldingType withHoldingType) {
        return service.updateWithHoldingStatus(id, withHoldingType);
    }

    @PatchMapping(value = "/update/password")
    public AdminDto changePassword(@RequestBody ChangePasswordRequest request) {
        return service.changePassword(request);
    }

}
