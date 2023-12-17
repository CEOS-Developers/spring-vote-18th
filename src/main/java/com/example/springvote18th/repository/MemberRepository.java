package com.example.springvote18th.repository;

import com.example.springvote18th.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
