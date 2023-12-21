package com.sharemindteam.votesystem.user.exception;

public class LoginIdAlreadyExistsException extends RuntimeException {
    public LoginIdAlreadyExistsException() {
        super("이미 존재하는 loginId 입니다.");
    }
}
