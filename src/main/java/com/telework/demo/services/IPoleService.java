package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleDto;

import java.util.List;

public interface IPoleService {

    PoleDto save(PoleDto poleDto);

    PoleDto findById(Integer id);

    List<PoleDto> findAll();

    void deleteById(Integer id);
}
