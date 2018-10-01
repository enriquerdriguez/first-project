package com.everis.firstproject.car.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CarNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1114024284904025718L;

	public CarNotFoundException(String message) {
		super(message);
	}
}
