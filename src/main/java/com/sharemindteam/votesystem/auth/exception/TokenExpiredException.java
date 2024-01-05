package com.sharemindteam.votesystem.auth.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("만료된 토큰입니다.");
    }
}
