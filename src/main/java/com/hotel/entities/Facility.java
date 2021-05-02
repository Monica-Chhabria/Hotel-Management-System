package com.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Facility {
  @Override
	public String toString() {
		return "Facility [id=" + id + ", wifiAvailable=" + wifiAvailable + ", restaurantAvailable="
				+ restaurantAvailable + ", acAvailable=" + acAvailable + ", mealsIncluded=" + mealsIncluded + ", hotel="
				+ hotel + "]";
	}

@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Facility() {
	super();
	// TODO Auto-generated constructor stub
}

public Facility(int id, String wifiAvailable, String restaurantAvailable, String acAvailable, String mealsIncluded,
		Hotel hotel) {
	super();
	this.id = id;
	this.wifiAvailable = wifiAvailable;
	this.restaurantAvailable = restaurantAvailable;
	this.acAvailable = acAvailable;
	this.mealsIncluded = mealsIncluded;
	this.hotel = hotel;
}

public String getWifiAvailable() {
	return wifiAvailable;
}

public void setWifiAvailable(String wifiAvailable) {
	this.wifiAvailable = wifiAvailable;
}

public String getRestaurantAvailable() {
	return restaurantAvailable;
}

public void setRestaurantAvailable(String restaurantAvailable) {
	this.restaurantAvailable = restaurantAvailable;
}

public String getAcAvailable() {
	return acAvailable;
}

public void setAcAvailable(String acAvailable) {
	this.acAvailable = acAvailable;
}

public String getMealsIncluded() {
	return mealsIncluded;
}

public void setMealsIncluded(String mealsIncluded) {
	this.mealsIncluded = mealsIncluded;
}

public Hotel getHotel() {
	return hotel;
}

public void setHotel(Hotel hotel) {
	this.hotel = hotel;
}

  private String wifiAvailable;
  private String restaurantAvailable;
  public String acAvailable;
  private String mealsIncluded;
  
  @OneToOne
  @JsonBackReference
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;
  
}
