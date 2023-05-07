package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty , Long> {
    Optional<Faculty> getFacultyByCode(String facultyCode);
}
