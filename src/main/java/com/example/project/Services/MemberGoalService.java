package com.example.project.Services;

import com.example.project.Models.MemberGoal;
import com.example.project.Repos.MemberGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class MemberGoalService {

    private final MemberGoalRepository memberGoalRepository;

    @Autowired
    public MemberGoalService(MemberGoalRepository memberGoalRepository) {
        this.memberGoalRepository = memberGoalRepository;
    }

    public List<MemberGoal> findCompleteMemberGoals(Long memberId) {
        return memberGoalRepository.findCompleteMemberGoals(memberId);
    }

    public List<MemberGoal> findIncompleteMemberGoals(Long memberId) {
        return memberGoalRepository.findIncompleteMemberGoals(memberId);
    }

    public void updateGoal(Long goalId, MemberGoal updatedMemberGoal) {
        Optional<MemberGoal> existingGoal = memberGoalRepository.findById(goalId);
        if (existingGoal.isPresent()) {
            updatedMemberGoal.setGoalId(goalId);
            memberGoalRepository.updateGoal(updatedMemberGoal);
        } else {
            throw new RuntimeException("Goal not found");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteGoal(Long memberId, Long goalId) {
        boolean goalDeleted = memberGoalRepository.deleteGoal(memberId, goalId);
        if (goalDeleted) return ResponseEntity.ok().body("Success");
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Failed");
    }

    public ResponseEntity<?> save(MemberGoal goal) {
        boolean goalAdded = memberGoalRepository.save(goal);
        if (goalAdded) {
            return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed"); 
    }


    public ResponseEntity<?> markGoalAsComplete(Long memberId, Long goalId) {
        LocalDate completionDate = LocalDate.now(); 
        boolean goalUpdated = memberGoalRepository.updateGoalCompletion(goalId, memberId, true, completionDate);
        if (goalUpdated) return ResponseEntity.ok().body("Success");
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Failed");      
    }


}