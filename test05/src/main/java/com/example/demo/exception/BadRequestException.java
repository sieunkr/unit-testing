package com.example.demo.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    public BadRequestException(ExceptionMessage message) {
        super(message.getMessage());
    }
}