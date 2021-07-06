package com.telework.demo.repository;

import com.telework.demo.domain.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeveloperRepository extends JpaRepository<Developer, Integer> {

    Developer findByEmail(String email);
    boolean existsByEmail(String email);

}
