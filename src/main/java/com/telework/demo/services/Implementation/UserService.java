package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.entity.User;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IUserRepository;
import com.telework.demo.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDto findById(Integer id) {

        return repository.findById(id).map((user -> modelMapper.map(user, UserDto.class))).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND + id)
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
    public UserDto updateStatus(Integer id, UserStatus userStatus) {
        //FIXME
        //TODO refactoring is necessary is this case
        //UserDto userDto = modelMapper.map(repository.findById(id).get(), UserDto.class);

        if (repository.findById(id).isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND);
        }
        User user = repository.findById(id).get();
        user.setUserStatus(userStatus);

        return modelMapper.map(repository.save(modelMapper.map(user, User.class)), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUserByUserStatus(UserStatus userStatus) {

        return repository.findByUserStatus(userStatus).stream().map(user ->
                modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }

}
