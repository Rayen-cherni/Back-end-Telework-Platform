package com.telework.demo.services;

import com.telework.demo.domain.dto.AdminDto;

public interface IAdminService {

    AdminDto save(AdminDto dto);

    AdminDto findById(Integer id);

    void delete(Integer id);
}
