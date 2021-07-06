package com.telework.demo.services;

import com.telework.demo.domain.dto.DeveloperDto;

import java.util.List;

public interface IDeveloperService {

    DeveloperDto save(DeveloperDto dto);

    DeveloperDto findById(Integer id);

    List<DeveloperDto> findAll();

    void deleteById(Integer id);
}
