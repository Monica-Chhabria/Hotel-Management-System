package com.hotel.exception;

public class NoSuchHotelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchHotelException(String message) {
		super(message);
	}

}
