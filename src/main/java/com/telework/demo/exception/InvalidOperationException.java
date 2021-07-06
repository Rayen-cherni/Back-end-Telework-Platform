package com.telework.demo.exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {

    @Getter
    private ErrorMessages errorMessages;

    public InvalidOperationException(ErrorMessages errorMessage) {
        this.errorMessages = errorMessage;
    }
}
