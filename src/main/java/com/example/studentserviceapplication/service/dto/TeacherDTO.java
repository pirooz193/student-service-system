package com.example.studentserviceapplication.service.dto;

import com.example.studentserviceapplication.domain.Lesson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("comments")
    private List<CommentDTO> comments = new ArrayList<>();
    @JsonProperty("lessons")
    private List<Lesson> lessons = new ArrayList<>();

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
