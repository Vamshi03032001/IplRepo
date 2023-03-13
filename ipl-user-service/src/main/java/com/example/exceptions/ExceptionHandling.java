package com.example.exceptions;

public class ExceptionHandling extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
	

	public ExceptionHandling() {
		super();
	}

	public ExceptionHandling(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
