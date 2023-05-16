package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Food;

import java.net.MalformedURLException;
import java.util.List;

public interface FoodService {
    List<Food> getTodayFood(Integer differentDay);

    void getAllFoodsFromWeb() throws InterruptedException, MalformedURLException;

    List<Food> getThisWeekFoods(Integer from);
}
