package com.example.studentserviceapplication.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_key", nullable = false, unique = true, length = 50)
    private String key;


    public User() {

    }

    public User(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(key, user.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
