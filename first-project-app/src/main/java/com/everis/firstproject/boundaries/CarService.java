package com.everis.firstproject.boundaries;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.everis.firstproject.car.entity.Car;
import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;

@Named
@Stateless
public class CarService {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<Car> getCars(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery <Car> query = builder.createQuery(Car.class);
		query.select(query.from(Car.class));
		List<Car> cars = (List<Car>) em.createQuery(query).getResultList();
		if(cars == null || cars.size() == 0) {
			return new LinkedList <>();
		}
		return cars;
	}
	
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public Car getCar(long car_id) throws CarNotFoundException {

			Car car = this.em.find(Car.class, car_id);
			if(car == null) {
				throw new CarNotFoundException("Car not found");
			}
			return car;

	}
	
	
	public Car createCar(Car car) throws CarNotValidException {
		try {
		this.em.persist(car);
		this.em.flush();
		this.em.refresh(car);
		}catch(CarNotValidException e) {
			throw e;
		}
		return car;
	}
	
	public Car updateCar(Car car) {
		Car car_to_update = this.getCar(car.getId());
		if (car_to_update == null) {
			throw new CarNotFoundException("Car not found");
		}else {
			car_to_update.update(car);
			this.em.persist(car_to_update);
			this.em.flush();
			this.em.refresh(car_to_update);
			return car_to_update;
		}
	}
	
	public Car deleteCar(long car_id) {
		Car car_toRemove = this.getCar(car_id);
		this.em.remove(car_toRemove);
		return car_toRemove;
	}
	
	
		
	
}