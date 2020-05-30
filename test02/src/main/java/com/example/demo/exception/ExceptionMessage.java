package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    NAVER_API_UNAUTHORIZED("네이버 오픈 API 통신 중 인증 에러가 발생하였습니다."),
    NAVER_API_ERROR("네이버 오픈 API 통신 중 알수 없는 에러가 발생하였습니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

}
