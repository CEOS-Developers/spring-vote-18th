package com.sharemindteam.votesystem.auth.application;

import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;
import com.sharemindteam.votesystem.user.domain.User;
import com.sharemindteam.votesystem.user.mapper.UserMapper;
import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void signUp(CreateUserRequest createUserRequest) {
        userRepository.save(userMapper.toEntity(createUserRequest));
    }
}
