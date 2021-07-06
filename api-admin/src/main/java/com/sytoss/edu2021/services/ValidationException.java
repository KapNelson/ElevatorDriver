package com.sytoss.edu2021.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
