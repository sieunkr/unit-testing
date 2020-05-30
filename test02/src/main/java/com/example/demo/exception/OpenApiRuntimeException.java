package com.example.demo.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "오픈API 통신 에러")
@NoArgsConstructor
public class OpenApiRuntimeException extends RuntimeException {

    public OpenApiRuntimeException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
