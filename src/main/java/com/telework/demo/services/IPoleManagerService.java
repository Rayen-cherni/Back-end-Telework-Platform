package com.telework.demo.services;

import com.telework.demo.domain.dto.PoleManagerDto;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import com.telework.demo.domain.model.ChangePasswordRequest;
import com.telework.demo.domain.model.UpdateUserForm;

import java.util.List;

public interface IPoleManagerService {

    PoleManagerDto save(PoleManagerDto dto);

    PoleManagerDto findById(Integer id);

    List<PoleManagerDto> findAll();

    void delete(Integer id);

    String updatePole(Integer idPoleManager, Integer idPole);

    void deletePole(Integer idPoleManager);

    PoleManagerDto updateWithHoldingStatus(Integer id, WithHoldingType withHoldingType);

    PoleManagerDto updateProfile(String token, UpdateUserForm updateUserForm);

    PoleManagerDto changePassword(ChangePasswordRequest request);
}
