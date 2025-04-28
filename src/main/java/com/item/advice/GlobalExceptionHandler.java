package com.item.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.item.dto.response.ErrorResponse;
import com.item.exception.ItemNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception= ItemNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		System.out.println(e.getMessage());
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(404);
		response.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
