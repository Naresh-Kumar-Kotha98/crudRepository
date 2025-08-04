package com.example.crudRepository.CustomException;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private LocalDateTime localDateTime;
	private String message;
	private String details;
	
	public ErrorResponse(LocalDateTime localDateTime, String message, String details) {
		this.localDateTime = localDateTime;
		this.message = message;
		this.details = details;
	}
}
