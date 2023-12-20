package com.sharemindteam.votesystem.user.application;

import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public boolean validateLoginId(String loginId) {
        return userRepository.findByLoginId(loginId).isPresent();
    }

    public boolean validateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
