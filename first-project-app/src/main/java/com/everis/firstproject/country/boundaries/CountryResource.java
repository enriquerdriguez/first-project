package com.everis.firstproject.country.boundaries;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.country.entity.Country;

@Path("/country")
public class CountryResource {

	@Inject
	private CountryService countryService;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "<h1>Hello Country</h1>";
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCountry(@PathParam("id") long id) throws CarNotFoundException {
		try {
			Country country = this.countryService.getCountry(id);
			return Response.status(Status.OK).entity(country).build();
		}catch(CarNotFoundException e) {
			throw e;
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCountry(Country country) throws Throwable{
		try {
			Country c_created = countryService.createCountry(country);
			return Response.status(Status.OK).entity(c_created).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}	
	
}
