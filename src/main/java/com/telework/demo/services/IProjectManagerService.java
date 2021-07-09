package com.telework.demo.services;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;

import java.util.List;

public interface IProjectManagerService {

    ProjectManagerDto save(ProjectManagerDto projectManagerDto);

    ProjectManagerDto findById(Integer id);

    List<ProjectManagerDto> findAll();

    void delete(Integer id);

    ProjectManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);
}
