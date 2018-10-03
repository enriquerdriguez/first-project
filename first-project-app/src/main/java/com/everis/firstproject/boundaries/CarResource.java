package com.everis.firstproject.boundaries;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.everis.firstproject.car.entity.Car;
import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;
import com.everis.logger.Logged;

@Logged
@Path("cars")
public class CarResource {

	@Inject
	private CarService carService;
	
	/**
	 * It returns a Response JSON build of a List with all the cars
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars() {
		List<Car> cars = carService.getCars();
		if(cars != null) {
			return Response.status(Status.OK).entity(cars).build();			
		}else {
			return Response.status(204).entity(cars).build();			
		}
	}
	
	/**
	 * It gets a car according to the id read on the URL. Throws a not found exception if case is given.
	 * @param id
	 * @return
	 * @throws CarNotFoundException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCar(@PathParam("id") long id) {
		Response response;
		try {
			Car car = this.carService.getCar(id);
			if(car != null) {
				response = Response.status(Status.OK).entity(car).build();				
			}else {
				response = Response.status(204).build();				
			}
		}catch(CarNotFoundException e) {
			response = Response.status(404).build();
		}
		
		return response;
		
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/country/{country}")
	public Response getCarsByCountry(@PathParam("country") String country) {
		Response response;
		try {
			List<Car> cars = carService.getCarsByCountry(country.toUpperCase());
			response = Response.status(Status.OK).entity(cars).build();				
		}catch(CarNotFoundException e) {
			response = Response.status(500).build();
		}
		return response;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/brand/{brand}")
	public Response getCarsByBrand(@PathParam("brand") String brand){
		Response response;
		try {
			List<Car> cars = carService.getCarsByBrand(brand.toUpperCase());
			response = Response.status(Status.OK).entity(cars).build();				
		}catch(CarNotFoundException e) {
			response = Response.status(500).build();
		}
		return response;
	}	
	
	/**
	 * Creates a Car and persist it on a database.
	 * @param car
	 * @return
	 * @throws Throwable
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCar(Car car){
		Response response;
		try {
			Car carCreated = carService.createCar(car);
			if(carCreated != null) {
				response =  Response.status(201).entity(carCreated).build();				
			}else {
				response =  Response.status(204).entity(carCreated).build();
			}
		}catch(CarNotValidException e) {
			response = Response.status(400).build();
		}
		
		return response;
		
	}
	
	/**
	 * Updates a car and persist it on a database
	 * @param car
	 * @return
	 * @throws Throwable
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car){
		Response response;
		try {
			Car carUpdated = carService.updateCar(car);
			if(carUpdated != null) {
				response = Response.status(201).entity(carUpdated).build();
			}else {
				response = Response.status(204).entity(carUpdated).build();				
			}
		}catch(CarNotFoundException e) {
			response = Response.status(400).build();
		}
		
		return response;
		
	}
	
	/**
	 * Deletes a car from the database. It needs the id which is read on the URL
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteCar(@PathParam("id") long id){
		Response response;
		try {
			Car carDeleted = carService.deleteCar(id);
			if(carDeleted != null) {
				response = Response.status(201).entity(carDeleted).build();				
			}else {				
				response = Response.status(204).entity(carDeleted).build();				
			}
		}catch(CarNotFoundException e) {
			response = Response.status(400).build();				
		}
		return response;
	}
	
}
