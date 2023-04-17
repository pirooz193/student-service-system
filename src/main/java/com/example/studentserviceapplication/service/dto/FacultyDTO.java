package com.example.studentserviceapplication.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyDTO {

    @JsonProperty("id")
    private long id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "title_fa")
    private String persianTitle;
    @JsonProperty(value = "teachers")
    private Set<TeacherDTO> teachers;

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", persianTitle='" + persianTitle + '\'' +
                ", teachers=" + teachers +
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherDTO> teachers) {
        this.teachers = teachers;
    }

    public String getPersianTitle() {
        return persianTitle;
    }

    public void setPersianTitle(String persianTitle) {
        this.persianTitle = persianTitle;
    }
}
