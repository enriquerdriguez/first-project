package com.everis.firstproject.country.boundaries;

import java.util.List;

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
import com.everis.firstproject.car.exceptions.CarNotValidException;
import com.everis.firstproject.country.entity.Country;

@Path("/country")
public class CountryResource {

	@Inject
	private CountryService countryService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List <Country> countries = countryService.getCountry();
		return Response.status(Status.OK).entity(countries).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCountry(@PathParam("id") long id){
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
	public Response createCountry(Country country) {
		try {
			Country countrycreated = countryService.createCountry(country);
			return Response.status(Status.OK).entity(countrycreated).build();
		}catch(CarNotValidException e) {
			throw e;
		}
		
		
	}	
	
}
