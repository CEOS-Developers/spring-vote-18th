package com.sharemindteam.votesystem.partLeader.application;

import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.partLeader.dto.response.PartLeaderResponse;

import java.util.List;

public interface PartLeaderService {
    List<PartLeaderResponse> getPartLeaderCandidates(String part);
    List<PartLeaderResponse> addPartLeaderVotes(Long userId, Long candidateId);
}
