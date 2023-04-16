package com.example.studentserviceapplication.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "code", nullable = false, length = 10)
    private String code;
    @Column(name = "title_fa", nullable = false, length = 30)
    private String persianTitle;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Teacher> teachers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && title.equals(faculty.title) && code.equals(faculty.code) && teachers.equals(faculty.teachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, code, teachers);
    }

    @Override
    public String toString() {
        return "Faculty{" +
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getPersianTitle() {
        return persianTitle;
    }

    public void setPersianTitle(String persianTitle) {
        this.persianTitle = persianTitle;
    }
}
