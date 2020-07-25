package com.solution.shorturl.exception;

import org.springframework.http.HttpStatus;

public enum ErrorModel {

    URL_INVALID(HttpStatus.BAD_REQUEST, "URL_INVALID"),

    CODE_TAKEN(HttpStatus.CONFLICT, "CODE_TAKEN"),

    CODE_INVALID(HttpStatus.BAD_REQUEST, "CODE_INVALID"),

    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "CODE_NOT_FOUND"),

    SECRET_WRONG(HttpStatus.UNAUTHORIZED, "SECRET_WRONG");

    private HttpStatus httpStatus;
    private String errorCode;

    ErrorModel(HttpStatus httpStatus, String errorCode) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return String.valueOf(errorCode);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
