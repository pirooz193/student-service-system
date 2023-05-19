package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Campus;

public interface CampusService {
    Campus getRequiredCampusByName(String origin);
}
