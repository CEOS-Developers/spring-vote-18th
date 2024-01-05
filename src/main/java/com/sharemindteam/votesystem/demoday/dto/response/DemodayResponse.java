package com.sharemindteam.votesystem.demoday.dto.response;

import com.sharemindteam.votesystem.demoday.domain.Demoday;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DemodayResponse {
    private final Long candidateId;
    private final String team;

    @Builder
    public DemodayResponse(Long candidateId, String team) {
        this.candidateId = candidateId;
        this.team = team;
    }

    public static DemodayResponse from(Demoday demoday) {
        return DemodayResponse.builder()
                .candidateId(demoday.getDemodayId())
                .team(demoday.getTeam().name())
                .build();
    }
}
