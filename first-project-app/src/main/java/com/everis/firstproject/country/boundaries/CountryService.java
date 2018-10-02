package com.everis.firstproject.country.boundaries;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;
import com.everis.firstproject.country.entity.Country;

@Named
@Stateless
public class CountryService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Country> getCountry(){
		Query query = this.em.createNamedQuery("Country.getAll");
		List <Country> countries = (List<Country>) query.getResultList();
		if (countries == null || countries.isEmpty()) {
			return new LinkedList <>();
		}
		return countries;
	}
	
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
