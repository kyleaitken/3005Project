package com.example.project.Controllers;

import com.example.project.Services.MemberService;
import com.example.project.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final MemberService memberService;

    @Autowired
    public AuthenticationController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isValidUser = memberService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValidUser) {
            // Generate a token or manage session here
            return ResponseEntity.ok().body("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}