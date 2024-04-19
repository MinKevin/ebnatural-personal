package com.ebnatural.authentication.service;

import com.ebnatural.authentication.domain.Member;
import com.ebnatural.authentication.domain.TermsOfServiceAgreement;
import com.ebnatural.authentication.dto.CustomUserDetails;
import com.ebnatural.authentication.dto.MemberDto;
import com.ebnatural.authentication.dto.TokenDto;
import com.ebnatural.authentication.dto.request.LoginRequest;
import com.ebnatural.authentication.dto.request.RegisterRequest;
import com.ebnatural.authentication.repository.MemberRepository;
import com.ebnatural.authentication.repository.TermsOfServiceRepository;
import com.ebnatural.common.exception.custom.AlreadyRegisteredUserException;
import com.ebnatural.common.exception.custom.PasswordNotMatchException;
import com.ebnatural.common.exception.custom.TermsOfServiceAgreementNeedException;
import com.ebnatural.common.exception.custom.UsernameNotFoundException;
import com.ebnatural.common.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
//@Transactional
public class AuthenticationService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final TermsOfServiceRepository termsOfServiceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = getMemberFromRepositoryByUsername(username);
        return new CustomUserDetails(member);
    }

    public void register(RegisterRequest registerRequest) {
        checkDuplicatedUsername(registerRequest.getUsername());
        checkPasswordAndPasswordConfirmMatch(registerRequest);

        Member member = encodePasswordAndConvertToMemberEntity(registerRequest);
        checkTermsOfServiceAgreements(registerRequest.getTermsOfServices(), member);

        memberRepository.save(member);
    }

    public MemberDto login(LoginRequest loginRequest) {
        Member member = getMemberFromRepositoryByUsername(loginRequest.getUsername());

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword()))
            throw new PasswordNotMatchException();

        TokenDto tokenDto = new TokenDto(
                jwtProvider.createAccessToken(member.getUsername(), member.getMemberRole()),
                jwtProvider.createRefreshToken(member.getUsername(), member.getMemberRole())
        );

        return MemberDto.from(member, tokenDto);
    }

    private void checkDuplicatedUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new AlreadyRegisteredUserException();
        }
    }

    private void checkPasswordAndPasswordConfirmMatch(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            throw new PasswordNotMatchException();
        }
    }

    private Member encodePasswordAndConvertToMemberEntity(RegisterRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        Member member = registerRequest.toEntity(encodedPassword);
        return member;
    }

    private void checkTermsOfServiceAgreements(List<Boolean> termsOfServiceAgreements, Member member) {
        log.info("termsOfServiceAgreements.size = {}", termsOfServiceAgreements.size());
        for (long i = 1; i <= termsOfServiceAgreements.size(); i++) {
            if (termsOfServiceRepository.findById(i).get().getNeedAgreement() && !termsOfServiceAgreements.get((int) (i - 1)))
                throw new TermsOfServiceAgreementNeedException();
        }

        for (long i = 1; i <= termsOfServiceAgreements.size(); i++) {
            TermsOfServiceAgreement.of(member, termsOfServiceRepository.findById(i).get(), termsOfServiceAgreements.get((int)(i - 1)));
        }
    }

    private Member getMemberFromRepositoryByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(UsernameNotFoundException::new);
    }
}
