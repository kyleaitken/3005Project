package com.example.project.Models;

import java.time.LocalDate;

public class MemberGoal {
    private Long goalId;
    private Long memberId;
    private String description;
    private LocalDate targetDate;
    private boolean completed;
    private LocalDate completedDate;

    // Constructors
    public MemberGoal() {
    }

    public MemberGoal(Long goalId, Long memberId, String description, LocalDate targetDate, boolean completed, LocalDate completedDate) {
        this.goalId = goalId;
        this.memberId = memberId;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = completed;
        this.completedDate = completedDate;
    }

    // Getters and Setters
    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }
}
