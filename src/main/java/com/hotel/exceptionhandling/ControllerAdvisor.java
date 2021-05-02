package com.hotel.exceptionhandling;

import com.hotel.exception.NoSuchHotelException;
import com.hotel.exception.NoSuchUserException;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(NoSuchHotelException.class)

	public ResponseEntity<Object> handleNoSuchHotelException(NoSuchHotelException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", "failure");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchUserException.class)

	public ResponseEntity<Object> handleNoSuchUserException(NoSuchUserException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", "failure");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)

	public ResponseEntity<Object> handleRuntimeException(NoSuchUserException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "Internal Server error occured");
		body.put("status", "failure");

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
