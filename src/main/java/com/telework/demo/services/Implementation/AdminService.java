package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.Admin;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IAdminRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.ADMIN_NOT_FOUND;
import static com.telework.demo.exception.ErrorMessages.USER_ALREADY_EXISTS;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AdminDto save(AdminDto dto) {

        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        } else {
            return modelMapper.map(adminRepository.save(modelMapper.map(dto, Admin.class)), AdminDto.class);
        }
    }

    @Override
    public AdminDto findById(Integer id) {
        return adminRepository.findById(id).map((admin -> modelMapper.map(admin, AdminDto.class))).orElseThrow(
                () -> new EntityNotFoundException(ADMIN_NOT_FOUND + id)
        );
    }

    @Override
    public void delete(Integer id) {
        AdminDto adminDto = findById(id);
        if (adminDto == null) {
            throw new EntityNotFoundException(ADMIN_NOT_FOUND + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public AdminDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {

        AdminDto adminDto = findById(id);
        adminDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(adminRepository.save(modelMapper.map(adminDto, Admin.class)), AdminDto.class);

    }
}
