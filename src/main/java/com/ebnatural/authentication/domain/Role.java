package com.ebnatural.authentication.domain;

public enum Role {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN"), ROLE_ANONYMOUS("ANONYMOUS");

    String role;

    private Role(String role) {
        this.role = role;
    }


    public String value() {
        return role;
    }
}
