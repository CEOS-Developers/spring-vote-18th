package com.sharemindteam.votesystem.partLeader.repository;

import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.partLeader.domain.PartLeader;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartLeaderRepository extends JpaRepository<PartLeader, Long> {
    List<PartLeader> findAllByPart(Part part, Sort sort);
}
