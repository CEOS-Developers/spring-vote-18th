package com.sharemindteam.votesystem.partLeader.presentation;

import com.sharemindteam.votesystem.partLeader.application.PartLeaderService;
import com.sharemindteam.votesystem.partLeader.dto.response.PartLeaderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partLeader")
@RequiredArgsConstructor
public class PartLeaderController {
    private final PartLeaderService partLeaderService;

    @GetMapping
    public ResponseEntity<List<PartLeaderResponse>> getPartLeaderCandidates(@RequestParam String part) {
        return ResponseEntity.ok(partLeaderService.getPartLeaderCandidates(part));
    }

    @PatchMapping("/{candidateId}")
    public ResponseEntity<List<PartLeaderResponse>> addPartLeaderVotes(@RequestParam Long userId, @PathVariable Long candidateId) {
        return ResponseEntity.ok(partLeaderService.addPartLeaderVotes(userId, candidateId));
    }
}
