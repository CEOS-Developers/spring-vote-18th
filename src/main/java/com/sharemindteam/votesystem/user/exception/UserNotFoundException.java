package com.sharemindteam.votesystem.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("user 정보를 찾을 수 없습니다. : " + userId);
    }
}
