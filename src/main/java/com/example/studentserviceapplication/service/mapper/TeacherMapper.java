package com.example.studentserviceapplication.service.mapper;

import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toEntity(TeacherDTO teacherDTO);

    TeacherDTO toDTO(Teacher teacher);
}
