package com.sharemindteam.votesystem.user.application;

import com.sharemindteam.votesystem.user.dto.response.GetUserResponse;

public interface UserService {
    void validateLoginId(String loginId);

    void validateEmail(String email);

    GetUserResponse getUser(Long userId);
}
