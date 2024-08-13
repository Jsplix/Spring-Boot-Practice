package com.practice.blog.bounded_context.auth.dto;

import com.practice.blog.bounded_context.auth.entity.User;

public record UserInfoDto(
        String username,
        String nickname,
        String password,
        String email
) {
    public User toEntity() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .email(email)
                .build();
    }
}
