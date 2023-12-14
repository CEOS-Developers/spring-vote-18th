package com.sharemindteam.votesystem.partLeader.dto.response;

import com.sharemindteam.votesystem.partLeader.domain.PartLeader;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PartLeaderResponse {
    private final Long candidateId;
    private final String name;
    private final String team;

    @Builder
    public PartLeaderResponse(Long candidateId, String name, String team) {
        this.candidateId = candidateId;
        this.name = name;
        this.team = team;
    }

    public static PartLeaderResponse from(PartLeader partLeader) {
        return PartLeaderResponse.builder()
                .candidateId(partLeader.getPartLeaderId())
                .name(partLeader.getName())
                .team(partLeader.getTeam().name())
                .build();
    }
}
