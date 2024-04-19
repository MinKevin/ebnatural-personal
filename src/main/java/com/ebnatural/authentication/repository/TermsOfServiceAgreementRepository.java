package com.ebnatural.authentication.repository;

import com.ebnatural.authentication.domain.TermsOfServiceAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsOfServiceAgreementRepository extends JpaRepository<TermsOfServiceAgreement, Long> {
}
