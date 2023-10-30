package com.ebnatural.authentication.controller;

import com.ebnatural.authentication.dto.LoginRequestDto;
import com.ebnatural.authentication.dto.MemberDto;
import com.ebnatural.authentication.service.AuthenticationService;
import com.ebnatural.authentication.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        authenticationService.login(loginRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody MemberDto memberDto) {
        authenticationService.signup(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
