package com.telework.demo.repository;

import com.telework.demo.domain.entity.Project;
import com.telework.demo.domain.entity.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByProjectManager(ProjectManager projectManager);

    boolean existsByName(String name);
}
