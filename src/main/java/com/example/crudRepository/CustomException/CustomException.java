package com.example.crudRepository.CustomException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoResponse(ResourceNotFoundException exception) {
		ErrorResponse response = new ErrorResponse(LocalDateTime.now(), "PGE001", exception.getMessage(), "No product found");
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
		
	}
}
