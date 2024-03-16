package com.example.project.Controllers;

import com.example.project.Models.Member;
import com.example.project.Services.MemberService;
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

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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


}
