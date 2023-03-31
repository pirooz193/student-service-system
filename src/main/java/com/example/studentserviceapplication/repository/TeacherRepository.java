package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher , Long> {

}
