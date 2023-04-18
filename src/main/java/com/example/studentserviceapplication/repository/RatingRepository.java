package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.teacher.id = :teacherId")
    double findAverageRatingByTeacherId(@Param("teacherId") Long teacherId);

}
