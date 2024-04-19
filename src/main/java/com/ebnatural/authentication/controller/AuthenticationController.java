package com.ebnatural.authentication.controller;

import com.ebnatural.authentication.dto.MemberDto;
import com.ebnatural.authentication.dto.request.LoginRequest;
import com.ebnatural.authentication.dto.request.RegisterRequest;
import com.ebnatural.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        MemberDto member = authenticationService.login(loginRequest);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/endpoint")
    public ResponseEntity<?> endpoint() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
