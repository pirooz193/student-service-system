package com.example.studentserviceapplication.service.dto;

import com.example.studentserviceapplication.domain.Comment;
import com.example.studentserviceapplication.domain.Lesson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("comments")
    private List<Comment> comments;
    @JsonProperty("lessons")
    private List<Lesson> lessons;

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "fullName='" + fullName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", comments=" + comments +
                ", lessons=" + lessons +
                '}';
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
