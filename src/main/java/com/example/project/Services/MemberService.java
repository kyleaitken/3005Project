package com.example.project.Services;

import com.example.project.Models.Member;
import com.example.project.Repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean validateLogin(String email, String password) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        return memberOpt.map(member -> encoder.matches(password, member.getPassword())).orElse(false);  
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
        Member existingMember = memberRepository.findById(memberId)
                                    .orElseThrow(() -> new RuntimeException("Member not found"));
        updatedMember.setMemberId(memberId);
        memberRepository.update(updatedMember);
    }

}
