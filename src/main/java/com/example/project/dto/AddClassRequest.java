package com.example.project.dto;

import java.time.LocalDate;

public class AddClassRequest {
    private LocalDate date;
    private Integer time;
    private String roomName;
    private String trainerName;
    private String className;

    public LocalDate getDate() { return date;}
    public Integer getTime() {return time;}
    public String getRoomName() {return roomName;}
    public String getTrainerName() {return trainerName;}
    public String getClassName() {return className;}

    public void setDate(LocalDate date) { this.date = date;}
    public void setTime(Integer time) {this.time = time;}
    public void setRoomName(String room) {this.roomName = room;}
    public void setTrainerName(String trainer) {this.trainerName = trainer;}
    public void setClassName(String className) {this.className = className;}
}