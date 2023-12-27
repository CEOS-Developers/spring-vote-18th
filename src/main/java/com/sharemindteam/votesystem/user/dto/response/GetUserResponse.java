package com.sharemindteam.votesystem.user.dto.response;

import com.sharemindteam.votesystem.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserResponse {
    private final String name;
    private final String part;
    private final String team;

    @Builder
    public GetUserResponse(String name, String part, String team) {
        this.name = name;
        this.part = part;
        this.team = team;
    }

    public static GetUserResponse from(User user) {
        return GetUserResponse.builder()
                .name(user.getName())
                .part(user.getPart().name())
                .team(user.getTeam().name())
                .build();
    }
}
