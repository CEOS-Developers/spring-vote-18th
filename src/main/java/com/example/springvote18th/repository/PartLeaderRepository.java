package com.example.springvote18th.repository;

import com.example.springvote18th.entity.Member;
import com.example.springvote18th.entity.PartLeader;
import com.example.springvote18th.entity.enums.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartLeaderRepository extends JpaRepository<PartLeader, Long> {
    List<PartLeader> findAllByPart(Part part);
}
