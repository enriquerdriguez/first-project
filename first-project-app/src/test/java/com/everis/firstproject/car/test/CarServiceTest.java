package com.everis.firstproject.car.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when; 
import static org.mockito.Mockito.verify; 

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.everis.firstproject.JMS.Notifier;
import com.everis.firstproject.boundaries.CarService;
import com.everis.firstproject.brand.entity.Brand;
import com.everis.firstproject.car.entity.Car;
import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;
import com.everis.firstproject.country.entity.Country;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@InjectMocks
	CarService carService;
	
	@Mock
	EntityManager em;
	
	@Mock
	Notifier notifier;
	
	@Mock
	Query query;
	
	@Mock
	Car car;
	
	@Mock
	Brand brand;
	
	@Mock
	Country country;
	
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
		
		brand= new Brand();
		country = new Country();
		
		brand.setId(1L);
		brand.setName("brand");
		
		country.setId(1L);
		country.setName("coutry");
	}
	
	@Test
	public final void testGetCarsOK() {
		List<Car> expected= new LinkedList <>();
		expected.add(new Car());
		
		when(em.createNamedQuery("Car.getAll")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCars();
		
		assertEquals(1,cars.size() );
		
		
	}
	
	@Test
	public final void testGetCarsNULL() {
		List<Car> expected= new LinkedList <>();
		
		when(em.createNamedQuery("Car.getAll")).thenReturn(query);
		when(query.getResultList()).thenReturn(null);
		
		List<Car> cars = carService.getCars();
		
		assertEquals(expected,cars );	
		
	}
	
	@Test
	public final void testGetCarsEmpty() {
		List<Car> expected= new LinkedList <>();
		
		when(em.createNamedQuery("Car.getAll")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCars();
		
		assertEquals(0,expected.size());	
		
	}	

	@Test(expected = CarNotFoundException.class)
	public final void testGetCarNull() {
		Car expected = new Car();
		expected.setId(1);
		when(em.find(Car.class, 1)).thenReturn(null);
		
		Car car = carService.getCar(8);
		
		assertEquals(expected, car);
	}
	
	@Test
	public final void testGetCarOK() {
		Car ex = new Car(1,brand,country);
				
		when(em.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(ex);
		Car car = carService.getCar(88L);
		
		assertEquals(car ,ex);
	}
	
	
	@Test
	public final void testGetCarsByCountryOK() {
		List<Car> expected= new LinkedList <>();
		expected.add(new Car());
		
		when(em.createNamedQuery("Car.byCountry")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCarsByCountry("testing");
		
		assertEquals(1,cars.size() );
		
		
	}
	
	@Test
	public final void testGetCarsByCountryNULL() {
		
		when(em.createNamedQuery("Car.byCountry")).thenReturn(query);
		when(query.getResultList()).thenReturn(null);
		
		List<Car> cars = carService.getCarsByCountry("testing");
		
		assertEquals(0,cars.size());	
		
	}
	
	@Test
	public final void testGetCarsByCountryEmpty() {
		List<Car> expected= new LinkedList <>();
		
		when(em.createNamedQuery("Car.byCountry")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCarsByCountry("testing");
		
		assertEquals(0,expected.size());	
		
	}

	@Test
	public final void testGetCarsByBrandOK() {
		List<Car> expected= new LinkedList <>();
		expected.add(new Car());
		
		when(em.createNamedQuery("Car.byBrand")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCarsByBrand("testing");
		
		assertEquals(1,cars.size() );
		
		
	}
	
	@Test
	public final void testGetCarsByBrandNULL() {
		
		when(em.createNamedQuery("Car.byBrand")).thenReturn(query);
		when(query.getResultList()).thenReturn(null);
		
		List<Car> cars = carService.getCarsByBrand("testing");
		
		assertEquals(0,cars.size());	
		
	}
	
	@Test
	public final void testGetCarsByBrandEmpty() {
		List<Car> expected= new LinkedList <>();
		
		when(em.createNamedQuery("Car.byBrand")).thenReturn(query);
		when(query.getResultList()).thenReturn(expected);
		
		List<Car> cars = carService.getCarsByBrand("testing");
		
		assertEquals(0,expected.size());	
		
	}
	
	@Test(expected = NullPointerException.class)
	public final void testCreateWhenCarIsNull() {
		Car car = null;
		
		doNothing().when(em).persist(car);
        carService.createCar(car);
        verify(em).persist(car);
		
	}
	
	@Test
	public final void testDeleteCarWhenCarIsOK() {
		
		Car car = new Car(1,brand,country);
		when(em.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(car);
		//carService.getCar(22L);
		//when(carService.getCar(Matchers.anyLong())).thenReturn(car);
		
        doNothing().when(em).remove(car);
        carService.deleteCar(22L);
        verify(em).remove(car);
	}
	
	@Test(expected = CarNotFoundException.class)
	public final void testDeleteWhenCarIsNull() {
		
		Car car = new Car(1,brand,country);
		Car c = null;
		when(em.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(c);
		//carService.getCar(22L);
		//when(carService.getCar(Matchers.anyLong())).thenReturn(car);
		
        doNothing().when(em).remove(car);
        carService.deleteCar(22L);
        verify(em).remove(car);
	}	
	
	@Test(expected = NullPointerException.class)
	public final void testUpdateCarNULL() {
		
		Car car = null;
		when(em.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(car);
		//carService.getCar(22L);
		
		when(em.merge(car)).thenReturn(car);
        carService.updateCar(car);
        verify(em).merge(car);
		
	}
	
	@Test
	public final void testUpdateCarOK() {
		
		Car car = new Car(1,brand,country);
		when(em.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(car);
		
		when(em.merge(car)).thenReturn(car);
        carService.updateCar(car);
        verify(em).merge(car);
		
	}
	
	
}
