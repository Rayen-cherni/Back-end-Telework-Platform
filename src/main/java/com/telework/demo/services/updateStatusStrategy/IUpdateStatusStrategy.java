package com.telework.demo.services.updateStatusStrategy;

import com.telework.demo.domain.entity.enumeration.UserStatus;

public interface IUpdateStatusStrategy<T> {
    T updateStatus(Integer id, UserStatus userStatus);
}
