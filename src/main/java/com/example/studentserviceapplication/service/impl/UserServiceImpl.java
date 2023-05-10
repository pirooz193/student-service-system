package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.User;
import com.example.studentserviceapplication.repository.UserRepository;
import com.example.studentserviceapplication.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByKey(String key) {
        if (userRepository.findUserByKey(key).isEmpty()) {
            User user = new User(key);
            userRepository.save(user);
            return user;
        } else return userRepository.findUserByKey(key).get();
    }
}
