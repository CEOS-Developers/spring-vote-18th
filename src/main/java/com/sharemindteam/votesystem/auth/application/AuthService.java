package com.sharemindteam.votesystem.auth.application;

import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;

public interface AuthService {
    void signUp(CreateUserRequest createUserRequest);
}
