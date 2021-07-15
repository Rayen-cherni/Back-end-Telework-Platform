package com.telework.demo.services.createUserStrategy;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.REGISTER_PROCESS_NOT_VALID;
import static com.telework.demo.exception.ErrorMessages.ROLE_NOT_FOUND_BY_NAME;

@Service("Pole ManagerStrategy")
public class CreatePoleManager implements IStrategy<PoleManagerDto> {

    @Autowired
    private IPoleManagerRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public PoleManagerDto createUser(CreateUserForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new InvalidOperationException(REGISTER_PROCESS_NOT_VALID);
        }

        PoleManager poleManager = new PoleManager();
        poleManager.setFirstname(userForm.getFirstname());
        poleManager.setLastname(userForm.getLastname());
        poleManager.setPassword(passwordEncoder.encode(userForm.getPassword()));
        poleManager.setEmail(userForm.getEmail());
        poleManager.setTelNum(userForm.getTelNum());
        poleManager.setWithHoldingType(userForm.getWithHoldingType());
        poleManager.setAdress(userForm.getAdress());
        poleManager.setUserStatus(userForm.getUserStatus());
        poleManager.setPresential(0);
        poleManager.setRemote(0);
        String roleName = userForm.getRole();
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        poleManager.setRole(role);

        return modelMapper.map(repository.save(poleManager), PoleManagerDto.class);
    }
}
