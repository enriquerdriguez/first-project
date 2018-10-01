package com.everis.firstproject.car.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider 
public class CarNotFoundExceptionMapper implements ExceptionMapper <CarNotFoundException>{

	@Override
	public Response toResponse(CarNotFoundException ex) {
		
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 404, "");
		return Response.status(Status.NOT_FOUND).entity(error).build();
		
	}
}
