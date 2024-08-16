package com.practice.blog.bounded_context.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignInResDto {
    private String accessToken;
    private String refreshToken;
}
