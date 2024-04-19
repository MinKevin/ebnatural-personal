package com.ebnatural.common.exception.custom;

public class TermsOfServiceAgreementNeedException extends CustomException{
    public TermsOfServiceAgreementNeedException() {
        super(ErrorCode.TERMS_OF_SERVICE_AGREEMENT_NEED_EXCEPTION);
    }
}
