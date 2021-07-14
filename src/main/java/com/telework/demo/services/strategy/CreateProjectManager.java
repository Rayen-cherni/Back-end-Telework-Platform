package com.telework.demo.services.strategy;

import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.telework.demo.exception.ErrorMessages.REGISTER_PROCESS_NOT_VALID;
import static com.telework.demo.exception.ErrorMessages.ROLE_NOT_FOUND_BY_NAME;

@Service("Project ManagerStrategy")
public class CreateProjectManager implements IStrategy<ProjectManagerDto> {
    @Autowired
    private IProjectManagerRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public ProjectManagerDto createUser(CreateUserForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new InvalidOperationException(REGISTER_PROCESS_NOT_VALID);
        }

        ProjectManager projectManager = new ProjectManager();
        projectManager.setFirstname(userForm.getFirstname());
        projectManager.setLastname(userForm.getLastname());
        projectManager.setPassword(passwordEncoder.encode(userForm.getPassword()));
        projectManager.setEmail(userForm.getEmail());
        projectManager.setTelNum(userForm.getTelNum());
        projectManager.setWithHoldingType(userForm.getWithHoldingType());
        projectManager.setAdress(userForm.getAdress());
        projectManager.setUserStatus(userForm.getUserStatus());
        projectManager.setPresential(0);
        projectManager.setRemote(0);
        String roleName = userForm.getRole();
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        projectManager.setRole(role);

        return modelMapper.map(repository.save(projectManager), ProjectManagerDto.class);
    }
}
