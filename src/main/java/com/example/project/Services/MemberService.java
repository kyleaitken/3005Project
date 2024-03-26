package com.example.project.Services;

import com.example.project.Models.Member;
import com.example.project.Repos.MemberRepository;
import com.example.project.Repos.TrainerRepository;
import com.example.project.dto.MemberScheduleView;
import com.example.project.dto.RegisterMemberRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public ResponseEntity<?> registerMember(RegisterMemberRequest member) {
        String hashedPassword = encoder.encode(member.getPassword());
		member.setPassword(hashedPassword);
        boolean registered = memberRepository.save(member);
        if (registered) return ResponseEntity.ok(Map.of("message", "Success"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed"));
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByEmail(String emailString) {
        return memberRepository.findByEmail(emailString);
    }

    // Update a member's info
    public ResponseEntity<?> updateMember(Long memberId, Member updatedMember) {
        updatedMember.setMemberId(memberId);
        boolean memberUpdated = memberRepository.update(updatedMember);
        if (memberUpdated) {
            return ResponseEntity.ok(Map.of("message", "Success"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred during updating."));
        }
    
    }

    public List<MemberScheduleView> getMemberSchedule(Integer memberId) {
        return memberRepository.getMemberSchedule(memberId);
    }

    public List<Map<String, Object>> getAvailableTrainers() {
        return trainerRepository.getAvailableTrainers();
    }

}
