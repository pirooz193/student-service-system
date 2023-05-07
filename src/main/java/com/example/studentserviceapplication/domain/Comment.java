package com.example.studentserviceapplication.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 20)
    private String title;
    @Column(name = "score", nullable = false, length = 10)
    private float score;
    @Column(name = "content", nullable = false, length = 2000)
    private String content;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "positive_interactions", length = 10)
    private int positiveInteractions;
    @Column(name = "negative_interactions", length = 10)
    private int negativeInteractions;

    public Comment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Float.compare(comment.score, score) == 0 && positiveInteractions == comment.positiveInteractions && negativeInteractions == comment.negativeInteractions && id.equals(comment.id) && title.equals(comment.title) && content.equals(comment.content) && dateTime.equals(comment.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, score, content, dateTime, positiveInteractions, negativeInteractions);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", positiveInteractions=" + positiveInteractions +
                ", negativeInteractions=" + negativeInteractions +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPositiveInteractions() {
        return positiveInteractions;
    }

    public void setPositiveInteractions(int positiveInteractions) {
        this.positiveInteractions = positiveInteractions;
    }

    public int getNegativeInteractions() {
        return negativeInteractions;
    }

    public void setNegativeInteractions(int negativeInteractions) {
        this.negativeInteractions = negativeInteractions;
    }
}
