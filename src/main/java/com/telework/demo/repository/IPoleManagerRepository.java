package com.telework.demo.repository;

import com.telework.demo.domain.entity.PoleManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPoleManagerRepository extends JpaRepository<PoleManager, Integer> {

    boolean existsByEmail(String email);

    PoleManager findByEmail(String email);
}
