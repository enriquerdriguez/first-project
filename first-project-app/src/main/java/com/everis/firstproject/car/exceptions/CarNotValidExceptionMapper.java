package com.everis.firstproject.car.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarNotValidExceptionMapper implements ExceptionMapper <CarNotValidException>{
	
	@Override
	public Response toResponse(CarNotValidException ex) {
		
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 500, "");
		return Response.status(Status.BAD_REQUEST).entity(error).build();
		
	}
}
