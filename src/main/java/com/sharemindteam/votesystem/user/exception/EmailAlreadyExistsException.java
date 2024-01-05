package com.sharemindteam.votesystem.user.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("이미 존재하는 email 입니다.");
    }
}
