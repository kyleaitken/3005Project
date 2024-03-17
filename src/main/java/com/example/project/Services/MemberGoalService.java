package com.example.project.Services;

import com.example.project.Models.MemberGoal;
import com.example.project.Repos.MemberGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteGoal(Long memberId, Long goalId) {
        memberGoalRepository.deleteGoal(memberId, goalId);
    }

    public void save(MemberGoal goal) {
        memberGoalRepository.save(goal);
    }


    public void markGoalAsComplete(Long memberId, Long goalId) {
        LocalDate completionDate = LocalDate.now(); 
        memberGoalRepository.updateGoalCompletion(goalId, memberId, true, completionDate);
    }


}