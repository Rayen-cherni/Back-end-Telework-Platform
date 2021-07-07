package com.telework.demo.repository;

import com.telework.demo.domain.entity.Pole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPoleRepository extends JpaRepository<Pole, Integer> {

    Optional<Pole> findById(Integer id);

    Pole findByName(String name);

    boolean existsByName(String name);
}
