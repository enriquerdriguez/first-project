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

@Path("cars")
public class CarResource {

	@Inject
	private CarService carService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars() {
		List<Car> cars = carService.getCars();
		return Response.status(Status.OK).entity(cars).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCar(@PathParam("id") long id) throws CarNotFoundException {
		try {
			Car car = this.carService.getCar(id);
			return Response.status(Status.OK).entity(car).build();
		}catch(CarNotFoundException e) {
			throw e;
		}
		
	}	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCar(Car car) throws Throwable{
		try {
			Car car_created = carService.createCar(car);
			return Response.status(Status.OK).entity(car_created).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dCar(Car car) throws Throwable{
		try {
			Car car_updated = carService.updateCar(car);
			return Response.status(Status.OK).entity(car_updated).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteCar(@PathParam("id") long id) throws Throwable{
		try {
			Car car_deleted = carService.deleteCar(id);
			return Response.status(Status.OK).entity(car_deleted).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}
	
}
