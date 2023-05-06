package com.example.studentserviceapplication.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "teacher_table")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;
    @Column(name = "image_url", nullable = false, length = 100)
    private String imageUrl;
    @Column(name = "rate", nullable = false, length = 5, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double rate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Comment> comments;
    @ManyToMany
    private List<Lesson> lessons;

    public Teacher(long id) {
        this.id = id;
    }

    public Teacher() {
        comments = new HashSet<>();
        lessons = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return fullName.equals(teacher.fullName) && imageUrl.equals(teacher.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, imageUrl, comments, lessons);
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
