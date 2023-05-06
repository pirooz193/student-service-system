package com.example.studentserviceapplication.service.dto;

import com.example.studentserviceapplication.domain.Lesson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("rate")
    private double rate;
    @JsonProperty("comments")
    private Set<CommentDTO> comments = new HashSet<>();
    @JsonProperty("lessons")
    private List<Lesson> lessons = new ArrayList<>();

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rate=" + rate +
                ", comments=" + comments +
                ", lessons=" + lessons +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
