package com.telework.demo.mapper;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAdminMapper {

    IAdminMapper adminMapper = Mappers.getMapper(IAdminMapper.class);

    @Mapping(target = "roles",ignore = true)
    Admin toEntity(AdminDto dto);
}
