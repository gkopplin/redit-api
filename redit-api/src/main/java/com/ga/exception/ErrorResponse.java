package com.ga.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus httpStatus;
	private String message;
	private String cause;
	private String timestamp;
	public ErrorResponse(HttpStatus httpStatus, String message, String cause) {
		super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.cause = cause;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }
    
    public ErrorResponse(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }
}
	
	


