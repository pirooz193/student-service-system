package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.repository.FacultyRepository;
import com.example.studentserviceapplication.service.FacultyService;
import com.example.studentserviceapplication.service.dto.FacultyDTO;
import com.example.studentserviceapplication.service.mapper.FacultyMapper;
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
        Faculty faculty = facultyRepository.getFacultyByCode(facultyCode);
        return facultyMapper.toDTO(faculty);
    }

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }
}
