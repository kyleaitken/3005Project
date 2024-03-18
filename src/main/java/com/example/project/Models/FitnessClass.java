package com.example.project.Models;

import java.sql.Date;

public class FitnessClass {
    private Integer classId;
    private Integer trainerId;
    private Date date;
    private Integer time;
    private String name;
    private Integer roomId;

    // Default constructor
    public FitnessClass() {
    }

    // Parameterized constructor
    public FitnessClass(Integer classId, Integer trainerId, Date date, Integer time, String name, Integer roomId) {
        this.classId = classId;
        this.trainerId = trainerId;
        this.date = date;
        this.time = time;
        this.name = name;
        this.roomId = roomId;
    }

    // Getters
    public Integer getClassId() {
        return classId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public Date getDate() {
        return date;
    }

    public Integer getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Integer getRoomId() {
        return roomId;
    }

    // Setters
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
