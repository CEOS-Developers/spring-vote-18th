package com.ceos.vote.exception;

import lombok.Getter;

@Getter
public class CeosException extends RuntimeException {

    private int status;
    private String message;
    private String solution;

    public CeosException(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getHttpStatus().value();
        this.solution = errorCode.getSolution();
    }

    public CeosException(ErrorCode errorCode, String message) {
        this.message = message;
        this.status = errorCode.getHttpStatus().value();
        this.solution = errorCode.getSolution();
    }

    public CeosException(ErrorCode errorCode, String message, String solution) {
        this.message = message;
        this.status = errorCode.getHttpStatus().value();
        this.solution = solution;
    }

}
