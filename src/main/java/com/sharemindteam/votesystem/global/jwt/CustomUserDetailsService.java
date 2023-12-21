package com.sharemindteam.votesystem.global.jwt;

import com.sharemindteam.votesystem.user.domain.User;
import com.sharemindteam.votesystem.user.exception.LoginIdAlreadyExistsException;
import com.sharemindteam.votesystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(LoginIdAlreadyExistsException::new);//todo:userNotFoundException만들어서 고쳐야함
        System.out.println("user 찾음 : " + user.getPassword());
        return new CustomUserDetails(user);
    }
}
