package com.sharemindteam.votesystem.auth.application;

import com.sharemindteam.votesystem.auth.dto.request.PostLoginRequest;
import com.sharemindteam.votesystem.global.dto.response.TokenDto;
import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;

public interface AuthService {
    void signUp(CreateUserRequest createUserRequest);

    TokenDto signIn(PostLoginRequest postLoginRequest);
}
