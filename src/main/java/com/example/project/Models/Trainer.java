package com.example.project.Models;

public class Trainer {
    private Integer trainerId;
    private String email;
    private String name;
    private String password;
    private Integer startTime;
    private Integer endTime;

    // Default constructor
    public Trainer() {
    }

    // Parameterized constructor
    public Trainer(Integer trainerId, String email, String name, String password, Integer startTime, Integer endTime) {
        this.trainerId = trainerId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public Integer getTrainerId() {
        return trainerId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    // Setters
    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}

