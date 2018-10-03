package com.everis.firstproject.boundaries;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.everis.firstproject.JMS.Notifier;
import com.everis.firstproject.car.entity.Car;
import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;

@Named
@Stateless
public class CarService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Notifier notifier;
	
	private static final String CAR_NOT_FOUND = "Car not found";
	
	/**
	 * This method returns a List with all the cars from database.
	 * @return List <Car>
	 */
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<Car> getCars(){
		 
		Query query = this.em.createNamedQuery("Car.getAll");
		List <Car> cars = (List<Car>) query.getResultList();
		
		if(cars == null || cars.isEmpty()) {
			return new LinkedList <>();
		}
		return cars;
	}
	
	/**
	 * @param long car_id.
	 * @exception It throws a not found Car exception in case it doesnt find it.
	 * @return This method return an object Car, using the Entity Manager to find it.It also sends notification via JMS
	 */
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public Car getCar(long carId) {
			
			Car car = this.em.find(Car.class, carId);
			
			if(car == null) {
				throw new CarNotFoundException(CAR_NOT_FOUND);
			}else {
				this.notifier.sendNotification(car.getId(),car.getBrand().getName(), "SEARCHED");
			}
			return car;

	}
	
	/**
	 * Returns a list of cars depending on the country received.
	 * @param country
	 * @return
	 */
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<Car> getCarsByCountry(String country){
			
			Query query = this.em.createNamedQuery("Car.byCountry");
			query.setParameter("name",country);
			List <Car> cars = (List <Car>) query.getResultList();
			
			if(cars == null) {
				return new LinkedList <>();
			}
			
			return cars;

	}
	
	/**
	 * Returns a list of cars depending on the brand received.
	 * @param country
	 * @return
	 */	
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<Car> getCarsByBrand(String brand){
			
			Query query = this.em.createNamedQuery("Car.byBrand");
			query.setParameter("name",brand);
			List <Car> cars = (List <Car>) query.getResultList();
			
			if(cars == null) {
				return new LinkedList <>();
			}
			
			return cars;

	}	
	
	/**
	 * @param Car: object type car.
	 * @exception CarNotValidException: that Car is not valid according to the Object structure.
	 * @return Car: returns the created car. It also sends notification via JMS
	 */	
	public Car createCar(Car car) {
		
		//Get current date for registration
		LocalDateTime now = LocalDateTime.now();

	    
		car.setCreatedAt(now);
		car.setRegistration(now);
		
		this.em.persist(car);
		notifier.sendNotification(car.getId(),car.getBrand().getName(), "CREATED");
		return car;
		
	}
	
	/**
	 * @param Car : car to update
	 * @exception CarNotFoundException @see
	 * @return Returns the updated car. It also sends notification via JMS
	 *  
	 */	
	public Car updateCar(Car car) {
		
		LocalDateTime now = LocalDateTime.now();

		Car carToUpdate = this.getCar(car.getId());
		car.setCreatedAt(carToUpdate.getCreatedAt());
		car.setRegistration(carToUpdate.getRegistration());
		car.setLatestUpdated(now);
		carToUpdate.update(car);
		this.em.merge(carToUpdate);
		notifier.sendNotification(car.getId(),car.getBrand().getName(), "UPDATED");
		return carToUpdate;
		
	}
	
	/**
	 * @param long id : Car's id
	 * @return Car that has been deleted. It also sends notification via JMS
	 */
	public Car deleteCar(long carId) {
		
		Car cartoRemove = this.getCar(carId);
		this.em.remove(cartoRemove);
		notifier.sendNotification(cartoRemove.getId(),cartoRemove.getBrand().getName(), "DELETED");
		
		return cartoRemove;
	}
	
	
		
	
}
