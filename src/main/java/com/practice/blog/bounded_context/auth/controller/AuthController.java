package com.practice.blog.bounded_context.auth.controller;

import com.practice.blog.base.jwt.util.JwtUtil;
import com.practice.blog.bounded_context.auth.dto.SignInReqDto;
import com.practice.blog.bounded_context.auth.dto.SignInResDto;
import com.practice.blog.bounded_context.auth.dto.UserInfoDto;
import com.practice.blog.bounded_context.auth.service.AuthService;
import com.practice.blog.bounded_context.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserInfoDto userInfoDto) {
        authService.signUp(userInfoDto);
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResDto> signIn(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }
}
