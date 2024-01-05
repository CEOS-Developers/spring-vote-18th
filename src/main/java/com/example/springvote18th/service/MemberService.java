package com.example.springvote18th.service;

import com.example.springvote18th.dto.member.response.InfoResponseDto;
import com.example.springvote18th.entity.Member;
import com.example.springvote18th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public InfoResponseDto getInfoByUsername(String username) {
		Optional<Member> findMember= memberRepository.findByUsername(username);
		if (findMember.isEmpty()) {
			throw new RuntimeException("존재하지 않는 유저입니다");
		}
		Member member = findMember.get();

		return InfoResponseDto.from(member);
	}
}
