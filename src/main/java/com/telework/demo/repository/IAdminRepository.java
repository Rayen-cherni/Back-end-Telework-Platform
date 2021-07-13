package com.telework.demo.repository;

import com.telework.demo.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    boolean existsByEmail(String email);
}
