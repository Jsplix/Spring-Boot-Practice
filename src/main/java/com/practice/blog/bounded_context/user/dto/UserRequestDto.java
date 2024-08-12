package com.practice.blog.bounded_context.user.dto;

import com.practice.blog.bounded_context.user.entity.User;

public record UserRequestDto(
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
