package com.sharemindteam.votesystem.auth.presentation;

import com.sharemindteam.votesystem.auth.application.AuthService;
import com.sharemindteam.votesystem.auth.dto.request.PostLoginRequest;
import com.sharemindteam.votesystem.auth.dto.request.PostTokenRequest;
import com.sharemindteam.votesystem.global.dto.response.TokenDto;
import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller", description = "Auth 컨트롤러")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signUp")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserRequest createUserRequest) {
        authService.signUp(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("signIn")
    public TokenDto signIn(@RequestBody PostLoginRequest postLoginRequest) {
        return authService.signIn(postLoginRequest);
    }

    @PostMapping
    public TokenDto reissueToken(@RequestBody PostTokenRequest postTokenRequest) {
        return authService.reissueToken(postTokenRequest.getRefreshToken());
    }
}
