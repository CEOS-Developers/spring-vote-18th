package com.sharemindteam.votesystem.auth.repository;

import com.sharemindteam.votesystem.auth.domain.Authority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(String authorityName);
}
