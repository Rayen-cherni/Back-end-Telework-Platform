package com.telework.demo.services.updateStatusStrategy;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.Admin;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IAdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;
import static com.telework.demo.exception.ErrorMessages.USER_OUT_OF_SERVICE;

@Service("updateStatusStrategyAdmin")
public class UpdateStatusAdmin implements IUpdateStatusStrategy<AdminDto> {
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdminDto updateStatus(Integer id, UserStatus userStatus) {

        if (adminRepository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        AdminDto adminDto = modelMapper.map(adminRepository.findById(id).get(), AdminDto.class);


        if (adminDto.getWithHoldingType() != WithHoldingType.NONE) {
            throw new InvalidOperationException(USER_OUT_OF_SERVICE);
        }
        adminDto.setUserStatus(userStatus);

        return modelMapper
                .map(adminRepository
                        .save(modelMapper
                                .map(adminDto, Admin.class)), AdminDto.class);

    }
}
