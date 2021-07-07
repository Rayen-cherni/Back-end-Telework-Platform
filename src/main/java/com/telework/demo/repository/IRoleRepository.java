package com.telework.demo.repository;

import com.telework.demo.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String name);

    boolean existsByRoleName(String name);
}
