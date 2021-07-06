package com.telework.demo.repository;

import com.telework.demo.domain.entity.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectManagerRepository extends JpaRepository<ProjectManager, Integer> {
}
