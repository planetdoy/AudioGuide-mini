package com.miniproject.audioguide.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private String code;
    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
