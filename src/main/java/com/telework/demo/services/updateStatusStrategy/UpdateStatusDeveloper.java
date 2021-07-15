package com.telework.demo.services.updateStatusStrategy;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;
import static com.telework.demo.exception.ErrorMessages.USER_OUT_OF_SERVICE;

@Service("updateStatusStrategyDeveloper")
public class UpdateStatusDeveloper implements IUpdateStatusStrategy<DeveloperDto> {

    @Autowired
    private IDeveloperRepository developerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DeveloperDto updateStatus(Integer id, UserStatus userStatus) {

        if (developerRepository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        DeveloperDto developerDto = modelMapper
                .map(developerRepository
                        .findById(id).get(), DeveloperDto.class);


        if (developerDto.getWithHoldingType() != WithHoldingType.NONE) {
            throw new InvalidOperationException(USER_OUT_OF_SERVICE);
        }
        developerDto.setUserStatus(userStatus);

        return modelMapper
                .map(developerRepository
                        .save(modelMapper
                                .map(developerDto, Developer.class)), DeveloperDto.class);
    }
}
