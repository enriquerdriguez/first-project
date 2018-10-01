package com.everis.firstproject.brand.boundaries;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.firstproject.brand.entity.Brand;
import com.everis.firstproject.car.exceptions.CarNotFoundException;
import com.everis.firstproject.car.exceptions.CarNotValidException;

@Named
@Stateless
public class BrandService {

	@PersistenceContext
	private EntityManager em;
	
	public Brand getBrand(long id) {
		Brand brand = this.em.find(Brand.class, id);
		if(brand == null) {
			throw new CarNotFoundException("Brand not found");
		}
		return brand;
	}
	
	public Brand createBrand (Brand brand) {
		try {
			this.em.persist(brand);
			this.em.flush();
			this.em.refresh(brand);
		}catch(CarNotValidException e) {
			throw e;
		}
		return brand;
	}
}
