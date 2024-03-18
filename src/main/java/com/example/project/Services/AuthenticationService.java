package com.example.project.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Models.Member;
import com.example.project.Models.Trainer;
import com.example.project.Models.Admin;
import com.example.project.Repos.AdminRepository;
import com.example.project.Repos.MemberRepository;
import com.example.project.Repos.TrainerRepository;
import com.example.project.dto.LoginResponse;

@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public AuthenticationService(MemberRepository memberRepository, TrainerRepository trainerRepository, AdminRepository adminRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.adminRepository = adminRepository;
    }

    public Optional<LoginResponse> validateLogin(String email, String password) {
        LoginResponse loginResponse = new LoginResponse();

        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (memberOpt.isPresent()) {
            if (memberOpt.filter(member -> encoder.matches(password, member.getPassword())).isPresent()) {
                Member member = memberOpt.get();
                loginResponse.setUserType("Member");
                loginResponse.setId(member.getMemberId().intValue());
                return Optional.of(loginResponse);
            } else {
                return Optional.empty();
            }
        }

        Optional<Trainer> trainerOpt = trainerRepository.findByEmail(email);
        if (trainerOpt.isPresent()) {
            Trainer trainer = trainerOpt.get();
            if (trainer.getPassword().matches(password)) {
                loginResponse.setUserType("Trainer");
                loginResponse.setId(trainer.getTrainerId());
                return Optional.of(loginResponse);
            } else {
                return Optional.empty();
            }
        }

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getPassword().matches(password)) {
                loginResponse.setUserType("Admin");
                loginResponse.setId(admin.getAdminId());
                return Optional.of(loginResponse);
            } else {
                return Optional.empty();
            }
        }

        return Optional.empty();

    }


}
