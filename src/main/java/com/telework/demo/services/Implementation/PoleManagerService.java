package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IPoleManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.POLE_MANAGER_NOT_FOUND;
import static com.telework.demo.exception.ErrorMessages.USER_ALREADY_EXISTS;

@Service
public class PoleManagerService implements IPoleManagerService {

    @Autowired
    private IPoleManagerRepository repository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PoleManagerDto save(PoleManagerDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PoleManager.class)), PoleManagerDto.class);
    }

    @Override
    public PoleManagerDto findById(Integer id) {
        return repository.findById(id).map(poleManager -> modelMapper
                .map(poleManager, PoleManagerDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id)
                );
    }

    @Override
    public List<PoleManagerDto> findAll() {
        return repository.findAll().stream()
                .map(poleManager -> modelMapper
                        .map(poleManager, PoleManagerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        PoleManagerDto poleManagerDto = findById(id);
        if (poleManagerDto == null) {
            throw new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    public PoleManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        PoleManagerDto poleManagerDto = findById(id);
        poleManagerDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(repository.save(modelMapper.map(poleManagerDto, PoleManager.class)), PoleManagerDto.class);
    }
}
