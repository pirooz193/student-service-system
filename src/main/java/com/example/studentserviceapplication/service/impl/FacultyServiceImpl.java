package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.repository.FacultyRepository;
import com.example.studentserviceapplication.service.FacultyService;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository ;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty getFacultyByCode(String facultyCode) {
        return  facultyRepository.getFacultyByCode(facultyCode) ;
    }
}
