package com.sharemindteam.votesystem.global.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TokenDto {
    private String accessToken;

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
