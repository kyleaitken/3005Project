package com.example.project.dto;

import java.time.LocalDate;

public class TrainingSessionRequest {
    private Integer trainerId;
    private LocalDate date;
    private Integer time;

    // Getters and Setters
    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}