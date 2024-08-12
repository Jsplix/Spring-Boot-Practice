package com.practice.blog.bounded_context.user.repository;

import com.practice.blog.bounded_context.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
