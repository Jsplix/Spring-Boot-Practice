package com.practice.blog.bounded_context.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInReqDto {
    private String username;
    private String password;
}
