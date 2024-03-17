package com.example.project.Models;

public class MemberExerciseRoutine {
    private Long routineId;
    private Long memberId;

    public MemberExerciseRoutine() {}

    public MemberExerciseRoutine(Long routineId, Long memberId) {
        this.routineId = routineId;
        this.memberId = memberId;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
