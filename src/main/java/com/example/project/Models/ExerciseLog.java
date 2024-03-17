package com.example.project.Models;

public class ExerciseLog {
    private Long logId;
    private Long routineId;
    private String exerciseName;
    private Integer numSets;
    private Integer numReps;
    private Integer duration;
    private Integer weight;

    public ExerciseLog() {}

    public ExerciseLog(Long logId, Long routineId, String exerciseName, Integer numSets, Integer numReps, Integer duration, Integer weight) {
        this.logId = logId;
        this.routineId = routineId;
        this.exerciseName = exerciseName;
        this.numSets = numSets;
        this.numReps = numReps;
        this.duration = duration;
        this.weight = weight;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Integer getNumSets() {
        return numSets;
    }

    public void setNumSets(Integer numSets) {
        this.numSets = numSets;
    }

    public Integer getNumReps() {
        return numReps;
    }

    public void setNumReps(Integer numReps) {
        this.numReps = numReps;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}

