package com.telework.demo.services.implementation;

import com.telework.demo.configuration.securityConfiguration.jwt.JwtProvider;
import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.Pole;
import com.telework.demo.domain.entity.PoleManager;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IPoleManagerRepository;
import com.telework.demo.repository.IPoleRepository;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IPoleManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.*;

@Service
public class PoleManagerService implements IPoleManagerService {

    @Autowired
    private IPoleManagerRepository repository;

    @Autowired
    private IPoleRepository poleRepository;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public PoleManagerDto save(PoleManagerDto dto) {
        boolean isExist = userRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new InvalidOperationException(USER_ALREADY_EXISTS);
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PoleManager.class)), PoleManagerDto.class);
    }

    @Override
    public PoleManagerDto findById(Integer id) {
        return repository.findById(id).map(poleManager -> modelMapper
                .map(poleManager, PoleManagerDto.class))
                .orElseThrow(
                        () -> new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id)
                );
    }

    @Override
    public List<PoleManagerDto> findAll() {
        return repository.findAll().stream()
                .map(poleManager -> modelMapper
                        .map(poleManager, PoleManagerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        PoleManagerDto poleManagerDto = findById(id);
        if (poleManagerDto == null) {
            throw new EntityNotFoundException(POLE_MANAGER_NOT_FOUND + id);
        }
        if (poleManagerDto.getPole() != null) {
            throw new InvalidOperationException(POLE_MANAGER_ALREADY_IN_USE);
        }
        repository.deleteById(id);
    }

    @Override
    public String updatePole(Integer idPoleManager, Integer idPole) {
        Optional<PoleManager> optionalPoleManager = repository.findById(idPoleManager);
        Optional<Pole> optionalPole = poleRepository.findById(idPole);
        if (optionalPoleManager.isEmpty()) {
            throw new EntityNotFoundException(POLE_MANAGER_NOT_FOUND);
        }
        if (optionalPole.isEmpty()) {
            throw new EntityNotFoundException(POLE_NOT_FOUND);
        }
        PoleManager poleManager = optionalPoleManager.get();
        Pole pole = optionalPole.get();
        poleManager.setPole(pole);
        repository.save(poleManager);
        return poleManager.getPole().getName();
    }

    //FIXME
    @Override
    public void deletePole(Integer idPoleManager) {
        PoleManagerDto poleManagerDto = findById(idPoleManager);
        System.out.println("****************************");
        System.out.println(poleManagerDto.getPole().getId());
        if (poleManagerDto.getPole().getId() !=null) {
            System.out.println("*****************************");
            poleManagerDto.setPole(null);
            repository.save(modelMapper.map(poleManagerDto, PoleManager.class));
        }
    }

    @Override
    public PoleManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType) {
        PoleManagerDto poleManagerDto = findById(id);
        poleManagerDto.setWithHoldingType(withHoldingType);

        return modelMapper.map(repository.save(modelMapper.map(poleManagerDto, PoleManager.class)), PoleManagerDto.class);
    }

    @Transactional
    @Override
    public PoleManagerDto updateProfile(String token, UpdateUserForm updateUserForm) {
        String email = jwtProvider.extractUsername(token);
        if (email == null) {
            throw new InvalidOperationException(POLE_MANAGER_NOT_FOUND);
        }
        PoleManagerDto poleManagerDto = modelMapper
                .map(repository.findByEmail(email), PoleManagerDto.class);
        poleManagerDto.setFirstname(updateUserForm.getFirstname());
        poleManagerDto.setLastname(updateUserForm.getLastname());
        poleManagerDto.setTelNum(updateUserForm.getTelNum());
        poleManagerDto.setAdress(updateUserForm.getTelNum());
        repository.save(modelMapper.map(poleManagerDto, PoleManager.class));
        return poleManagerDto;
    }

    @Override
    public PoleManagerDto changePassword(ChangePasswordRequest request) {
        String email = jwtProvider.extractUsername(request.getToken());
        if (email == null) {
            throw new InvalidOperationException(POLE_MANAGER_NOT_FOUND);
        }
        PoleManager poleManager = repository.findByEmail(email);
        String currentPassword = poleManager.getPassword();
        if (!passwordEncoder.matches(request.getCurrentPassword(), currentPassword)) {
            throw new InvalidOperationException(CHANGE_PASSWORD_ERROR);
        }

        String newPassword = request.getNewPassword();
        String encodedPassword = passwordEncoder.encode(newPassword);
        poleManager.setPassword(encodedPassword);

        return modelMapper.map(repository.save(poleManager), PoleManagerDto.class);
    }
}
