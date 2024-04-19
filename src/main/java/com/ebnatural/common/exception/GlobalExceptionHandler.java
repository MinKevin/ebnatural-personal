package com.ebnatural.common.exception;

import com.ebnatural.common.exception.custom.CustomException;
import com.ebnatural.common.exception.custom.ErrorCode;
import com.ebnatural.common.exception.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(Exception e) {
//        log.warn("handleException = {}", e.getMessage());
//
//        return internalHandleException();
//    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        return internalHandleException(e.getErrorCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return internalHandleException(ErrorCode.BINDING_EXCEPTION, getMessage(e));
    }

    private ResponseEntity<Object> internalHandleException() {
        return new ResponseEntity<>(makeErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> internalHandleException(ErrorCode errorCode) {
        return new ResponseEntity<>(makeErrorResponse(errorCode), errorCode.getHttpStatus());
    }

    private ResponseEntity<Object> internalHandleException(ErrorCode errorCode, String detailMessage) {
        return new ResponseEntity<>(makeErrorResponse(errorCode, detailMessage), ErrorCode.BINDING_EXCEPTION.getHttpStatus());
    }

    private String getMessage(MethodArgumentNotValidException e) {
        return e.getFieldErrors().get(0).getDefaultMessage();
    }

    private ErrorResponse makeErrorResponse() {
        return ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .code(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .build();
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String detailMessage) {
        return ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .code(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .detailMessage(detailMessage)
                .build();
    }
}