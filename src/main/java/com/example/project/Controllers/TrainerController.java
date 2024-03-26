package com.example.project.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Models.Trainer;
import com.example.project.Services.TrainerService;
import com.example.project.dto.TrainerMemberProfileView;
import com.example.project.dto.TrainerMemberView;
import com.example.project.dto.TrainerScheduleUpdateRequest;
import com.example.project.dto.TrainerTrainingSessionView;

@RestController
@RequestMapping("/trainers") 
public class TrainerController {
    private final TrainerService trainerservice;

    public TrainerController(TrainerService trainerservice) {
        this.trainerservice = trainerservice;
    }

    @GetMapping("/{trainerId}")
    public Optional<Trainer> getTrainer(@PathVariable Integer trainerId) {
        return trainerservice.getTrainer(trainerId);
    }

    @PutMapping("/{trainerId}/schedule")
    public ResponseEntity<?> updateTrainerSchedule(@PathVariable Integer trainerId, @RequestBody TrainerScheduleUpdateRequest newSchedule) {
        return trainerservice.updateSchedule(trainerId, newSchedule);
    }

    @GetMapping("/{trainerId}/upcomingSessions")
    public List<TrainerTrainingSessionView> findAllUpcomingSessions(@PathVariable Integer trainerId) {
        return trainerservice.getUpcomingSessions(trainerId);
    }

    @GetMapping("/{trainerId}/pastSessions")
    public List<TrainerTrainingSessionView> findAllPastSessions(@PathVariable Integer trainerId) {
        return trainerservice.getPastSessions(trainerId);
    }

    @DeleteMapping("/deleteSession/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable Integer sessionId) {
        return trainerservice.deleteSession(sessionId);
    }


    @GetMapping("/members")
    public List<TrainerMemberView> getMembers() {
        return trainerservice.getMembers();
    }


    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Integer memberId) {
        Optional<TrainerMemberProfileView> memberProfileOpt = trainerservice.getMember(memberId);
        if (memberProfileOpt.isPresent()) {
            return ResponseEntity.ok(memberProfileOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
    }

    
}
