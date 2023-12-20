package com.sharemindteam.votesystem.user.application;

public interface UserService {
    boolean validateLoginId(String loginId);
    boolean validateEmail(String email);
}
