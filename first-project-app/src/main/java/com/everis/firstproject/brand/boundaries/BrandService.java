package com.everis.firstproject.brand.boundaries;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.everis.firstproject.brand.entity.Brand;
import com.everis.firstproject.car.exceptions.CarNotFoundException;

@Named
@Stateless
public class BrandService {

	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public List<Brand> getBrands(){
		Query query = this.em.createNamedQuery("Brand.getAll");
		List <Brand> brands = (List<Brand>) query.getResultList();
		if(brands == null || brands.isEmpty()) {
			return new LinkedList <>();
		}
		return brands;
		
	}
	
	@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public Brand getBrand(long id) {
		Brand brand = this.em.find(Brand.class, id);
		if(brand == null) {
			throw new CarNotFoundException("Brand not found");
		}
		return brand;
	}
	
	public Brand createBrand (Brand brand) {
		this.em.persist(brand);
		this.em.flush();
		this.em.refresh(brand);
		return brand;
	}
}
