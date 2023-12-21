package com.sharemindteam.votesystem.auth.dto.request;

import lombok.Getter;

@Getter
public class PostLoginRequest {
    private String loginId;
    private String password;
}
