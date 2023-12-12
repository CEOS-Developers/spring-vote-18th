package com.sharemindteam.votesystem.demoday.presentation;

import com.sharemindteam.votesystem.demoday.application.DemodayService;
import com.sharemindteam.votesystem.demoday.dto.response.DemodayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demoday")
@RequiredArgsConstructor
public class DemodayController {
    private final DemodayService demodayService;

    @GetMapping
    public ResponseEntity<List<DemodayResponse>> getDemodayCandidates() {
        return ResponseEntity.ok(demodayService.getDemodayCandidates());
    }

    @PatchMapping("/{candidateId}")
    public ResponseEntity<List<DemodayResponse>> addDemodayVotes(@RequestParam Long userId, @PathVariable Long candidateId) {
        return ResponseEntity.ok(demodayService.addDemodayVotes(userId, candidateId));
    }
}
