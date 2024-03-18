package com.example.project.Models;

import java.time.LocalDate;

public class TrainingSession {
    private Long sessionId;
    private Long trainerId;
    private Long memberId;
    private LocalDate date;
    private Integer time;

    // Default constructor
    public TrainingSession() {
    }

    // Parameterized constructor for creating an instance with all fields
    public TrainingSession(Long sessionId, Long trainerId, Long memberId, LocalDate date, Integer time) {
        this.sessionId = sessionId;
        this.trainerId = trainerId;
        this.memberId = memberId;
        this.date = date;
        this.time = time;
    }

    // Getters
    public Long getSessionId() {
        return sessionId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public Long getMemberId() {
        return memberId;
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

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
