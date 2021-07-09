package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleDto;
import com.telework.demo.domain.model.CreatePoleForm;

import java.util.List;

public interface IPoleService {

    PoleDto save(CreatePoleForm poleForm);

    PoleDto findById(Integer id);

    List<PoleDto> findAll();

    void delete(Integer id);

    PoleDto updateName(Integer id, String name);

    PoleDto updateDescription(Integer id, String description);

    PoleDto updateCapacity(Integer id, Integer capacity);

    PoleDto updateReserved(Integer id, Integer reserved);

    PoleDto updatePoleManager(Integer id, Integer idPoleManager);
}
