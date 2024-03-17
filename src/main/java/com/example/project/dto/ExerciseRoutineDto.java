package com.example.project.dto;

import java.util.List;

public class ExerciseRoutineDto {
    private Long routineId;
    private List<ExerciseDetail> exercises;

    public ExerciseRoutineDto(Long routineId, List<ExerciseDetail> exercises) {
        this.routineId = routineId;
        this.exercises = exercises;
    }


    // Getters and Setters
    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public List<ExerciseDetail> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDetail> exercises) {
        this.exercises = exercises;
    }


    public static class ExerciseDetail {
        private Long logId;
        private String exerciseName;
        private Integer numSets;
        private Integer numReps;
        private Integer duration;
        private Integer weight;

        public ExerciseDetail(Long logId, String exerciseName, Integer numSets, Integer numReps, Integer duration, Integer weight) {
            this.logId = logId;
            this.exerciseName = exerciseName;
            this.numSets = numSets;
            this.numReps = numReps;
            this.duration = duration;
            this.weight = weight;
        }

        public Long getLogId() {
            return logId;
        }

        public String getExerciseName() {
            return exerciseName;
        }
    
        public Integer getNumSets() {
            return numSets;
        }
    
        public Integer getNumReps() {
            return numReps;
        }
    
        public Integer getDuration() {
            return duration;
        }
    
        public Integer getWeight() {
            return weight;
        }
    
        // Setters (if you need to modify the properties after creation)
        public void setExerciseName(String exerciseName) {
            this.exerciseName = exerciseName;
        }
    
        public void setNumSets(Integer numSets) {
            this.numSets = numSets;
        }
    
        public void setNumReps(Integer numReps) {
            this.numReps = numReps;
        }
    
        public void setDuration(Integer duration) {
            this.duration = duration;
        }
    
        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        // Getters and Setters
    }
}