package com.hotel.exception;

public class NoSuchRatingException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	public NoSuchRatingException(String message) {
		super(message);
	}
}
