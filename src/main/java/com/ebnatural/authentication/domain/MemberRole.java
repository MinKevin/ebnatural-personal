package com.ebnatural.authentication.domain;

public enum MemberRole {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN"), ROLE_ANONYMOUS("ANONYMOUS");

    String role;

    private MemberRole(String role) {
        this.role = role;
    }


    public String value() {
        return role;
    }
}
