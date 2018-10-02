package com.everis.firstproject.brand.boundaries;

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

import com.everis.firstproject.brand.entity.Brand;

@Path("brand")
public class BrandResource {

	@Inject
	private BrandService brandService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Brand> brands = brandService.getBrands();
		return Response.status(Status.OK).entity(brands).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getBrand(@PathParam("id") long id) {
			Brand b = this.brandService.getBrand(id);
			return Response.status(Status.OK).entity(b).build();
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCountry(Brand brand){
			Brand b = brandService.createBrand(brand);
			return Response.status(Status.OK).entity(b).build();

		
		
	}		
	
}
