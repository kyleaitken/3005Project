package com.example.project.dto;

import java.time.LocalDate;

public class FitnessClassView {
    private Integer classId;
    private LocalDate date;
    private Integer time;
    private String className;
    private String trainerName;
    private String roomName;

    
    public Integer getClassId() { return this.classId; }

    public void setClassId(Integer classId) { this.classId = classId;}

    public LocalDate getDate() { return this.date; }

    public void setDate(LocalDate date) { this.date = date;}

    public Integer getTime() {return this.time;}

    public void setTime(Integer time) { this.time = time;}

    public String getClassName() { return this.className;}

    public void setClassName(String className) { this.className = className;}

    public String getRoomName() { return this.roomName; }

    public void setRoomName(String roomName) { this.roomName = roomName;}

    public String getTrainerName() {return this.trainerName;}

    public void setTrainerName(String trainerName) { this.trainerName = trainerName;}

}
