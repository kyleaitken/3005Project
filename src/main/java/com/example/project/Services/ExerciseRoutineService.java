package com.example.project.Services;

import com.example.project.Models.ExerciseLog;
import com.example.project.Models.MemberExerciseRoutine;
import com.example.project.Repos.ExerciseLogRepository;
import com.example.project.Repos.ExerciseRoutineRepository;
import com.example.project.dto.ExerciseRoutineDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseRoutineService {
    private final ExerciseRoutineRepository exerciseRoutineRepository;
    private final ExerciseLogRepository exerciseLogRepository;

    @Autowired
    public ExerciseRoutineService(ExerciseRoutineRepository exerciseRoutineRepository, ExerciseLogRepository exerciseLogRepository) {
        this.exerciseRoutineRepository = exerciseRoutineRepository;
        this.exerciseLogRepository = exerciseLogRepository;
    }

    public ResponseEntity<?> save(Long memberId) {
        MemberExerciseRoutine routine = new MemberExerciseRoutine();
        routine.setMemberId(memberId);
        boolean routineAdded = exerciseRoutineRepository.save(routine);
        if (routineAdded) return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Failed\"}");
    }

    public List<MemberExerciseRoutine> findMemberRoutines(Long memberId) {
        return exerciseRoutineRepository.findMemberRoutines(memberId);
    }

    public ResponseEntity<?> deleteRoutine(Long routineId) {
        boolean routineDeleted = exerciseRoutineRepository.deleteRoutine(routineId);
        if (routineDeleted) return ResponseEntity.ok().body("Success");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed");
    }

    public List<ExerciseRoutineDto> findRoutinesAndExercises(Long memberId) {
        return exerciseRoutineRepository.findRoutinesAndExercises(memberId);
    }

    public ResponseEntity<?> addExerciseLogToRoutine(ExerciseLog log) {
        boolean exerciseAdded = exerciseLogRepository.save(log);
        if (exerciseAdded) {
            return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Failed\"}"); 
    }

    public ResponseEntity<?> deleteExerciseLog(Long logId) {
        boolean exerciseDeleted = exerciseLogRepository.deleteLog(logId);
        if (exerciseDeleted) {
            return ResponseEntity.ok().body("Success");
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed");
    }

    public void updateExerciseLog(Long logId, ExerciseLog updatedExerciseLog) {
        Optional<ExerciseLog> existingLog = exerciseLogRepository.findById(logId);
        if (existingLog.isPresent()) {
            updatedExerciseLog.setLogId(logId);
            exerciseLogRepository.updateExerciseLog(updatedExerciseLog);
        } else {
            throw new RuntimeException("Exercise Log not found");
        }
    }
}
