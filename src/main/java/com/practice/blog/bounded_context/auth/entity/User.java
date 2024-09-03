package com.practice.blog.bounded_context.auth.entity;

import com.practice.blog.base.entity.BaseTime;
import com.practice.blog.bounded_context.auth.type.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Role role;

    @Builder
    public User(String username, String nickname, String password, String email, Role role) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void update(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : role.getValue().split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
}
