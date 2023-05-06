package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.service.dto.FacultyDTO;

import java.util.List;

public interface FacultyService {
    FacultyDTO getFacultyByCode(String facultyCode);

    List<Faculty> getAll();

    Faculty save(Faculty teacherFaculty);
}
