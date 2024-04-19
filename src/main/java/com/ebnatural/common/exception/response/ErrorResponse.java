package com.ebnatural.common.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private Integer code;
    private String message;
    private String detailMessage;
}
