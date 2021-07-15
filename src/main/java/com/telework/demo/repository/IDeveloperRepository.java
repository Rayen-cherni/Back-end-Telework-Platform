package com.telework.demo.repository;

import com.telework.demo.domain.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDeveloperRepository extends JpaRepository<Developer, Integer> {

    boolean existsByEmail(String email);

}
