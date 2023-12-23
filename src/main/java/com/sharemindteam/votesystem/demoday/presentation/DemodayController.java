package com.sharemindteam.votesystem.demoday.presentation;

import com.sharemindteam.votesystem.demoday.application.DemodayService;
import com.sharemindteam.votesystem.demoday.dto.response.DemodayResponse;
import com.sharemindteam.votesystem.global.jwt.CustomUserDetails;
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

@CrossOrigin
@Tag(name = "Demoday Controller", description = "데모데이 투표 컨트롤러")
@RestController
@RequestMapping("/demoday")
@RequiredArgsConstructor
public class DemodayController {
    private final DemodayService demodayService;

    @Operation(summary = "데모데이 투표 후보 조회", description = "데모데이 투표 후보 조회를 요청합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "후보 조회 성공, 득표순으로 내림차순 정렬")
    })
    @GetMapping
    public ResponseEntity<List<DemodayResponse>> getDemodayCandidates() {
        return ResponseEntity.ok(demodayService.getDemodayCandidates());
    }

    @Operation(summary = "데모데이 투표", description = "데모데이 투표를 요청합니다. 요청 형식: /demoday/{candidateId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "투표 성공, 득표순으로 내림차순 정렬"),
            @ApiResponse(responseCode = "400", description = "1. 이미 투표권을 행사함\n 2. 본인 팀에 투표함"),
            @ApiResponse(responseCode = "404", description = "1. 존재하지 않는 회원 아이디로 요청됨\n 2. 존재하지 않는 후보 아이디로 요청됨")
    })
    @Parameters({
            @Parameter(name = "candidateId", description = "데모데이 후보 아이디")
    })
    @PatchMapping("/{candidateId}")
    public ResponseEntity<List<DemodayResponse>> addDemodayVotes(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long candidateId) {
        return ResponseEntity.ok(demodayService.addDemodayVotes(customUserDetails.getUserId(), candidateId));
    }
}
