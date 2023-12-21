package com.sharemindteam.votesystem.partLeader.presentation;

import com.sharemindteam.votesystem.global.jwt.CustomUserDetails;
import com.sharemindteam.votesystem.partLeader.application.PartLeaderService;
import com.sharemindteam.votesystem.partLeader.dto.response.PartLeaderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Part Leader Controller", description = "파트장 투표 컨트롤러")
@RestController
@RequestMapping("/partLeader")
@RequiredArgsConstructor
public class PartLeaderController {
    private final PartLeaderService partLeaderService;

    @Operation(summary = "파트장 투표 후보 조회", description = "파트장 투표 후보 조회를 요청합니다. 요청 형식: /partLeader?part={part}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "후보 조회 성공, 득표순으로 내림차순 정렬")
    })
    @Parameters({
            @Parameter(name = "part", description = "BACKEND, FRONTEND")
    })
    @GetMapping
    public ResponseEntity<List<PartLeaderResponse>> getPartLeaderCandidates(@RequestParam String part) {
        return ResponseEntity.ok(partLeaderService.getPartLeaderCandidates(part));
    }

    @Operation(summary = "파트장 투표", description = "파트장 투표를 요청합니다. 요청 형식: /partLeader/{candidateId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "투표 성공, 득표순으로 내림차순 정렬"),
            @ApiResponse(responseCode = "400", description = "1. 이미 투표권을 행사함\n 2. 다른 파트에 투표함"),
            @ApiResponse(responseCode = "404", description = "1. 존재하지 않는 회원 아이디로 요청됨\n 2. 존재하지 않는 후보 아이디로 요청됨")
    })
    @Parameters({
            @Parameter(name = "candidateId", description = "파트장 후보 아이디")
    })
    @PatchMapping("/{candidateId}")
    public ResponseEntity<List<PartLeaderResponse>> addPartLeaderVotes(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long candidateId) {
        return ResponseEntity.ok(partLeaderService.addPartLeaderVotes(customUserDetails.getUserId(), candidateId));
    }
}
