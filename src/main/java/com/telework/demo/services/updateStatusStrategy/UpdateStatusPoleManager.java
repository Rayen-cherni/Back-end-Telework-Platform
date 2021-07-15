package com.telework.demo.services.updateStatusStrategy;


import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;
import static com.telework.demo.exception.ErrorMessages.USER_OUT_OF_SERVICE;

@Service("updateStatusStrategyPole Manager")
public class UpdateStatusPoleManager implements IUpdateStatusStrategy<PoleManagerDto> {
    @Autowired
    private IPoleManagerRepository poleManagerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PoleManagerDto updateStatus(Integer id, UserStatus userStatus) {
        if (poleManagerRepository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        PoleManagerDto poleManagerDto = modelMapper
                .map(poleManagerRepository
                        .findById(id).get(), PoleManagerDto.class);


        if (poleManagerDto.getWithHoldingType() != WithHoldingType.NONE) {
            throw new InvalidOperationException(USER_OUT_OF_SERVICE);
        }
        poleManagerDto.setUserStatus(userStatus);

        return modelMapper
                .map(poleManagerRepository
                        .save(modelMapper
                                .map(poleManagerDto, PoleManager.class)), PoleManagerDto.class);
    }
}
