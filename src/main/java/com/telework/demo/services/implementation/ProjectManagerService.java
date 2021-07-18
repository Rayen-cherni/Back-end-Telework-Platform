package com.telework.demo.services.implementation;

import com.telework.demo.configuration.securityConfiguration.jwt.JwtProvider;
import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.dto.ProjectManagerDto;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.entity.ProjectManager;
import com.telework.demo.domain.entity.Role;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IProjectManagerRepository;
import com.telework.demo.repository.IRoleRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IProjectManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class ProjectManagerService implements IProjectManagerService {

    @Autowired
    private IProjectManagerRepository repository;
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
    private IPoleManagerRepository poleManagerRepository;

    @Override
    public ProjectManagerDto save(ProjectManagerDto projectManagerDto) {
        boolean isExist = userRepository.existsByEmail(projectManagerDto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        } else {

            return modelMapper.
                    map(repository.save(modelMapper.
                            map(projectManagerDto, ProjectManager.class)), ProjectManagerDto.class);
        }
    }

    @Override
    public ProjectManagerDto findById(Integer id) {
        return repository.findById(id).map(projectManager -> modelMapper
                .map(projectManager, ProjectManagerDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id)
                );
    }

    @Override
    public List<ProjectManagerDto> findAll() {
        return repository.findAll().stream()
                .map((projectManager -> modelMapper
                        .map(projectManager, ProjectManagerDto.class))).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        ProjectManagerDto projectManagerDto = findById(id);
        if (projectManagerDto == null) {
            throw new EntityNotFoundException(PROJECT_MANAGER_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Override
    public ProjectManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        ProjectManagerDto projectManagerDto = findById(id);
        projectManagerDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(repository.save(modelMapper.map(projectManagerDto, ProjectManager.class)), ProjectManagerDto.class);
    }

    @Override
    public List<List<DeveloperDto>> getAllDevelopersByProjectManager(Integer idProjectManager) {
        // TODO refactoring
        ProjectManagerDto projectManager = findById(idProjectManager);
        List<ProjectDto> projectDtos = projectManager.getProjects();

        return projectDtos
                .stream()
                .map(ProjectDto::getDevelopers)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public ProjectManagerDto updateProfile(String token, UpdateUserForm updateUserForm) {
        String email = jwtProvider.extractUsername(token);
        if (email == null) {
            throw new InvalidOperationException(PROJECT_MANAGER_NOT_FOUND);
        }
        ProjectManagerDto projectManagerDto = modelMapper
                .map(repository.findByEmail(email), ProjectManagerDto.class);
        projectManagerDto.setFirstname(updateUserForm.getFirstname());
        projectManagerDto.setLastname(updateUserForm.getLastname());
        projectManagerDto.setTelNum(updateUserForm.getTelNum());
        projectManagerDto.setAdress(updateUserForm.getTelNum());
        repository.save(modelMapper.map(projectManagerDto, ProjectManager.class));
        return projectManagerDto;
    }

    @Override
    public ProjectManagerDto changePassword(ChangePasswordRequest request) {
        String email = jwtProvider.extractUsername(request.getToken());
        if (email == null) {
            throw new InvalidOperationException(PROJECT_NOT_FOUND);
        }
        ProjectManager projectManager = repository.findByEmail(email);
        String currentPassword = projectManager.getPassword();
        if (!passwordEncoder.matches(request.getCurrentPassword(), currentPassword)) {
            throw new InvalidOperationException(CHANGE_PASSWORD_ERROR);
        }

        String newPassword = request.getNewPassword();
        String encodedPassword = passwordEncoder.encode(newPassword);
        projectManager.setPassword(encodedPassword);

        return modelMapper.map(repository.save(projectManager), ProjectManagerDto.class);
    }

    @Transactional
    @Override
    public PoleManagerDto changeRoleToPoleManager(Integer idProjectManager) {
        ProjectManagerDto projectManagerDto = findById(idProjectManager);
        String roleName = "Pole Manager";

        PoleManager poleManager = new PoleManager();

        poleManager.setFirstname(projectManagerDto.getFirstname());
        poleManager.setLastname(projectManagerDto.getLastname());
        poleManager.setPassword(projectManagerDto.getPassword());
        poleManager.setEmail(projectManagerDto.getEmail());
        poleManager.setTelNum(projectManagerDto.getTelNum());
        poleManager.setWithHoldingType(projectManagerDto.getWithHoldingType());
        poleManager.setAdress(projectManagerDto.getAdress());
        poleManager.setUserStatus(projectManagerDto.getUserStatus());
        poleManager.setPresential(projectManagerDto.getPresential());
        poleManager.setRemote(projectManagerDto.getRemote());

        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() ->
                new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
        poleManager.setRole(role);

        delete(idProjectManager);

        return modelMapper.map(poleManagerRepository.save(poleManager), PoleManagerDto.class);
    }
}
