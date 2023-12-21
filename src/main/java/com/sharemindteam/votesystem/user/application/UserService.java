package com.sharemindteam.votesystem.user.application;

public interface UserService {
    void validateLoginId(String loginId);
    void validateEmail(String email);
}
