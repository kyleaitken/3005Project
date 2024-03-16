package com.example.project.Controllers; // Adjust package name as necessary

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.Services.MemberHealthInfoService;
import org.springframework.http.ResponseEntity;
import com.example.project.Models.MemberHealthInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/memberHealthInfo")
public class MemberHealthInfoController {

    private final MemberHealthInfoService memberHealthInfoService;

    @Autowired
    public MemberHealthInfoController(MemberHealthInfoService memberHealthInfoService) {
        this.memberHealthInfoService = memberHealthInfoService;
    }

    // get member's health info
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMemberHealthInfoById(@PathVariable Long memberId) {
		Optional<MemberHealthInfo> memberHealthInfoOptional = memberHealthInfoService.findById(memberId);
        if (memberHealthInfoOptional.isPresent()) {
            return ResponseEntity.ok(memberHealthInfoOptional.get());
        } else {
            // Returning a custom error message with 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Health Info not found");
        }
    }

    // update member's health info
    @PutMapping("/{memberId}")
    public ResponseEntity<MemberHealthInfo> updateMemberHealthInfo(@PathVariable Long memberId, @RequestBody MemberHealthInfo updatedMemberHealthInfo) {
        memberHealthInfoService.updateOrSaveMemberHealthInfo(memberId, updatedMemberHealthInfo);
        return ResponseEntity.ok(updatedMemberHealthInfo); 
    }
}