package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IProjectManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.PROJECT_MANAGER_NOT_FOUND;
import static com.telework.demo.exception.ErrorMessages.USER_ALREADY_EXISTS;

@Service
public class ProjectManagerService implements IProjectManagerService {

    @Autowired
    private IProjectManagerRepository repository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectManagerDto save(ProjectManagerDto projectManagerDto) {
        boolean isExist = userRepository.existsByEmail(projectManagerDto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        } else {

            return modelMapper.
                    map(repository.save(modelMapper.
                            map(projectManagerDto, ProjectManager.class)), ProjectManagerDto.class);
        }
    }

    @Override
    public ProjectManagerDto findById(Integer id) {
        return repository.findById(id).map(projectManager -> modelMapper
                .map(projectManager, ProjectManagerDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id)
                );
    }

    @Override
    public List<ProjectManagerDto> findAll() {
        return repository.findAll().stream()
                .map(projectManager -> modelMapper
                        .map(projectManager, ProjectManagerDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        ProjectManagerDto projectManagerDto = findById(id);
        if (projectManagerDto == null) {
            throw new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    public ProjectManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        ProjectManagerDto projectManagerDto = findById(id);
        projectManagerDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(repository.save(modelMapper.map(projectManagerDto, ProjectManager.class)), ProjectManagerDto.class);
    }
}
