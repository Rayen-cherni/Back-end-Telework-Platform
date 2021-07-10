package com.telework.demo.services;

import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.domain.entity.enumeration.Decision;
import com.telework.demo.domain.model.CreateHistoriqueForm;

import java.util.List;

public interface IHistoriqueService {

    HistoriqueDto save(CreateHistoriqueForm historiqueForm);

    HistoriqueDto findById(Integer id);

    List<HistoriqueDto> findAll();

    void delete(Integer id);

    HistoriqueDto updateDecisionProjectManager(Integer idHistorique, Integer idDeveloper, Decision decision);

    HistoriqueDto updateDecisionPoleManager(Integer idHistorique, Integer idDeveloper, Decision decision);

}
