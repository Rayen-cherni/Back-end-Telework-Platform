package com.telework.demo.services.createUserStrategy;

import com.telework.demo.domain.dto.AdminDto;
import com.telework.demo.domain.entity.Admin;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IAdminRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.REGISTER_PROCESS_NOT_VALID;
import static com.telework.demo.exception.ErrorMessages.ROLE_NOT_FOUND_BY_NAME;

@Service("AdminStrategy")
public class CreateAdmin implements IStrategy<AdminDto> {

    @Autowired
    private IAdminRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public AdminDto createUser(CreateUserForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new InvalidOperationException(REGISTER_PROCESS_NOT_VALID);
        }

        Admin admin = new Admin();
        admin.setFirstname(userForm.getFirstname());
        admin.setLastname(userForm.getLastname());
        admin.setPassword(passwordEncoder.encode(userForm.getPassword()));
        admin.setEmail(userForm.getEmail());
        admin.setTelNum(userForm.getTelNum());
        admin.setWithHoldingType(userForm.getWithHoldingType());
        admin.setAdress(userForm.getAdress());
        admin.setUserStatus(userForm.getUserStatus());
        admin.setPresential(0);
        admin.setRemote(0);
        String roleName = userForm.getRole();
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        admin.setRole(role);

        return modelMapper.map(repository.save(admin), AdminDto.class);
    }
}
