package com.telework.demo.repository;

import com.telework.demo.domain.entity.PoleManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPoleManagerRepository extends JpaRepository<PoleManager, Integer> {

    Optional<PoleManager> findById(Integer id);

    PoleManager findByEmail(String email);

    boolean existsByEmail(String email);
}
