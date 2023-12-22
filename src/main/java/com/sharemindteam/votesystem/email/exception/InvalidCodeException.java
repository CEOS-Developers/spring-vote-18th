package com.sharemindteam.votesystem.email.exception;

public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException(String code) {
        super("잘못된 코드입니다 " + code);
    }
}
