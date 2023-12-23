package com.sharemindteam.votesystem.global.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "액세스 토큰과 리프레시 토큰을 담는 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TokenDto {
    @Schema(description = "Bearer헤더 붙였으니 그대로 헤더에 담아서 주시면 됩니다.", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2hsZWUzIiwiYXV0aCI6IlJPTEVfVVNFUiIsImxvZ2luSWQiOiJzb2hsZWUzIiwiZXhwIjoxNzAzMjM3NjM1fQ._Q92NctNS2sdQSRlq_FwoWJjMhVo0S-uI0bSBXXT7x6hlKePmJsYmJrZhXB12-q5NkzYh-7gkurX-dZXpezgVQ")
    private String accessToken;
    @Schema(description = "refresh헤더 붙였으니 그대로 담아서 주시면 됩니다.", example = "refresh eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MDMyMzc2MDIsImF1dGgiOiJST0xFX1VTRVIiLCJsb2dpbklkIjoic29obGVlMyJ9.348JuLGgaGVGZHT4Z-dM5MRE5knkYOc2ZLN3rTgrI_EwsVjYWTSADBweKi8Lfj7PUu37Nle0IIGpZoHODPg6BA")
    private String refreshToken;

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
