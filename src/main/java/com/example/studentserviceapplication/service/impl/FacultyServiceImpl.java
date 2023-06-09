package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.repository.FacultyRepository;
import com.example.studentserviceapplication.service.FacultyService;
import com.example.studentserviceapplication.service.dto.FacultyDTO;
import com.example.studentserviceapplication.service.mapper.FacultyMapper;
import com.example.studentserviceapplication.web.error.NotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.facultyMapper = facultyMapper;
    }

    @Override
    public FacultyDTO getFacultyByCode(String facultyCode) {
        if (facultyRepository.getFacultyByCode(facultyCode).isEmpty()) {
            throw new NotFoundException("Faculty with required code {" + facultyCode + "}");
        } else {
            Faculty faculty = facultyRepository.getFacultyByCode(facultyCode).get();
            return facultyMapper.toDTO(faculty);
        }
    }

    @Cacheable("faculties")
    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty save(Faculty teacherFaculty) {
        return facultyRepository.save(teacherFaculty);
    }
}
