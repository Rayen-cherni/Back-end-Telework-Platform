package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.RoleDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository repository;

    public RoleService(IRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        //TODO test d'existance
        // if(role exist) we can't add him again
        return RoleDto.fromEntity(repository.save(RoleDto.toEntity(roleDto)));
    }

    @Override
    public RoleDto findById(Integer id) {
        return repository.findById(id).map(RoleDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessages.ROLE_NOT_FOUND, id)
        );
    }

    @Override
    public List<RoleDto> findAll() {
        return repository.findAll().stream()
                .map(RoleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        RoleDto roleDto = findById(id);
        if (roleDto == null) {
            throw new EntityNotFoundException(ErrorMessages.ROLE_NOT_FOUND, id);
        }
        repository.deleteById(id);
    }
}
