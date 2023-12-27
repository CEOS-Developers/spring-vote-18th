package com.sharemindteam.votesystem.user.application;

import com.sharemindteam.votesystem.user.domain.User;
import com.sharemindteam.votesystem.user.dto.response.GetUserResponse;
import com.sharemindteam.votesystem.user.exception.EmailAlreadyExistsException;
import com.sharemindteam.votesystem.user.exception.LoginIdAlreadyExistsException;
import com.sharemindteam.votesystem.user.exception.UserNotFoundException;
import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void validateLoginId(String loginId) {
        if (userRepository.findByLoginId(loginId).isPresent()) {
            throw new LoginIdAlreadyExistsException();
        }
    }

    public void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
    }

    public GetUserResponse getUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return GetUserResponse.from(user);
    }
}
