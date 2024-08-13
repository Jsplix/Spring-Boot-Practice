package com.practice.blog.bounded_context.auth.service;

import com.practice.blog.bounded_context.auth.dto.UserInfoDto;
import com.practice.blog.bounded_context.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUp(UserInfoDto userInfoDto) {
        User user = User.builder()
                .username(userInfoDto.username())
                .nickname(userInfoDto.nickname())
                .password(bCryptPasswordEncoder.encode(userInfoDto.password()))
                .email(userInfoDto.email()).build();

        return userService.create(user);
    }
}
