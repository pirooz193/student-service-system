package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Campus;
import com.example.studentserviceapplication.repository.CampusRepository;
import com.example.studentserviceapplication.service.CampusService;
import org.springframework.stereotype.Service;

@Service
public class CampusServiceImpl implements CampusService {

    private final CampusRepository campusRepository;

    public CampusServiceImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public Campus getRequiredCampusByName(String name) {
        return campusRepository.getCampusByName(name);
    }
}
