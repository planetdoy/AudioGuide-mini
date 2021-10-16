package com.miniproject.audioguide.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    protected ResponseEntity<ErrorResult> handleExceptionInternal(Exception ex, WebRequest request) {
        log.error("[exceptionHandle] ex", ex);
        ErrorResult errorResult =
                new ErrorResult("SERVER-EX",LocalDateTime.now(), "내부 오류", request.getDescription(false));
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<ErrorResult> notMatchException(Exception ex, WebRequest request) {
        log.error("[exceptionHandle] ex", ex);
        ErrorResult errorResult =
                new ErrorResult("LOGIN-EX",LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(Exception ex, WebRequest request) {
        log.error("[exceptionHandle] ex", ex);
        ErrorResult errorResult =
                new ErrorResult("USER-EX",LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }
}
