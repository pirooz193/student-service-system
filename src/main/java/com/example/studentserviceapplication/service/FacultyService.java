package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Faculty;

public interface FacultyService {
    Faculty getFacultyByCode(String facultyCode);
}
