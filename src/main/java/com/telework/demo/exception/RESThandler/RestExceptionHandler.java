package com.telework.demo.exception.RESThandler;

import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static com.telework.demo.exception.ErrorMessages.AUTHENTICATION_USER_NOT_VALID;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception) {

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidOperationException exception) {

        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;


        final ErrorDto errorDto = ErrorDto.builder()
                //.message(exception.getCause().toString())
                .message(AUTHENTICATION_USER_NOT_VALID)
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }



}
