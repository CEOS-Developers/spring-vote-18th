package com.sharemindteam.votesystem.partLeader.repository;

import com.sharemindteam.votesystem.partLeader.domain.PartLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartLeaderRepository extends JpaRepository<PartLeader, Long> {
}
