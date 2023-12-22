package com.sharemindteam.votesystem.email.application;

import com.sharemindteam.votesystem.email.exception.InvalidCodeException;
import com.sharemindteam.votesystem.email.exception.CodeAlreadyExistsException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final RedisTemplate<String, String> redisTemplate;
    private final JavaMailSender mailSender;
    private static final int EXPIRATION_TIME = 3 * 60; // 3분으로 설정

    private void checkExistingCode(String email) {
        String existingCode = redisTemplate.opsForValue().get(email);
        if (existingCode != null) {
            throw new CodeAlreadyExistsException();
        }
    }

    public void sendVerificationCode(String email) {
        checkExistingCode(email);
        String code = createVerificationCode();
        sendEmail(email, code);
        storeCodeInRedis(email, code);
    }

    private void storeCodeInRedis(String email, String code) {
        redisTemplate.opsForValue().set(email, code, EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    private String createVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(900000) + 100000; // 100000~999999 범위로 숫자 생성
        return String.valueOf(code);
    }

    public void verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        if (!code.equals(storedCode)) {
            throw new InvalidCodeException(code);
        }
    }

    private void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("ceos vote 인증 메일입니다.");
        message.setText(text);
        mailSender.send(message);
    }
}
