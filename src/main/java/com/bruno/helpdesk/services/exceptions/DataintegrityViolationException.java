package com.bruno.helpdesk.services.exceptions;

public class DataintegrityViolationException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataintegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataintegrityViolationException(String message) {
		super(message);
	}

	
	
}
