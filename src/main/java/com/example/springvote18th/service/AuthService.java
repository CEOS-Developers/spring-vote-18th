package com.example.springvote18th.service;

import com.example.springvote18th.dto.auth.request.AuthRequestDto;
import com.example.springvote18th.dto.auth.request.SigninRequestDto;
import com.example.springvote18th.dto.security.TokenDto;
import com.example.springvote18th.entity.Member;
import com.example.springvote18th.repository.MemberRepository;
import com.example.springvote18th.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto signup(AuthRequestDto authRequestDto) {
        Optional<Member> findUser = memberRepository.findByUsername(authRequestDto.getUsername());
        if (findUser.isPresent()) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = authRequestDto.toMember(passwordEncoder);

        memberRepository.save(member);

        SigninRequestDto signinRequestDto = SigninRequestDto.builder()
                .username(authRequestDto.getUsername())
                .password(authRequestDto.getPassword())
                .build();

        return signin(signinRequestDto);
    }

    @Transactional
    public TokenDto signin(SigninRequestDto signinRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = signinRequestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.createAccessToken(authentication);
    }
}
