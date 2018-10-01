package com.everis.firstproject.car.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CAR")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID" )
	private long id;
	@Column(name = "BRAND", nullable = false)
	private String brand;
	@Column(name = "COUNTRY" , nullable = false)
	private String country;
	@Column(name = "CREATED_AT" , nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//private Timestamp created_at;
	private Date created_at;
	@Column(name = "LATEST_UPDATED" , nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//private Timestamp latest_updated;
	private Date latest_updated;
	@Column(name = "REGISTRATION" , nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//private Timestamp registration;
	private Date registration;
	
	
	
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
	public String getBrand() {
		return brand;
	}
	
	/**
	 * It sets the new brand for the car
	 * @param brand String
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * Return the country where the car was made
	 * @return String 
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * It sets a new country for a car
	 * @param country String
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Returns the date when the car was created
	 * @return Date
	 */
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * Sets a new date of creation
	 * @param created_at
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * Returns last time the car was updated
	 * @return Date 
	 */
	public Date getLatest_updated() {
		return latest_updated;
	}

	/**
	 * Sets last time the car is updated
	 * @param latest_updated
	 */
	public void setLatest_updated(Date latest_updated) {
		this.latest_updated = latest_updated;
	}

	/**
	 * Returns when the car was registered
	 * @return Date
	 */
	public Date getRegistration() {
		return registration;
	}

	/**
	 * Set a new date for car registration
	 * @param registration Date
	 */
	public void setRegistration(Date registration) {
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
		this.created_at = car.getCreated_at();
		this.latest_updated = car.getLatest_updated();
	}
	
	
	
	
}