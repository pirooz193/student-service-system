package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Campus;
import com.example.studentserviceapplication.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByOriginCampusAndDestinationCampus(Campus origin, Campus destination);
}
