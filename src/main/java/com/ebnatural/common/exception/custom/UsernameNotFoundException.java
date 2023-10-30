package com.ebnatural.common.exception.custom;

public class UsernameNotFoundException extends CustomException {
    public UsernameNotFoundException() {
        super(ErrorCode.USERNAME_NOT_FOUND_EXCEPTION);
    }
}
