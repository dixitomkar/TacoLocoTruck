package com.example.order.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderError extends RuntimeException {
    public InvalidOrderError(String message) {
        super(message);
    }
}

