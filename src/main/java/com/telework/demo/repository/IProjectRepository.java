package com.telework.demo.repository;

import com.telework.demo.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Integer> {

    Project findByName(String name);

    boolean existsByName(String name);
}
