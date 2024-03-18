package com.example.project.Controllers;

import com.example.project.Models.Member;
import com.example.project.Models.TrainingSession;
import com.example.project.Services.FitnessClassService;
import com.example.project.Services.MemberService;
import com.example.project.Services.TrainingSessionService;
import com.example.project.dto.FitnessClassView;
import com.example.project.dto.MemberTrainingSessionView;
import com.example.project.dto.TrainingSessionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members") // Base URI for all endpoints in this controller
public class MemberController {

    private final MemberService memberService;
    private final TrainingSessionService trainingSessionService;
    private final FitnessClassService fitnessClassService;

    @Autowired
    public MemberController(MemberService memberService, TrainingSessionService trainingSessionService, FitnessClassService fitnessClassService) {
        this.memberService = memberService;
        this.trainingSessionService = trainingSessionService;
        this.fitnessClassService = fitnessClassService;
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        return ResponseEntity.ok(members); // Return the list of members with OK status
    }

	// Get specific member
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMemberById(@PathVariable Long memberId) {
		Optional<Member> memberOptional = memberService.findById(memberId);
        if (memberOptional.isPresent()) {
            return ResponseEntity.ok(memberOptional.get());
        } else {
            // Returning a custom error message with 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
    }

	@PostMapping("/register")
	public ResponseEntity<?> registerMember(@RequestBody Member member) {
		try {
			Member savedMember = memberService.registerMember(member);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Email already in use.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
		}
	}

    // Update an existing member's information
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        memberService.updateMember(memberId, updatedMember);
        return ResponseEntity.ok(updatedMember); // Return the updated member with OK status
    }


    /* 
    ***** TRAINING SESSIONS ******
    */ 

    // get all member training sessions

   @GetMapping("/{memberId}/sessions")
    public ResponseEntity<List<TrainingSession>> getMemberTrainingSessions(@PathVariable Long memberId) {
        List<TrainingSession> sessions = trainingSessionService.findAllMemberSessions(memberId);
        return ResponseEntity.ok(sessions); 
    }

    // get all upcoming member training sessions

   @GetMapping("/{memberId}/futureSessions")
   public ResponseEntity<List<MemberTrainingSessionView>> getMemberFutureTrainingSessions(@PathVariable Long memberId) {
       List<MemberTrainingSessionView> sessions = trainingSessionService.findAllFutureMemberSessions(memberId);
       return ResponseEntity.ok(sessions); 
   }


   @GetMapping("/{memberId}/pastSessions")
   public ResponseEntity<List<MemberTrainingSessionView>> getMemberPastTrainingSessions(@PathVariable Long memberId) {
       List<MemberTrainingSessionView> sessions = trainingSessionService.findAllPastMemberSessions(memberId);
       return ResponseEntity.ok(sessions); 
   }

   // delete training session
   @DeleteMapping("/sessions/{sessionId}")
   public ResponseEntity<?> deleteTrainingSessionById(@PathVariable Long sessionId) {
        trainingSessionService.deleteSession(sessionId);
        return ResponseEntity.ok().build();
    }

    // add training session for member
    @PostMapping("/{memberId}/addSession")
	public ResponseEntity<?> addMemberTrainingSession(@PathVariable Integer memberId, @RequestBody TrainingSessionRequest session) {
       return trainingSessionService.addNewMemberSession(memberId, session);
	}


    /* 
    ***** FITNESS CLASSES ******
    */ 

    // get past classes
    @GetMapping("/{memberId}/pastClasses")
   public ResponseEntity<List<FitnessClassView>> getMemberPastFitnessClasses(@PathVariable Integer memberId) {
       List<FitnessClassView> fitnessClasses = fitnessClassService.findAllPastMemberFitnessClasses(memberId);
       return ResponseEntity.ok(fitnessClasses); 
   }


    // get future classes enrolled for

    @GetMapping("/{memberId}/futureClasses")
    public ResponseEntity<List<FitnessClassView>> getMemberFutureFitnessClasses(@PathVariable Integer memberId) {
        List<FitnessClassView> fitnessClasses = fitnessClassService.findAllFutureMemberFitnessClasses(memberId);
        return ResponseEntity.ok(fitnessClasses); 
    }

    // get future classes not enrolled for 

    @GetMapping("/{memberId}/availableClasses")
    public ResponseEntity<List<FitnessClassView>> getMemberAvailableFitnessClasses(@PathVariable Integer memberId) {
        List<FitnessClassView> fitnessClasses = fitnessClassService.findAllAvailableMemberFitnessClasses(memberId);
        return ResponseEntity.ok(fitnessClasses); 
    }

    // leave class
    @DeleteMapping("/{memberId}/leave/{classId}")
    public ResponseEntity<?> leaveClassById(@PathVariable Integer memberId, @PathVariable Integer classId) {
        fitnessClassService.removeMemberFromClass(memberId, classId);
        return ResponseEntity.ok().build();
    }

    // join class

    @PostMapping("/{memberId}/join/{classId}")
    public ResponseEntity<?> joinClassById(@PathVariable Integer memberId, @PathVariable Integer classId) {
        return fitnessClassService.addMemberToClass(memberId, classId);
    }


    /* 
    ***** INVOICES ******
    */ 



}
