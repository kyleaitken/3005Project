package com.example.project.dto;

import java.time.LocalDate;

public class TrainerTrainingSessionView {
    private Integer sessionId;
    private Integer memberId;
    private String memberFirstName;
    private String memberLastName;
    private LocalDate date;
    private Integer time;

    // Getters
    public Integer getSessionId() {
        return sessionId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getTime() {
        return time;
    }

    // Setters
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
