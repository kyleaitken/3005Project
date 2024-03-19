package com.example.project.dto;

import java.time.LocalDate;

public class ClassUpdateRequest {
    private LocalDate date;
    private Integer time;

    public LocalDate getDate() { return date;}
    public Integer getTime() {return time;}

    public void setDate(LocalDate date) { this.date = date;}
    public void setTime(Integer time) {this.time = time;}
}
