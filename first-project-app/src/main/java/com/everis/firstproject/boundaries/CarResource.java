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
	
	/**
	 * It returns a Response JSON build of a List with all the cars
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars() {
		List<Car> cars = carService.getCars();
		return Response.status(Status.OK).entity(cars).build();
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
	public Response getCar(@PathParam("id") long id) throws CarNotFoundException {
		try {
			Car car = this.carService.getCar(id);
			return Response.status(Status.OK).entity(car).build();
		}catch(CarNotFoundException e) {
			throw e;
		}
		
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
	public Response createCar(Car car) throws Throwable{
		try {
			Car car_created = carService.createCar(car);
			return Response.status(Status.OK).entity(car_created).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
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
	public Response updateCar(Car car) throws Throwable{
		try {
			Car car_updated = carService.updateCar(car);
			return Response.status(Status.OK).entity(car_updated).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
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
	public Response deleteCar(@PathParam("id") long id) throws Throwable{
		try {
			Car car_deleted = carService.deleteCar(id);
			return Response.status(Status.OK).entity(car_deleted).build();
		}catch(Throwable e) {
			throw e;
		}
		
		
	}
	
}
