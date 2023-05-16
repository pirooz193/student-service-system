package com.example.studentserviceapplication.repository;

import com.example.studentserviceapplication.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> getFoodByDate(LocalDate today);

    List<Food> findAllByDateBetween(LocalDate from, LocalDate to);
}
