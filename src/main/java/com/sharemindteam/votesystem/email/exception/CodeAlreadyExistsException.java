package com.sharemindteam.votesystem.email.exception;

public class CodeAlreadyExistsException extends RuntimeException {
    public CodeAlreadyExistsException() {
        super("아직 만료되지 않은 코드가 남아있습니다.");
    }
}
