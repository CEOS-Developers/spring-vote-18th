package com.sharemindteam.votesystem.demoday.application;

import com.sharemindteam.votesystem.demoday.domain.Demoday;
import com.sharemindteam.votesystem.demoday.dto.response.DemodayResponse;
import com.sharemindteam.votesystem.demoday.repository.DemodayRepository;
import com.sharemindteam.votesystem.global.exception.AlreadyVotedException;
import com.sharemindteam.votesystem.global.exception.CandidateNotFoundException;
import com.sharemindteam.votesystem.global.exception.IllegalVoteException;
import com.sharemindteam.votesystem.user.domain.User;
import com.sharemindteam.votesystem.user.exception.UserNotFoundException;
import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DemodayServiceImpl implements DemodayService {
    private final DemodayRepository demodayRepository;
    private final UserRepository userRepository;

    @Override
    public List<DemodayResponse> getDemodayCandidates() {
        return demodayRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"))
                .stream()
                .map(DemodayResponse::from)
                .toList();
    }

    @Transactional
    @Override
    public List<DemodayResponse> addDemodayVotes(Long userId, Long candidateId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.isVotedDemoday()) {
            throw new AlreadyVotedException();
        }

        Demoday candidate = demodayRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        if (user.getTeam().name().equals(candidate.getTeam().name())) {
            throw new IllegalVoteException(user.getTeam());
        }

        candidate.increaseVotes();
        user.updateVotedDemoday();

        return demodayRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"))
                .stream()
                .map(DemodayResponse::from)
                .toList();
    }
}
