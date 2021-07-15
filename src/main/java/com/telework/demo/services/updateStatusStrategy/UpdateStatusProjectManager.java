package com.telework.demo.services.updateStatusStrategy;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;
import static com.telework.demo.exception.ErrorMessages.USER_OUT_OF_SERVICE;

@Service("updateStatusStrategyProject Manager")
public class UpdateStatusProjectManager implements IUpdateStatusStrategy<ProjectManagerDto> {
    @Autowired
    private IProjectManagerRepository projectManagerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectManagerDto updateStatus(Integer id, UserStatus userStatus) {

        if (projectManagerRepository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        ProjectManagerDto projectManagerDto = modelMapper
                .map(projectManagerRepository
                        .findById(id).get(), ProjectManagerDto.class);

        if (projectManagerDto.getWithHoldingType() != WithHoldingType.NONE) {
            throw new InvalidOperationException(USER_OUT_OF_SERVICE);
        }
        projectManagerDto.setUserStatus(userStatus);

        return modelMapper
                .map(projectManagerRepository
                        .save(modelMapper
                                .map(projectManagerDto, ProjectManager.class)), ProjectManagerDto.class);
    }
}
