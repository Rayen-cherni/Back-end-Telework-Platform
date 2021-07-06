package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IAdminRepository;
import com.telework.demo.services.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDto save(AdminDto dto) {

        boolean isExist = adminRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.ADMIN_ALREADY_EXISTS);
        } else {
            return AdminDto.fromEntity(adminRepository.save(AdminDto.toEntity(dto)));

        }
    }

    @Override
    public AdminDto findById(Integer id) {
        return adminRepository.findById(id).map(AdminDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessages.ADMIN_NOT_FOUND, id)
        );
    }
}
