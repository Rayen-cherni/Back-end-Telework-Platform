package com.telework.demo.repository;

import com.telework.demo.domain.entity.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriqueRepository extends JpaRepository<Historique, Integer> {
}
