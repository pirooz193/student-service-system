package com.example.studentserviceapplication.service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    @JsonProperty(namespace = "id")
    private Long id;
    @JsonProperty(namespace = "title")
    private String title;
    @JsonProperty(namespace = "score")
    private float score;
    @JsonProperty(namespace = "content")
    private String content;
    @JsonProperty(namespace = "time")
    private LocalDateTime dateTime;
    @JsonProperty(namespace = "num_of_positive_interactions")
    private int positiveInteractions;
    @JsonProperty(namespace = "num_of_negative_interactions")
    private int negativeInteractions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Float.compare(that.score, score) == 0 && positiveInteractions == that.positiveInteractions && negativeInteractions == that.negativeInteractions && id.equals(that.id) && title.equals(that.title) && content.equals(that.content) && dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, score, content, dateTime, positiveInteractions, negativeInteractions);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", positiveInteractions=" + positiveInteractions +
                ", negativeInteractions=" + negativeInteractions +
                '}';
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
