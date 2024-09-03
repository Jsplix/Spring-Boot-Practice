package com.practice.blog.bounded_context.auth.service;

import com.practice.blog.base.exception.GlobalException;
import com.practice.blog.base.jwt.util.JwtUtil;
import com.practice.blog.bounded_context.auth.dto.SignInReqDto;
import com.practice.blog.bounded_context.auth.dto.SignInResDto;
import com.practice.blog.bounded_context.auth.dto.UserInfoDto;
import com.practice.blog.bounded_context.auth.entity.User;
import com.practice.blog.bounded_context.auth.exception.AuthExceptionCode;
import com.practice.blog.bounded_context.auth.repository.UserRepository;
import com.practice.blog.bounded_context.auth.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private String grantType = "Bearer";

    @Transactional
    public User signUp(UserInfoDto userInfoDto) {

        if (duplicateUsername(userInfoDto.username())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        User user = User.builder()
                .username(userInfoDto.username())
                .nickname(userInfoDto.nickname())
                .password(bCryptPasswordEncoder.encode(userInfoDto.password()))
                .email(userInfoDto.email())
                .role(Role.USER)
                .build();

        return userService.create(user);
    }

    @Transactional(readOnly = true)
    public SignInResDto signIn(SignInReqDto signInReqDto) {
        User user = userService.read(signInReqDto.getUsername());

        if (!bCryptPasswordEncoder.matches(signInReqDto.getPassword(), user.getPassword())) {
            throw new GlobalException(AuthExceptionCode.INVALID_PASSWORD);
        }

        String accessToken = grantType + " " + jwtUtil.generateToken(user.getId(), user.getUsername());
        String refreshToken = grantType + " " + jwtUtil.generateRefreshToken(user.getId(), user.getUsername());

        return SignInResDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private boolean duplicateUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            return user;
        }
        return null;
    }
}
