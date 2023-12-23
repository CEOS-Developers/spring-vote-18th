package com.example.springvote18th.dto.partleader.response;

import com.example.springvote18th.entity.PartLeader;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartLeaderVoteReadResponseDto {
    private Long id;
    private String name;
    private String projectName;
    private int count;

    public static PartLeaderVoteReadResponseDto from(PartLeader partLeader) {
        return PartLeaderVoteReadResponseDto.builder()
                .id(partLeader.getId())
                .name(partLeader.getName())
                .projectName(partLeader.getProjectId().getName())
                .count(partLeader.getCount())
                .build();
    }
}
