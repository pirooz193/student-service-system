package com.example.studentserviceapplication.domain;

import com.example.studentserviceapplication.domain.enumuration.MealType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "foods")
public class Food implements Comparable<Food> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "meal", nullable = false, length = 20)
    private MealType mealType;
    @Column(name = "date", nullable = false, length = 20)
    private LocalDate date;

    public Food() {
    }

    public Food(String title, MealType mealType) {
        this.title = title;
        this.mealType = mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id && Objects.equals(title, food.title) && mealType == food.mealType && Objects.equals(date, food.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, mealType, date);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mealType=" + mealType +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Food food) {
        if (food.getDate().isAfter(this.date) || food.getDate().isEqual(this.date)) {
            return 1;
        } else
            return 0;
    }
}
