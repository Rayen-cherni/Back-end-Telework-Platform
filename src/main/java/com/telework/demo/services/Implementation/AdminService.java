package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IAdminRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IAdminService;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.ADMIN_NOT_FOUND;
import static com.telework.demo.exception.ErrorMessages.USER_ALREADY_EXISTS;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;
    private final IUserRepository userRepository;

    public AdminService(IAdminRepository adminRepository, IUserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdminDto save(AdminDto dto) {

        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        } else {
            return AdminDto.fromEntity(adminRepository.save(AdminDto.toEntity(dto)));

        }
    }

    @Override
    public AdminDto findById(Integer id) {
        return adminRepository.findById(id).map(AdminDto::fromEntity).orElseThrow(
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
}
