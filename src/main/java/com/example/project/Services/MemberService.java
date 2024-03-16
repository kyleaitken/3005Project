package com.example.project.Services;

import com.example.project.Entities.Member;
import com.example.project.Repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    // Update a member's info
    public void updateMember(Long memberId, Member updatedMember) {
        // Optionally, fetch and validate the existing member exists
        Member existingMember = memberRepository.findById(memberId)
                                    .orElseThrow(() -> new RuntimeException("Member not found"));
    
        // Update the ID of the updatedMember object to ensure it matches the path variable
        updatedMember.setMemberId(memberId);
    
        // Now, update the member
        memberRepository.update(updatedMember);
    }

}
