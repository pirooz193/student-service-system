package com.example.studentserviceapplication.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teacher_table")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name", nullable = false , length = 30)
    private String fullName;
    @Column(name = "image_url", nullable = false , length = 100)
    private String imageUrl;
    @ManyToOne
    private Faculty faculty;
    @OneToMany( fetch = FetchType.EAGER)
    private List<Comment> comments;
    @ManyToMany
    private List<Lesson> lessons;

    public Teacher() {
        comments = new ArrayList<>();
        lessons = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return fullName.equals(teacher.fullName) && imageUrl.equals(teacher.imageUrl) &&  faculty.equals(teacher.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, imageUrl, faculty, comments, lessons);
    }

    public Teacher(String fullName, String imageUrl) {
        this.fullName = fullName;
        this.imageUrl = "https://shahroodut.ac.ir/fa"+imageUrl;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", faculty=" + faculty +
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
