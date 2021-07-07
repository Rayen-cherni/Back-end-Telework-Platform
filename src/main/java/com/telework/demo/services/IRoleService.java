package com.telework.demo.services;

import com.telework.demo.domain.dto.RoleDto;

import java.util.List;

public interface IRoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Integer id);

    List<RoleDto> findAll();

    void delete(Integer id);
}
