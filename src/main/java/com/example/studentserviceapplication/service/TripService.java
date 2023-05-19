package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Trip;

import java.util.List;

public interface TripService {
    List<Trip> getTripsForRequiredLocations(String origin, String destination);
}
