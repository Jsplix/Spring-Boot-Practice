package com.practice.blog.bounded_context.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

}
