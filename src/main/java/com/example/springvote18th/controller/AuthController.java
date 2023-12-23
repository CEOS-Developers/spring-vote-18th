package com.example.springvote18th.controller;

import com.example.springvote18th.common.ApiResponse;
import com.example.springvote18th.dto.auth.request.AuthRequestDto;
import com.example.springvote18th.dto.auth.request.EmailMessage;
import com.example.springvote18th.dto.auth.request.EmailRequestDto;
import com.example.springvote18th.dto.auth.request.SigninRequestDto;
import com.example.springvote18th.dto.security.TokenDto;
import com.example.springvote18th.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<TokenDto> signup(@RequestBody AuthRequestDto authRequestDto) {
        log.info("유저 회원가입하기");
        return ApiResponse.createSuccess(authService.signup(authRequestDto));
    }

    @PostMapping("/signin")
    public ApiResponse<TokenDto> signin(@RequestBody SigninRequestDto signinRequestDto) {
        log.info("유저 로그인하기");
        return ApiResponse.createSuccess(authService.signin(signinRequestDto));
    }

    @PostMapping("/email")
    public ApiResponse sendMessage(@RequestBody EmailRequestDto emailRequestDto) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailRequestDto.getEmail())
                .subject("[REDDI] 이메일 인증을 위한 인증 코드 발송")
                .build();

        authService.sendEmail(emailMessage);

        return ApiResponse.createSuccessWithNoContent();
    }
}