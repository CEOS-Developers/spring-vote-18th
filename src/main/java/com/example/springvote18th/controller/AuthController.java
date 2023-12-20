package com.example.springvote18th.controller;

import com.example.springvote18th.common.ApiResponse;
import com.example.springvote18th.dto.auth.request.AuthRequestDto;
import com.example.springvote18th.dto.auth.request.SigninRequestDto;
import com.example.springvote18th.dto.security.TokenDto;
import com.example.springvote18th.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}