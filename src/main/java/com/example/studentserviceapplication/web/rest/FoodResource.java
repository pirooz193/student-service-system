package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.domain.Food;
import com.example.studentserviceapplication.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/food/")
public class FoodResource {

    private final FoodService foodService;

    public FoodResource(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/today")
    public ResponseEntity<List<Food>> getRequiredDateFood(@RequestParam Integer differentDay) {
        List<Food> foods = foodService.getTodayFood(differentDay);
        return ResponseEntity.ok().body(foods);
    }

    @GetMapping("/this-week")
    public ResponseEntity<List<Food>> getThisWeekFoods(@RequestParam Integer from) {
        List<Food> thisWeekFoods = foodService.getThisWeekFoods(from);
        return ResponseEntity.ok().body(thisWeekFoods);
    }

    @GetMapping("/get-food-from-web")
    public ResponseEntity<?> getAllFoodsFromWeb() throws InterruptedException, MalformedURLException {
        foodService.getAllFoodsFromWeb();
        return ResponseEntity.ok().build();
    }
}
