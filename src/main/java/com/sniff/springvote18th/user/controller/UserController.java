package com.sniff.springvote18th.user.controller;

import com.sniff.springvote18th.user.dto.request.EmailDto;
import com.sniff.springvote18th.user.dto.request.LoginDto;
import com.sniff.springvote18th.user.dto.request.LoginIdDto;
import com.sniff.springvote18th.user.dto.request.SignUpDto;
import com.sniff.springvote18th.user.dto.response.LoginResponseDto;
import com.sniff.springvote18th.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/loginId")
    public ResponseEntity<Void> checkLoginId(@RequestBody LoginIdDto loginIdDto) {
        return userService.checkLoginId(loginIdDto);
    }

    @PostMapping("/email")
    public ResponseEntity<Void> checkEmail(@RequestBody EmailDto emailDto) {
        return userService.checkEmail(emailDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
