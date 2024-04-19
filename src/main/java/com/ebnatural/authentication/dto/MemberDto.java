package com.ebnatural.authentication.dto;

import com.ebnatural.authentication.domain.Member;
import com.ebnatural.authentication.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private MemberRole memberRole;
    private String representative;
    private String businessNumber;
    private String postalCode;
    private String address;
    private String businessRegistration;
    private String manager;
    private String managerEmail;
    private String managerPhoneNumber;
    private LocalDate lastLogin;
    private LocalDate createdAt;
    private TokenDto token;
    public MemberDto(Long id, String username, MemberRole memberRole, TokenDto token) {
        this.id = id;
        this.username = username;
        this.memberRole = memberRole;
        this.token = token;
    }

    public static MemberDto from(Member member, TokenDto tokenDto) {
        return new MemberDto(member.getId(), member.getUsername(), member.getMemberRole(), tokenDto);
    }
}
