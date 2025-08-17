package com.example.crudRepository.CustomException;

import java.time.LocalDateTime;

public class ErrorResponse {

	private LocalDateTime localDateTime;
	private String errorMessage;
	private String errorCode;
	private String details;
	
	public ErrorResponse(LocalDateTime localDateTime, String errorCode, String errorMessage, String details) {
		this.localDateTime = localDateTime;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.details = details;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
