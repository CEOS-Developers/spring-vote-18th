package com.sharemindteam.votesystem.auth.dto.request;

import lombok.Getter;

@Getter
public class PostTokenRequest {
    private String refreshToken;
}
