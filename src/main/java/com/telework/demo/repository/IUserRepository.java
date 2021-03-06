package com.telework.demo.repository;

import com.telework.demo.domain.entity.User;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByUserStatus(UserStatus userStatus);
}
