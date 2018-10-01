package com.everis.firstproject.country.boundaries;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;
import com.everis.firstproject.country.entity.Country;

@Named
@Stateless
public class CountryService {

	@PersistenceContext
	private EntityManager em;
	
	public Country getCountry(long id) {
		Country country = this.em.find(Country.class, id);
		if(country == null) {
			throw new CarNotFoundException("Country not found");
		}
		return country;
	}
	
	public Country createCountry (Country country) {
		try {
			this.em.persist(country);
			this.em.flush();
			this.em.refresh(country);
		}catch(CarNotValidException e) {
			throw e;
		}
		return country;
	}
	
	
}
