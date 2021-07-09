package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;

import java.util.List;

public interface IPoleManagerService {

    PoleManagerDto save(PoleManagerDto dto);

    PoleManagerDto findById(Integer id);

    List<PoleManagerDto> findAll();

    void delete(Integer id);

    PoleManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);


}
