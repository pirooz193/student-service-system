package com.example.studentserviceapplication.service.mapper;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.service.dto.FacultyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TeacherMapper.class)
public interface FacultyMapper {
    Faculty toEntity(FacultyDTO facultyDTO);

    FacultyDTO toDTO(Faculty faculty);
}
