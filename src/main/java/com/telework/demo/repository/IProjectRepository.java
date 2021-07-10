package com.telework.demo.repository;

import com.telework.demo.domain.entity.Developer;
import com.telework.demo.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findById(Integer id);

    //List<Project> findByDevelopers(List<Developer> developers);

    boolean existsByName(String name);
}
