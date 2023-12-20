package com.example.springvote18th.controller;

import com.example.springvote18th.common.ApiResponse;
import com.example.springvote18th.dto.project.request.ProjectVoteRequestDto;
import com.example.springvote18th.dto.project.response.ProjectReadResponseDto;
import com.example.springvote18th.dto.project.response.ProjectVoteReadResponseDto;
import com.example.springvote18th.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ApiResponse<List<ProjectReadResponseDto>> findAllProjects() {
        return ApiResponse.createSuccess(projectService.findAllProjects());
    }

    // 데모데이 결과
    @GetMapping("/result")
    public ApiResponse<List<ProjectVoteReadResponseDto>> findAllProjectVotes() {
        return ApiResponse.createSuccess(projectService.findAllProjectVotes());
    }

    // 데모데이 투표
    @PostMapping
    public ApiResponse postProjectVote(@RequestBody ProjectVoteRequestDto projectVoteRequest, @AuthenticationPrincipal User user) {

        projectService.postProjectVote(projectVoteRequest, user);

        return ApiResponse.createSuccessWithNoContent();
    }

}
