package com.sharemindteam.votesystem.demoday.application;

import com.sharemindteam.votesystem.demoday.dto.response.DemodayResponse;

import java.util.List;

public interface DemodayService {
    List<DemodayResponse> getDemodayCandidates();
    List<DemodayResponse> addDemodayVotes(Long userId, Long candidateId);
}
