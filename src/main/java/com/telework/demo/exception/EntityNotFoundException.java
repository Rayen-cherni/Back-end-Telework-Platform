package com.telework.demo.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private ErrorMessages errorMessages;
    @Getter
    private Integer id;

    public EntityNotFoundException(ErrorMessages errorMessage) {
        this.errorMessages = errorMessage;
    }

    public EntityNotFoundException(ErrorMessages errorMessage, Integer id) {
        this.errorMessages = errorMessage;
        this.id = id;
    }

}
