package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Food;

import java.net.MalformedURLException;
import java.util.List;

public interface FoodService {
    List<Food> getTodayFood();

    void getAllFoodsFromWeb() throws InterruptedException, MalformedURLException;
}
