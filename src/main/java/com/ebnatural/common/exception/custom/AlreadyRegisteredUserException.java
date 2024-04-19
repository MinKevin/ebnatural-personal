package com.ebnatural.common.exception.custom;

public class AlreadyRegisteredUserException extends CustomException {
    public AlreadyRegisteredUserException() {
        super(ErrorCode.ALREADY_REGISTERED_USER_EXCEPTION);
    }
}
