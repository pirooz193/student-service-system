package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.User;

public interface UserService {
    User getUserByKey(String userKey);
}
