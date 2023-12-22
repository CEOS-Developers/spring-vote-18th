package com.sharemindteam.votesystem.auth.application;

import com.sharemindteam.votesystem.auth.dto.request.PostLoginRequest;
import com.sharemindteam.votesystem.auth.exception.TokenExpiredException;
import com.sharemindteam.votesystem.global.dto.response.TokenDto;
import com.sharemindteam.votesystem.global.jwt.TokenProvider;
import com.sharemindteam.votesystem.user.application.UserService;
import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;
import com.sharemindteam.votesystem.user.domain.User;
import com.sharemindteam.votesystem.user.mapper.UserMapper;
import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(CreateUserRequest createUserRequest) {
        userService.validateLoginId(createUserRequest.getLoginId());
        userService.validateEmail(createUserRequest.getEmail());
        User user = userMapper.toEntity(createUserRequest);
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public TokenDto signIn(PostLoginRequest postLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                postLoginRequest.getLoginId(), postLoginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenDto reissueToken(String refreshToken) {
        String token = tokenProvider.reissueAccessToken(refreshToken);
        if (token == null) {
            throw new TokenExpiredException();
        }
        return TokenDto.builder()
                .accessToken(token)
                .build();
    }
}
