package com.jpmc.theater.exception;

/**
 * TheaterException is an exception wrapper class to raise the theater system
 * specific exceptions as needed. More specific exceptions can be derives as needed
 * if system grows large.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class TheaterException extends Exception {
	private static final long serialVersionUID = -6137940470539273741L;

	public TheaterException(String message) {
		super(message);
	}
	
	public TheaterException(Exception exception) {
		super(exception);
	}
	
}
