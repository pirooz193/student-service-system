package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {
    Campus getCampusByName(String name);
}
