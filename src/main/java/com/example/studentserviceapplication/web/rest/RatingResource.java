package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.service.RatingService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rating")
public class RatingResource {

    private final RatingService ratingService;
    private static final String USER_ID_COOKIE_NAME = "userId";

    public RatingResource(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/teachers/{teacherId}/ratings")
    public void submitRating(@CookieValue(name = USER_ID_COOKIE_NAME, required = false) String userId,
                             @PathVariable Long teacherId,
                             @RequestParam int score,
                             HttpServletResponse response) {

        // Generate a new user ID if it doesn't exist in the cookie
        if (userId == null) {
            userId = UUID.randomUUID().toString();
            Cookie userIdCookie = new Cookie(USER_ID_COOKIE_NAME, userId);
            userIdCookie.setMaxAge(30 * 60); // Expires in 30 minutes
            response.addCookie(userIdCookie);
        }

        // Save the rating using the RatingService
        ratingService.saveRating(userId, teacherId, score);
    }
}
