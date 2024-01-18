package com.codeqube.springboot2.repository;

import com.codeqube.springboot2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
