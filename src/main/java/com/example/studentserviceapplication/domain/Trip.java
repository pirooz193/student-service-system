package com.example.studentserviceapplication.domain;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "origin_camous_id")
    private Campus originCampus;

    @ManyToOne
    @JoinColumn(name = "destination_camous_id")
    private Campus destinationCampus;

    @Column(name = "start_time", nullable = false, length = 30)
    private LocalTime startTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id && Objects.equals(originCampus, trip.originCampus) && Objects.equals(destinationCampus, trip.destinationCampus) && Objects.equals(startTime, trip.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originCampus, destinationCampus, startTime);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", originCampus=" + originCampus +
                ", destinationCampus=" + destinationCampus +
                ", startTime=" + startTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Campus getOriginCampus() {
        return originCampus;
    }

    public void setOriginCampus(Campus originCampus) {
        this.originCampus = originCampus;
    }

    public Campus getDestinationCampus() {
        return destinationCampus;
    }

    public void setDestinationCampus(Campus destinationCampus) {
        this.destinationCampus = destinationCampus;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
