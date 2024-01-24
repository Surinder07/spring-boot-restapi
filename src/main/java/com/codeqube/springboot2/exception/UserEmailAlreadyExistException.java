package com.codeqube.springboot2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserEmailAlreadyExistException extends RuntimeException{
    private String message;

    public UserEmailAlreadyExistException(String message) {
        this.message = message;
    }
}
