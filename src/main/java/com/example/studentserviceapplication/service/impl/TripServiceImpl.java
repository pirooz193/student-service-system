package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Campus;
import com.example.studentserviceapplication.domain.Trip;
import com.example.studentserviceapplication.repository.TripRepository;
import com.example.studentserviceapplication.service.CampusService;
import com.example.studentserviceapplication.service.TripService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final CampusService campusService;

    public TripServiceImpl(TripRepository tripRepository, CampusService campusService) {
        this.tripRepository = tripRepository;
        this.campusService = campusService;
    }

    @Override
    public List<Trip> getTripsForRequiredLocations(String origin, String destination) {
        Campus originCampus = campusService.getRequiredCampusByName(origin);
        Campus destinationCampus = campusService.getRequiredCampusByName(destination);
        return tripRepository.findAllByOriginCampusAndDestinationCampus(originCampus, destinationCampus);
    }
}
