package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.model.CreatePoleForm;

import java.util.List;

public interface IPoleService {

    //PoleDto save(PoleDto poleDto);
    PoleDto save(CreatePoleForm poleForm);

    PoleDto findById(Integer id);

    List<PoleDto> findAll();

    void delete(Integer id);
}
