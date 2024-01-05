package com.sharemindteam.votesystem.email.application;

public interface EmailService {
    void sendVerificationCode(String email);

    void verifyCode(String email, String code);
}
