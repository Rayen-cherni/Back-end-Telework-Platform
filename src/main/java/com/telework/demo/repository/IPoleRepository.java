package com.telework.demo.repository;

import com.telework.demo.domain.entity.Pole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPoleRepository extends JpaRepository<Pole, Integer> {

    boolean existsByName(String email);
}
