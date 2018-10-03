package com.everis.firstproject.car.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.everis.firstproject.brand.entity.Brand;
import com.everis.firstproject.country.entity.Country;
import com.everis.javaconverter.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "CAR")
@NamedQueries({
	@NamedQuery(name = "Car.getAll", query = "SELECT c FROM Car c"),
	@NamedQuery(name = "Car.byCountry", query = "SELECT c FROM Car c JOIN c.country co WHERE co.name = :name"),
	@NamedQuery(name = "Car.byBrand", query = "SELECT c FROM Car c JOIN c.brand br WHERE br.name = :name")
})
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID" )
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@Column(name = "CREATED_AT" , nullable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createdAt;
	
	@Column(name = "LATEST_UPDATED" , nullable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime latestUpdated;
	
	@Column(name = "REGISTRATION" , nullable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime registration;

	
	public Car(){
		
	}
	
	
	public Car(long id, Brand brand, Country country) {

        this.id= id;
        this.brand = brand;
        this.country = country;
        this.registration = null;
        this.latestUpdated = null;
        this.createdAt = null;
    }
    
	
	/**
	 * Return Car's id
	 * @return id 
	 *
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets Car's id 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Returns Car's brand
	 * @return
	 */
	public Brand getBrand() {
		return brand;
	}
	
	/**
	 * It sets the new brand for the car
	 * @param brand String
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	/**
	 * Return the country where the car was made
	 * @return String 
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * It sets a new country for a car
	 * @param country String
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Returns the date when the car was created
	 * @return Date
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets a new date of creation
	 * @param created_at
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Returns last time the car was updated
	 * @return Date 
	 */
	public LocalDateTime getLatestUpdated() {
		return latestUpdated;
	}

	/**
	 * Sets last time the car is updated
	 * @param latest_updated
	 */
	public void setLatestUpdated(LocalDateTime latestUpdated) {
		this.latestUpdated = latestUpdated;
	}

	/**
	 * Returns when the car was registered
	 * @return Date
	 */
	public LocalDateTime getRegistration() {
		return registration;
	}

	/**
	 * Set a new date for car registration
	 * @param registration Date
	 */
	public void setRegistration(LocalDateTime registration) {
		this.registration = registration;
	}
	
	/**
	 * It sets all properties of a Car to update it
	 * @param Car car
	 */
	public void update(Car car) {
		this.brand = car.getBrand();
		this.country = car.getCountry();
		this.registration = car.getRegistration();
		this.createdAt = car.getCreatedAt();
		this.latestUpdated = car.getLatestUpdated();
	}
	
	
	
	
}