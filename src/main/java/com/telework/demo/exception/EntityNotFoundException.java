package com.telework.demo.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private String errorMessages;


    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessages = errorMessage;
    }


}
