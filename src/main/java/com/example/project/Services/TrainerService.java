package com.example.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Repos.MemberRepository;
import com.example.project.Repos.TrainerRepository;
import com.example.project.Repos.TrainingSessionRepository;
import com.example.project.dto.TrainerMemberProfileView;
import com.example.project.dto.TrainerMemberView;
import com.example.project.dto.TrainerScheduleUpdateRequest;
import com.example.project.dto.TrainerTrainingSessionView;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final MemberRepository memberRepository;

    public TrainerService(TrainerRepository trainerRepository, TrainingSessionRepository trainingSessionRepository, MemberRepository memberRepository) {
        this.trainerRepository = trainerRepository;
        this.trainingSessionRepository = trainingSessionRepository;
        this.memberRepository = memberRepository;
    }

    public ResponseEntity<?> updateSchedule(Integer trainerId, TrainerScheduleUpdateRequest newSched) {
        boolean scheduleUpdated = trainerRepository.updateSchedule(trainerId, newSched);
        if (scheduleUpdated) return ResponseEntity.ok().body("Trainer schedule updated");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found or update failed");
    }

    public List<TrainerTrainingSessionView> getUpcomingSessions(Integer trainerId) {
        return trainerRepository.getUpcomingSessions(trainerId);
    }

    public List<TrainerTrainingSessionView> getPastSessions(Integer trainerId) {
        return trainerRepository.getPastSessions(trainerId);
    }

    public ResponseEntity<?> deleteSession(Integer sessionId) {
        boolean sessionDeleted = trainingSessionRepository.deleteSession(sessionId);
        if (sessionDeleted) return ResponseEntity.ok().body("Training session deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not found or unsuccessful delete");
    }

    public List<TrainerMemberView> getMembers() {
        return memberRepository.getMembersForTrainer();
    }

    public Optional<TrainerMemberProfileView> getMember(Integer memberId) {
        return memberRepository.getMemberProfileForTrainer(memberId);
    }

}
