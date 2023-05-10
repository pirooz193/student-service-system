package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByKey(String key);
}
