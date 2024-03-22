package com.example.project.Services;

import com.example.project.Models.Member;
import com.example.project.Repos.MemberRepository;
import com.example.project.Repos.TrainerRepository;
import com.example.project.dto.MemberScheduleView;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Member registerMember(Member member) {
        String hashedPassword = encoder.encode(member.getPassword());
		member.setPassword(hashedPassword);
        memberRepository.save(member);
        return member;
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
    public void updateMember(Long memberId, Member updatedMember) {
        memberRepository.findById(memberId)
                                    .orElseThrow(() -> new RuntimeException("Member not found"));
        updatedMember.setMemberId(memberId);
        memberRepository.update(updatedMember);
    }

    public List<MemberScheduleView> getMemberSchedule(Integer memberId) {
        return memberRepository.getMemberSchedule(memberId);
    }

    public List<Map<String, Object>> getAvailableTrainers() {
        return trainerRepository.getAvailableTrainers();
    }

}
