package com.hotel.exception;

public class NoSuchUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchUserException(String message) {
		super(message);
	}
}
