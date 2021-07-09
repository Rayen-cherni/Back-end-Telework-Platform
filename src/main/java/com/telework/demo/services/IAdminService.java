package com.telework.demo.services;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;

public interface IAdminService {

    AdminDto save(AdminDto dto);

    AdminDto findById(Integer id);

    void delete(Integer id);

    AdminDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);
}
