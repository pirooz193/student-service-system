package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.domain.Trip;
import com.example.studentserviceapplication.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripResource {

    private final TripService tripService;

    public TripResource(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam String origin, @RequestParam String destination) {
        List<Trip> trips = tripService.getTripsForRequiredLocations(origin, destination);
        return ResponseEntity.ok().body(trips);
    }
}
