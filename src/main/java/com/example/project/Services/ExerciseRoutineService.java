package com.example.project.Services;

import com.example.project.Models.ExerciseLog;
import com.example.project.Models.MemberExerciseRoutine;
import com.example.project.Models.MemberGoal;
import com.example.project.Models.MemberHealthInfo;
import com.example.project.Repos.ExerciseLogRepository;
import com.example.project.Repos.ExerciseRoutineRepository;
import com.example.project.Repos.MemberHealthInfoRepository;
import com.example.project.dto.ExerciseRoutineDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void save(Long memberId) {
        MemberExerciseRoutine routine = new MemberExerciseRoutine();
        routine.setMemberId(memberId);
        exerciseRoutineRepository.save(routine);
    }

    public List<MemberExerciseRoutine> findMemberRoutines(Long memberId) {
        return exerciseRoutineRepository.findMemberRoutines(memberId);
    }

    public void deleteRoutine(Long routineId) {
        exerciseRoutineRepository.deleteRoutine(routineId);
    }

    public List<ExerciseRoutineDto> findRoutinesAndExercises(Long memberId) {
        return exerciseRoutineRepository.findRoutinesAndExercises(memberId);
    }

    public void addExerciseLogToRoutine(ExerciseLog log) {
        exerciseLogRepository.save(log);
    }

    public void deleteExerciseLog(Long logId) {
        exerciseLogRepository.deleteLog(logId);
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
