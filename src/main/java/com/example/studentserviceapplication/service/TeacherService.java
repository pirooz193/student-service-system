package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.service.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO getTeacherById(long id);

    List<TeacherDTO> getAllTeachers();

    List<Teacher> getAllTeachersFromWeb();

    TeacherDTO save(TeacherDTO teacher);
}
