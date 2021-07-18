package com.telework.demo.services;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;

import java.util.List;

public interface IDeveloperService {

    DeveloperDto save(DeveloperDto dto);

    DeveloperDto findById(Integer id);

    List<DeveloperDto> findAll();

    void deleteById(Integer id);

    DeveloperDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);

    DeveloperDto updateProfile(String token, UpdateUserForm updateUserForm);

    DeveloperDto changePassword(ChangePasswordRequest request);

    ProjectManagerDto changeRoleToProjectManager(Integer idDeveloper);
}
