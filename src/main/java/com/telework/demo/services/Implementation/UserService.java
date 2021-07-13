package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.entity.*;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.*;
import com.telework.demo.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private IDeveloperRepository developerRepository;
    @Autowired
    private IPoleManagerRepository poleManagerRepository;
    @Autowired
    private IProjectManagerRepository projectManagerRepository;

    @Override
    public UserDto findById(Integer id) {

        return repository.findById(id).map((user -> modelMapper.map(user, UserDto.class))).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_BY_ID)
        );
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(user -> modelMapper
                        .map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByEmail(String email) {

        if (!repository.existsByEmail(email)) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_EMAIL);
        }

        User user = repository.findByEmail(email);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateStatus(Integer id, UserStatus userStatus) {
        //TODO refactoring is necessary is this case we need to use DTO
        //UserDto userDto = modelMapper.map(repository.findById(id).get(), UserDto.class);
        //TODO test si l'utilisitauer en service ou pas !
        if (repository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        User user = repository.findById(id).get();
        user.setUserStatus(userStatus);

        return modelMapper.map(repository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUserByUserStatus(UserStatus userStatus) {

        return repository.findByUserStatus(userStatus).stream().map(user ->
                modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto createUser(CreateUserForm userForm) {

        if (repository.existsByEmail(userForm.getEmail())) {
            throw new InvalidOperationException(REGISTER_PROCESS_NOT_VALID);
        }

        User user = new User();
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setTelNum(userForm.getTelNum());
        user.setWithHoldingType(userForm.getWithHoldingType());
        user.setAdress(userForm.getAdress());
        user.setUserStatus(userForm.getUserStatus());
        user.setPresential(0);
        user.setRemote(0);
        //TODO TEST sur le role si existe ou pas
        String roleName = userForm.getRole();
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        user.setRole(role);
        if (roleName.equals("Admin")) {
            adminRepository.save((Admin) user);
        }
        if (roleName.equals("Developer")) {
            developerRepository.save((Developer) user);
        }
        if (roleName.equals("Pole Manager")) {
            poleManagerRepository.save((PoleManager) user);
        }
        if (roleName.equals("Project Manager")) {
            projectManagerRepository.save((ProjectManager) user);
        }

        return null;
    }

}
