package com.ebnatural.authentication.repository;

import com.ebnatural.authentication.domain.TermsOfService;
import com.ebnatural.authentication.domain.TermsOfServiceAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsOfServiceRepository extends JpaRepository<TermsOfService, Long> {
}
