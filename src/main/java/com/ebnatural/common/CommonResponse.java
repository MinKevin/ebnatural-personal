package com.ebnatural.common;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class CommonResponse extends ResponseEntity {

    private Integer customCode;
    private String message;
    private Map<String, Object> result;

    private CommonResponse (Integer customCode, String message, Map<String, Object> result) {
        super(HttpStatus.OK);
        this.customCode = customCode;
        this.message = message;
        this.result = result;
    }
    public CommonResponse(HttpStatusCode status) {
        super(status);
    }

    public CommonResponse(Object body, HttpStatusCode status) {
        super(body, status);
    }

    public CommonResponse(MultiValueMap headers, HttpStatusCode status) {
        super(headers, status);
    }

    public CommonResponse(Object body, MultiValueMap headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    public CommonResponse(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
