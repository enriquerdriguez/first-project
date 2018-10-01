package com.everis.firstproject.brand.boundaries;

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

import com.everis.firstproject.brand.entity.Brand;
import com.everis.firstproject.car.exceptions.CarNotFoundException;

@Path("/brand")
public class BrandResource {

	@Inject
	private BrandService brandService;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "<h1>Hello Brand</h1>";
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getBrand(@PathParam("id") long id) throws CarNotFoundException {
		try {
			Brand b = this.brandService.getBrand(id);
			return Response.status(Status.OK).entity(b).build();
		}catch(CarNotFoundException e) {
			throw e;
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCountry(Brand brand) throws Throwable{
		try {
			Brand b = brandService.createBrand(brand);
			return Response.status(Status.OK).entity(b).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}		
	
}
