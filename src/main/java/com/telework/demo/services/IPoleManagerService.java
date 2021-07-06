package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleManagerDto;

import java.util.List;

public interface IPoleManagerService {

    PoleManagerDto save(PoleManagerDto dto);

    PoleManagerDto findById(Integer id);

    List<PoleManagerDto> findAll();

    void deleteById(Integer id);

}
