package com.example.project.Controllers; 

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.Services.ExerciseRoutineService;
import com.example.project.Services.MemberGoalService;
import com.example.project.dto.ExerciseRoutineDto;

import org.springframework.http.ResponseEntity;

import com.example.project.Models.ExerciseLog;
import com.example.project.Models.MemberExerciseRoutine;
import com.example.project.Models.MemberGoal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/members/{memberId}/routines")
public class ExerciseRoutineController {

    private final ExerciseRoutineService exerciseRoutineService;

    @Autowired
    public ExerciseRoutineController(ExerciseRoutineService exerciseRoutineService) {
        this.exerciseRoutineService = exerciseRoutineService;
    }


    @PostMapping("/add")
	public ResponseEntity<?> addExerciseRoutine(@PathVariable Long memberId) {
        exerciseRoutineService.save(memberId);
        return ResponseEntity.ok().build();
	}

    // get member's exercise routines
	@GetMapping
	public ResponseEntity<?> getMemberExerciseRoutines(@PathVariable Long memberId) {
		List<MemberExerciseRoutine> exerciseRoutines = exerciseRoutineService.findMemberRoutines(memberId);
        return ResponseEntity.ok(exerciseRoutines); 
    }

    // get routines and exercises

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseRoutineDto>> getRoutinesAndExercises(@PathVariable Long memberId) {
        List<ExerciseRoutineDto> routines = exerciseRoutineService.findRoutinesAndExercises(memberId);
        return ResponseEntity.ok(routines);
    }

    // delete routine
    @DeleteMapping("/{routineId}")
    public ResponseEntity<?> deleteMemberGoalById(@PathVariable Long routineId) {
        exerciseRoutineService.deleteRoutine(routineId);
        return ResponseEntity.ok().build();
    }


    // add an exerciseLog to a routine

    @PostMapping("/{routineId}/exercises")
    public ResponseEntity<?> addExerciseLogToRoutine(@PathVariable Long memberId, @PathVariable Long routineId, @RequestBody ExerciseLog exerciseLog) {
        exerciseLog.setRoutineId(routineId);
        exerciseRoutineService.addExerciseLogToRoutine(exerciseLog);
        return ResponseEntity.ok().build();
    }

    // delete an exercise log
    @DeleteMapping("/{logId}")
    public ResponseEntity<?> deleteExerciseLog(@PathVariable Long logId) {
        exerciseRoutineService.deleteExerciseLog(logId);
        return ResponseEntity.ok().build();
    }

    // updateExerciseLog
    @PutMapping("/{logId}")
    public ResponseEntity<?> updateExerciseLog(@PathVariable Long logId, @RequestBody ExerciseLog updatedExerciseLog) {
        exerciseRoutineService.updateExerciseLog(logId, updatedExerciseLog);
        return ResponseEntity.ok(updatedExerciseLog); 
    }



}