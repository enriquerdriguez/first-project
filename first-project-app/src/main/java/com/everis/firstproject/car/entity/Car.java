package com.everis.firstproject.car.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID" )
	private BigInteger id;
	@Column(name = "BRAND", nullable = false)
	private String brand;
	@Column(name = "COUNTRY" , nullable = false)
	private String country;
	@Column(name = "CREATED_AT" , nullable = false)
	private Timestamp created_at;
	@Column(name = "LATEST_UPDATED" , nullable = false)
	private Timestamp latest_updated;
	@Column(name = "REGISTRATION" , nullable = false)
	private Timestamp registration;
	
	
	public BigInteger getId() {
		return id;
	}
	
	public void setId(BigInteger id) {
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
	
	public Timestamp getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public Timestamp getLatest_updated() {
		return latest_updated;
	}
	
	public void setLatest_updated(Timestamp latest_updated) {
		this.latest_updated = latest_updated;
	}
	
	public Timestamp getRegistration() {
		return registration;
	}
	
	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}
	
	
	
	
}
