package com.sharemindteam.votesystem.user.repository;

import com.sharemindteam.votesystem.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByEmail(String email);
}
