package com.sharemindteam.votesystem.partLeader.application;

import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.global.exception.AlreadyVotedException;
import com.sharemindteam.votesystem.global.exception.CandidateNotFoundException;
import com.sharemindteam.votesystem.global.exception.IllegalVoteException;
import com.sharemindteam.votesystem.partLeader.domain.PartLeader;
import com.sharemindteam.votesystem.partLeader.dto.response.PartLeaderResponse;
import com.sharemindteam.votesystem.partLeader.repository.PartLeaderRepository;
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
public class PartLeaderServiceImpl implements PartLeaderService {
    private final PartLeaderRepository partLeaderRepository;
    private final UserRepository userRepository;

    @Override
    public List<PartLeaderResponse> getPartLeaderCandidates(String partString) {

        Part part = Part.getPartByName(partString);

        return partLeaderRepository.findAllByPart(part, Sort.by(Sort.Direction.DESC, "votes"))
                .stream()
                .map(PartLeaderResponse::from)
                .toList();
    }

    @Transactional
    @Override
    public List<PartLeaderResponse> addPartLeaderVotes(Long userId, Long candidateId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.isVotedPart()) {
            throw new AlreadyVotedException();
        }

        PartLeader candidate = partLeaderRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        if (!user.getPart().name().equals(candidate.getPart().name())) {
            throw new IllegalVoteException(candidate.getPart());
        }

        candidate.increaseVotes();
        user.updateVotedPartLeader();

        return partLeaderRepository.findAllByPart(user.getPart(), Sort.by(Sort.Direction.DESC, "votes"))
                .stream()
                .map(PartLeaderResponse::from)
                .toList();
    }
}
