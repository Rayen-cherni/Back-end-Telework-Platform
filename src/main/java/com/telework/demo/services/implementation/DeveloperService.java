package com.telework.demo.services.implementation;

import com.telework.demo.configuration.securityConfiguration.jwt.JwtProvider;
import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.ErrorMessages;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IDeveloperRepository;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IDeveloperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class DeveloperService implements IDeveloperService {

    @Autowired
    private IDeveloperRepository repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProjectManagerRepository projectManagerRepository;


    @Override
    public DeveloperDto save(DeveloperDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(ErrorMessages.USER_ALREADY_EXISTS);
        } else {
            return modelMapper.map(repository.save(modelMapper.map(dto, Developer.class)), DeveloperDto.class);

        }
    }

    @Override
    public DeveloperDto findById(Integer id) {
        return repository.findById(id)
                .map((developer -> modelMapper.
                        map(developer, DeveloperDto.class)))
                .orElseThrow(
                        () -> new EntityNotFoundException(DEVELOPER_NOT_FOUND + id)
                );
    }

    @Override
    public List<DeveloperDto> findAll() {

        return repository.findAll()
                .stream()
                .map((developer -> modelMapper.map(developer, DeveloperDto.class)))
                .collect(Collectors.toList()) ;

    }

    @Override
    public void deleteById(Integer id) {
        DeveloperDto developerDto = findById(id);
        if (developerDto == null) {
            throw new EntityNotFoundException(DEVELOPER_NOT_FOUND + id);
        }
        if(!developerDto.getProjects().isEmpty()){
            throw new InvalidOperationException(DEVELOPER_ALREADY_IN_USE);
        }
        repository.deleteById(id);
    }

    @Override
    public DeveloperDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        DeveloperDto developerDto = findById(id);
        developerDto.setWithHoldingType(withHoldingType);

        return modelMapper
                .map(repository.save(modelMapper
                        .map(developerDto, Developer.class)), DeveloperDto.class);
    }

    @Override
    public DeveloperDto updateProfile(String token, UpdateUserForm updateUserForm) {
        String email = jwtProvider.extractUsername(token);
        if (email == null) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        DeveloperDto developerDto = modelMapper.map(repository.findByEmail(email), DeveloperDto.class);
        developerDto.setFirstname(updateUserForm.getFirstname());
        developerDto.setLastname(updateUserForm.getLastname());
        developerDto.setTelNum(updateUserForm.getTelNum());
        developerDto.setAdress(updateUserForm.getTelNum());
        repository.save(modelMapper.map(developerDto, Developer.class));
        return developerDto;
    }

    @Override
    public DeveloperDto changePassword(ChangePasswordRequest request) {

        String email = jwtProvider.extractUsername(request.getToken());
        if (email == null) {
            throw new InvalidOperationException(DEVELOPER_NOT_FOUND);
        }
        Developer developer = repository.findByEmail(email);
        String currentPassword = developer.getPassword();
        if (!passwordEncoder.matches(request.getCurrentPassword(), currentPassword)) {
            throw new InvalidOperationException(CHANGE_PASSWORD_ERROR);
        }

        String newPassword = request.getNewPassword();
        String encodedPassword = passwordEncoder.encode(newPassword);
        developer.setPassword(encodedPassword);

        return modelMapper.map(repository.save(developer), DeveloperDto.class);
    }

    @Transactional
    @Override
    public ProjectManagerDto changeRoleToProjectManager(Integer idDeveloper) {
        DeveloperDto developerDto = findById(idDeveloper);
        String roleName = "Project Manager";

        ProjectManager projectManager = new ProjectManager();

        projectManager.setFirstname(developerDto.getFirstname());
        projectManager.setLastname(developerDto.getLastname());
        projectManager.setPassword(developerDto.getPassword());
        projectManager.setEmail(developerDto.getEmail());
        projectManager.setTelNum(developerDto.getTelNum());
        projectManager.setWithHoldingType(developerDto.getWithHoldingType());
        projectManager.setAdress(developerDto.getAdress());
        projectManager.setUserStatus(developerDto.getUserStatus());
        projectManager.setPresential(developerDto.getPresential());
        projectManager.setRemote(developerDto.getRemote());

        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        projectManager.setRole(role);

        deleteById(idDeveloper);

        return modelMapper.map(projectManagerRepository.save(projectManager), ProjectManagerDto.class);
    }
}
