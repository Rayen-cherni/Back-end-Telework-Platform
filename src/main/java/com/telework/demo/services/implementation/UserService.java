package com.telework.demo.services.implementation;

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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_EMAIL;
import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;
    @Autowired
    private ModelMapper modelMapper;

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

        Optional<User> optionalUser = repository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_BY_EMAIL);
        }
        User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }

    /*
        @Override
        public UserDto updateStatus(Integer id, UserStatus userStatus) {
            //TODO Impl strategy pattern using roleName as context!
            if (repository.findById(id).isEmpty()) {
                throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
            }
            UserDto userDto = modelMapper.map(repository.findById(id).get(), UserDto.class);

            System.out.println(userDto);

            if (userDto.getWithHoldingType() != WithHoldingType.NONE) {
                throw new InvalidOperationException(USER_OUT_OF_SERVICE);
            }
            userDto.setUserStatus(userStatus);
            User s = modelMapper.map(userDto, User.class);
            repository.save(s);
            return userDto;
        }
     */
    @Override
    public List<UserDto> getAllUserByUserStatus(UserStatus userStatus) {

        return repository.findByUserStatus(userStatus).stream().map(user ->
                modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }

}
