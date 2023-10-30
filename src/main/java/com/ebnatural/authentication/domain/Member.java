package com.ebnatural.authentication.domain;

import com.ebnatural.authentication.domain.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role memberRole = Role.ROLE_USER;
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
