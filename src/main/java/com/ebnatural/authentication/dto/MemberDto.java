package com.ebnatural.authentication.dto;

import com.ebnatural.authentication.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private Role memberRole;
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

}
