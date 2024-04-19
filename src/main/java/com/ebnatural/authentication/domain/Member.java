package com.ebnatural.authentication.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.ROLE_USER;
    private String representative;
    private String businessName;
    private String businessNumber;
    private String postalCode;
    private String address;
    private String businessRegistration;
    private String manager;
    private String managerEmail;
    private String managerPhoneNumber;

    @OneToMany
    @JoinColumn(name = "terms_of_service_agreement_id")
    List<TermsOfServiceAgreement> termsOfServiceAgreements;

    public Member() {

    }

    public Member(String username,
                  String password,
                  String representative,
                  String businessName,
                  String businessNumber,
                  String postalCode,
                  String address,
                  String businessRegistration,
                  String manager,
                  String managerEmail,
                  String managerPhoneNumber) {
        this.username = username;
        this.password = password;
        this.representative = representative;
        this.businessName = businessName;
        this.businessNumber = businessNumber;
        this.postalCode = postalCode;
        this.address = address;
        this.businessRegistration = businessRegistration;
        this.manager = manager;
        this.managerEmail = managerEmail;
        this.managerPhoneNumber = managerPhoneNumber;
    }
}
