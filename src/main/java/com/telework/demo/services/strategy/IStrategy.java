package com.telework.demo.services.strategy;

import com.telework.demo.domain.model.CreateUserForm;

public interface IStrategy<T> {
    T createUser(CreateUserForm userForm);
}
