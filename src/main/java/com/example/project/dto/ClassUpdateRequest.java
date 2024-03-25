package com.example.project.dto;

import java.time.LocalDate;

public class ClassUpdateRequest {
    private String className;
    private LocalDate date;
    private Integer time;
    private Integer roomId;
    private Integer trainerId;

    public LocalDate getDate() { return date;}
    public Integer getTime() {return time;}
    public Integer getRoomId() {return roomId;}
    public String getClassName() { return className;}
    public Integer getTrainerId() {return trainerId;}


    public void setDate(LocalDate date) { this.date = date;}
    public void setTime(Integer time) {this.time = time;}
    public void setRoomId(Integer id) {this.roomId = id;}
    public void setClassName(String className) {this.className = className;}
    public void setTrainerId(Integer id) {trainerId = id;}
}
