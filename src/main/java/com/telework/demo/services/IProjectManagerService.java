package com.telework.demo.services;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.ProjectManagerLite;
import com.telework.demo.domain.model.UpdateUserForm;

import java.util.List;

public interface IProjectManagerService {

    ProjectManagerDto save(ProjectManagerDto projectManagerDto);

    ProjectManagerDto findById(Integer id);

    List<ProjectManagerDto> findAll();

 //   List<ProjectManagerLite> findAllLite();

    void delete(Integer id);

    ProjectManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);

    void updateProjectsList(Integer idProjectManager, Integer idProject);

    List<DeveloperDto> getAllDevelopersByProjectManager(Integer idProjectManager);

    ProjectManagerDto updateProfile(String token, UpdateUserForm updateUserForm);

    ProjectManagerDto changePassword(ChangePasswordRequest request);

    PoleManagerDto changeRoleToPoleManager(Integer idProjectManager);

}
