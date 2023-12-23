package com.sharemindteam.votesystem.auth.presentation;

import com.sharemindteam.votesystem.auth.application.AuthService;
import com.sharemindteam.votesystem.auth.dto.request.PostLoginRequest;
import com.sharemindteam.votesystem.auth.dto.request.PostTokenRequest;
import com.sharemindteam.votesystem.global.dto.response.TokenDto;
import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "Auth Controller", description = "Auth 컨트롤러")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 이메일 혹은 로그인 아이디")
    })
    @PostMapping("signUp")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserRequest createUserRequest) {
        authService.signUp(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "로그인", description = "로그인 성공 시 토큰 반환")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 실패"),
    })
    @PostMapping("signIn")
    public TokenDto signIn(@RequestBody PostLoginRequest postLoginRequest) {
        return authService.signIn(postLoginRequest);
    }

    @Operation(summary = "accessToken 재발급", description = "accessToken 만료 시 refreshToken으로 accessToken 재발급")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "403", description = "refreshToken 만료된 경우"),
    })
    @PostMapping
    public TokenDto reissueToken(@RequestBody PostTokenRequest postTokenRequest) {
        return authService.reissueToken(postTokenRequest.getRefreshToken());
    }
}
