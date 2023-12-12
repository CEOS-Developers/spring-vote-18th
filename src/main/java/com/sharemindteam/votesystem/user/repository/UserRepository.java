package com.sharemindteam.votesystem.user.repository;

import com.sharemindteam.votesystem.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
