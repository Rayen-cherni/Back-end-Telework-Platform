package com.telework.demo.repository;

import com.telework.demo.domain.dto.ProjectDto;
import com.telework.demo.domain.entity.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProjectManagerRepository extends JpaRepository<ProjectManager, Integer> {

    Optional<ProjectManager> findById(Integer id);

}
