package com.example.project.Controllers;

import com.example.project.Entities.Member;
import com.example.project.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Add a new member
    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED); // Return the saved member with CREATED status
    }

    // Update an existing member's information
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        memberService.updateMember(memberId, updatedMember);
        return ResponseEntity.ok(updatedMember); // Return the updated member with OK status
    }


}
