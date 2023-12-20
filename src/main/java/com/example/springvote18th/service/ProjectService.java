package com.example.springvote18th.service;

import com.example.springvote18th.dto.project.request.ProjectVoteRequestDto;
import com.example.springvote18th.dto.project.response.ProjectReadResponseDto;
import com.example.springvote18th.dto.project.response.ProjectVoteReadResponseDto;
import com.example.springvote18th.entity.Member;
import com.example.springvote18th.entity.MemberProject;
import com.example.springvote18th.entity.Project;
import com.example.springvote18th.repository.MemberProjectRepository;
import com.example.springvote18th.repository.MemberRepository;
import com.example.springvote18th.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final MemberProjectRepository memberProjectRepository;

    public List<ProjectReadResponseDto> findAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectReadResponseDto> projectReadResponseList = projectList.stream()
                .map(project -> ProjectReadResponseDto.from(project))
                .collect(Collectors.toList());
        return projectReadResponseList;
    }

    public List<ProjectVoteReadResponseDto> findAllProjectVotes() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectVoteReadResponseDto> projectVoteReadResponses = projectList.stream()
                .map(project -> ProjectVoteReadResponseDto.from(project))
                .collect(Collectors.toList());
        return projectVoteReadResponses;
    }

    @Transactional
    public void postProjectVote(ProjectVoteRequestDto projectVoteRequest, User user) {
        Member member = memberRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        if (!member.getIsProjectVoted()) {
//            projectVoteRequest.getProjectIdList().stream().forEach(arr -> {
//                Project project = projectRepository.findById(arr.getProjectId())
//                        .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다."));
//
//                project.updateCount();
//
//                memberProjectRepository.save(MemberProject.builder()
//                                .member(member)
//                                .project(project)
//                                .build());
//
//                projectRepository.save(project);
//            });

            Project project = projectRepository.findById(projectVoteRequest.getProjectId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다."));

            project.updateCount();
            member.updateIsProjectVoted();

            memberProjectRepository.save(MemberProject.builder()
                            .member(member)
                            .project(project)
                            .build());
            projectRepository.save(project);
            memberRepository.save(member);
        } else {
            throw new IllegalArgumentException("이미 투표를 했습니다");
        }

        return;
    }
}
