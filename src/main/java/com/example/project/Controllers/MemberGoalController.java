package com.example.project.Controllers; 

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.Services.MemberGoalService;

import org.springframework.http.ResponseEntity;

import com.example.project.Models.MemberGoal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/members/{memberId}/goals")
public class MemberGoalController {

    private final MemberGoalService memberGoalService;

    @Autowired
    public MemberGoalController(MemberGoalService memberGoalService) {
        this.memberGoalService = memberGoalService;
    }

    // get member's completed goals
	@GetMapping("/completed")
	public ResponseEntity<?> getCompleteMemberGoals(@PathVariable Long memberId) {
		List<MemberGoal> completedMemberGoals = memberGoalService.findCompleteMemberGoals(memberId);
        return ResponseEntity.ok(completedMemberGoals); 
    }

    // get member's incomplete goals
	@GetMapping("/incomplete")
	public ResponseEntity<?> getIncompleteMemberGoals(@PathVariable Long memberId) {
		List<MemberGoal> incompleteMemberGoals = memberGoalService.findIncompleteMemberGoals(memberId);
        return ResponseEntity.ok(incompleteMemberGoals); 
    }

    // Update a goal
    @PutMapping("/incomplete/{goalId}")
    public ResponseEntity<?> updateMemberGoal(@PathVariable Long goalId, @RequestBody MemberGoal updatedMemberGoal) {
        memberGoalService.updateGoal(goalId, updatedMemberGoal);
        return ResponseEntity.ok(updatedMemberGoal); 
    }

    @PutMapping("/{goalId}/complete")
    public ResponseEntity<?> markGoalAsComplete(@PathVariable Long memberId, @PathVariable Long goalId) {
        memberGoalService.markGoalAsComplete(memberId, goalId);
        return ResponseEntity.ok().build();
    }

    // delete goal
    @DeleteMapping("/incomplete/{goalId}")
    public ResponseEntity<?> deleteMemberGoalById(@PathVariable Long memberId, @PathVariable Long goalId) {
        memberGoalService.deleteGoal(memberId, goalId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
	public ResponseEntity<?> addMemberGoal(@PathVariable Long memberId, @RequestBody MemberGoal goal) {
        goal.setMemberId(memberId);
        memberGoalService.save(goal);
        return ResponseEntity.ok().build();
	}




}