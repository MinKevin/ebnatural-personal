package com.ebnatural.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
