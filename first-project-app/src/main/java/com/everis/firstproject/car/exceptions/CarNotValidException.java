package com.everis.firstproject.car.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CarNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarNotValidException(String message) {
		super(message);
	}

}
