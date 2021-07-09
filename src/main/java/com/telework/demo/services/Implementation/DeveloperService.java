package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IDeveloperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.DEVELOPER_NOT_FOUND;

@Service
public class DeveloperService implements IDeveloperService {

    @Autowired
    private IDeveloperRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DeveloperDto save(DeveloperDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.USER_ALREADY_EXISTS);
        } else {
            return modelMapper.map(repository.save(modelMapper.map(dto, Developer.class)), DeveloperDto.class);

        }
    }

    @Override
    public DeveloperDto findById(Integer id) {
        return repository.findById(id).map(developer -> modelMapper.map(developer, DeveloperDto.class)).orElseThrow(
                () -> new EntityNotFoundException(DEVELOPER_NOT_FOUND + id)
        );

    }

    @Override
    public List<DeveloperDto> findAll() {
        return repository.findAll().stream()
                .map((developer -> modelMapper.map(developer, DeveloperDto.class))).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        DeveloperDto developerDto = findById(id);
        if (developerDto == null) {
            throw new EntityNotFoundException(DEVELOPER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    public DeveloperDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        DeveloperDto developerDto = findById(id);
        developerDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(repository.save(modelMapper.map(developerDto, Developer.class)), DeveloperDto.class);
    }
}
