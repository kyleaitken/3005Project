package com.example.project.Services;

import com.example.project.Models.TrainingSession;
import com.example.project.Repos.TrainingSessionRepository;
import com.example.project.dto.MemberTrainingSessionView;
import com.example.project.dto.TrainingSessionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public List<TrainingSession> findAllMemberSessions(Long memberId) {
        return trainingSessionRepository.findAllMemberSessions(memberId);
    }

    public List<MemberTrainingSessionView> findAllFutureMemberSessions(Long memberId) {
        return trainingSessionRepository.findAllFutureMemberSessions(memberId);
    }

    public List<MemberTrainingSessionView> findAllPastMemberSessions(Long memberId) {
        return trainingSessionRepository.findAllPastMemberSessions(memberId);
    }

    public void deleteSession(Long sessionId) {
        trainingSessionRepository.deleteSession(sessionId);
    }

    public ResponseEntity<?> addNewMemberSession(Integer memberId, TrainingSessionRequest session) {
        return trainingSessionRepository.addNewMemberSession(memberId, session);
    }

}
