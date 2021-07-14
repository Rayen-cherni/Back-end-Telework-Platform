package com.telework.demo.services.implementation;

import com.telework.demo.domain.dto.RoleDto;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RoleService(IRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        boolean isExist = repository.existsByRoleName(roleDto.getRoleName());
        if (isExist) {
            throw new InvalidOperationException(ROLE_ALREADY_EXISTS);
        } else {

            return modelMapper.map(repository.save(modelMapper.map(roleDto, Role.class)), RoleDto.class);
        }
    }

    @Override
    public RoleDto findById(Integer id) {

        return repository.findById(id).map((role -> modelMapper.map(role, RoleDto.class))).orElseThrow(
                () -> new EntityNotFoundException(ROLE_NOT_FOUND_BY_ID + id));
    }

    @Override
    public List<RoleDto> findAll() {
        return repository.findAll().stream()
                .map((role -> modelMapper
                        .map(role, RoleDto.class)))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        RoleDto roleDto = findById(id);
        if (roleDto == null) {
            throw new EntityNotFoundException(ROLE_NOT_FOUND_BY_ID + id);
        }
        repository.deleteById(id);
    }
}
