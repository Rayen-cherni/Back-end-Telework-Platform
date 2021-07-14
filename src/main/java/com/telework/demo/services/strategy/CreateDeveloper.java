package com.telework.demo.services.strategy;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.REGISTER_PROCESS_NOT_VALID;
import static com.telework.demo.exception.ErrorMessages.ROLE_NOT_FOUND_BY_NAME;

@Service("DeveloperStrategy")
public class CreateDeveloper implements IStrategy<DeveloperDto> {
    @Autowired
    private IDeveloperRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public DeveloperDto createUser(CreateUserForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new InvalidOperationException(REGISTER_PROCESS_NOT_VALID);
        }

        Developer developer = new Developer();
        developer.setFirstname(userForm.getFirstname());
        developer.setLastname(userForm.getLastname());
        developer.setPassword(passwordEncoder.encode(userForm.getPassword()));
        developer.setEmail(userForm.getEmail());
        developer.setTelNum(userForm.getTelNum());
        developer.setWithHoldingType(userForm.getWithHoldingType());
        developer.setAdress(userForm.getAdress());
        developer.setUserStatus(userForm.getUserStatus());
        developer.setPresential(0);
        developer.setRemote(0);

        String roleName = userForm.getRole();
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        developer.setRole(role);

        return modelMapper.map(repository.save(developer), DeveloperDto.class);
    }
}
