package com.example.project.Controllers;

import com.example.project.Models.Member;
import com.example.project.Models.Trainer;
import com.example.project.Services.AuthenticationService;
import com.example.project.Services.MemberService;
import com.example.project.dto.LoginRequest;
import com.example.project.dto.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<LoginResponse> loginOpt = authenticationService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginOpt.isPresent()) {
            return ResponseEntity.ok().body(loginOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}