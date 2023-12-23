package com.sharemindteam.votesystem.demoday.repository;

import com.sharemindteam.votesystem.demoday.domain.Demoday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemodayRepository extends JpaRepository<Demoday, Long> {
}
