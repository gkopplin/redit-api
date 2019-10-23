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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
    
}
	
	


