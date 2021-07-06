package com.telework.demo.services;

import com.telework.demo.domain.dto.HistoriqueDto;

import java.util.List;

public interface IHistoriqueService {

    HistoriqueDto save(HistoriqueDto dto);

    HistoriqueDto findById(Integer id);

    List<HistoriqueDto> findAll();

    void deleteById(Integer id);
}
