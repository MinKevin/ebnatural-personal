package com.ebnatural.authentication.service;

import com.ebnatural.authentication.domain.Member;
import com.ebnatural.authentication.dto.CustomUserDetails;
import com.ebnatural.authentication.dto.LoginRequestDto;
import com.ebnatural.authentication.dto.MemberDto;
import com.ebnatural.authentication.repository.MemberRepository;
import com.ebnatural.common.exception.custom.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = getMemberFromRepositoryByUsername(username);
        return new CustomUserDetails(member);
    }

    public void signup(MemberDto memberDto) {

    }

    public ResponseEntity<Member> login(LoginRequestDto loginRequestDto) {
        getMemberFromRepositoryByUsername(loginRequestDto.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Member getMemberFromRepositoryByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(UsernameNotFoundException::new);
    }
}
