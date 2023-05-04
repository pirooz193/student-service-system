package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Food;

import java.util.List;

public interface FoodService {
    List<Food> getTodayFood();

    void getAllFoodsFromWeb() throws InterruptedException;
}
