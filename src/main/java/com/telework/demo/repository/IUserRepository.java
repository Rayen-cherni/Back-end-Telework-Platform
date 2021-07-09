package com.telework.demo.repository;

import com.telework.demo.domain.entity.User;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);

    boolean existsByEmail(String email);

    Optional<User> findByUserStatus(UserStatus userStatus);
}
