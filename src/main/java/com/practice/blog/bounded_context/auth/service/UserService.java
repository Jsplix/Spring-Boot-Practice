package com.practice.blog.bounded_context.auth.service;

import com.practice.blog.bounded_context.auth.dto.UserInfoDto;
import com.practice.blog.bounded_context.auth.entity.User;
import com.practice.blog.bounded_context.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User read(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public User read(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }

    public User update(Long userId, UserInfoDto userInfoDto) {
        User user = read(userId);
        user.update(userInfoDto.nickname(), bCryptPasswordEncoder.encode(userInfoDto.password()));
        return user;
    }

    public void delete(Long userId) {
        userRepository.delete(read(userId));
    }
}
