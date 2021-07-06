package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(UserDto::fromEntity).collect(Collectors.toList());
    }
}
