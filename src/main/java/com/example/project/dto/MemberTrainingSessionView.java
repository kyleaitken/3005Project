package com.example.project.dto;

import java.time.LocalDate;

public class MemberTrainingSessionView {
    private Long sessionId;
    private Long trainerId;
    private String trainerName;
    private LocalDate date;
    private Integer time;

    // Getters
    public Long getSessionId() {
        return sessionId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getTime() {
        return time;
    }

    // Setters
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
