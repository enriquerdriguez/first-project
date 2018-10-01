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
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLatest_updated() {
		return latest_updated;
	}

	public void setLatest_updated(Date latest_updated) {
		this.latest_updated = latest_updated;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	
	public void update(Car car) {
		this.brand = car.getBrand();
		this.country = car.getCountry();
		this.registration = car.getRegistration();
		this.created_at = car.getCreated_at();
		this.latest_updated = car.getLatest_updated();
	}
	
	
	
	
}
