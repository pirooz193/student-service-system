package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Rating;
import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.domain.User;
import com.example.studentserviceapplication.repository.RatingRepository;
import com.example.studentserviceapplication.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void addRating(Long teacherId, int score, String userId) {
        Rating rating = new Rating();
        rating.setTeacher(new Teacher(teacherId));
        rating.setScore(score);
        rating.setUser(new User(userId));
        ratingRepository.save(rating);
    }

    public double calculateAverageRating(Long teacherId) {
        return ratingRepository.findAverageRatingByTeacherId(teacherId);
    }

    @Override
    public Rating saveRating(String userId, Long teacherId, int score) {
//        return ratingRepository.save(rating);
        return null;
    }
}
