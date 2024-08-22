package com.practice.blog.bounded_context.auth.exception;

import com.practice.blog.base.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

public enum AuthExceptionCode implements ExceptionCode {
    EMPTY_AUTHORIZATION(HttpStatus.BAD_REQUEST, "인증 값이 비어있습니다."),
    MISMATCH_TOKEN_TYPE(HttpStatus.BAD_REQUEST, "토큰 유형이 일치하지 않습니다."),

    WRONG_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 값이 유효하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 정보가 잘못되었습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");


    HttpStatus httpStatus;
    String cause;

    AuthExceptionCode(HttpStatus httpStatus, String cause) {
        this.httpStatus = httpStatus;
        this.cause = cause;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCause() {
        return cause;
    }

    @Override
    public String getName() {
        return name();
    }
}
