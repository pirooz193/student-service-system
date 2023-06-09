package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFullName(String fullName);

    @Query("SELECT AVG(t.rate) FROM Teacher t WHERE t.id = :teacherId")
    double findAverageRatingByTeacherId(@Param("teacherId") Long teacherId);
}
