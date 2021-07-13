package com.telework.demo.repository;

import com.telework.demo.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String name);

    boolean existsByRoleName(String name);
}
