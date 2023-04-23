package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Rating;

public interface RatingService {
    void addRating(Long teacherId, int score, String userId);

    double calculateAverageRating(Long teacherId);

    Rating saveRating(String userId, Long teacherId, int score);
}
