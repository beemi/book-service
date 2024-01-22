package com.jaitechltd.bookservice.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private String timestamp;
    private boolean success;
    private String message;
    private Object data;

    public ErrorResponse(boolean success, String message, Object data) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ErrorResponse success(Object data) {
        return new ErrorResponse(true, "Operation successful", data);
    }

    public static ErrorResponse failure(String message) {
        return new ErrorResponse(false, message, null);
    }
}
