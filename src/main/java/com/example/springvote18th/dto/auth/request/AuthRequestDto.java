package com.example.springvote18th.dto.auth.request;

import com.example.springvote18th.entity.Member;
import com.example.springvote18th.entity.enums.Part;
import com.example.springvote18th.entity.enums.Team;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
public class AuthRequestDto {
    private String username;
    private String password;
    private String name;
    private String email;
    private Team teamName;
    private Part part;
    private Boolean isVerified;

    public Member toMember(PasswordEncoder passwordEncoder)  {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .teamName(teamName)
                .part(part)
                .isVerified(isVerified)
                .build();
    }
}