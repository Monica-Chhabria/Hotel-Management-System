package com.hotel.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Hotel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public Hotel(long id, List<Review> reviews, float averageRating, String name, String city, Date availableDate,
		int noofroomsavailable, Facility facility, BigDecimal costOfStay) {
	super();
	this.id = id;
	this.reviews = reviews;
	this.averageRating = averageRating;
	this.name = name;
	this.city = city;
	this.availableDate = availableDate;
	this.noofroomsavailable = noofroomsavailable;
	this.facility = facility;
	this.costOfStay = costOfStay;
}

@OneToMany(fetch = FetchType.LAZY,mappedBy = "hotel",cascade= CascadeType.REMOVE)
  @JsonIgnore
  List<Review> reviews;
  private float averageRating;
  public float getAverageRating() {
	return averageRating;
  }

  public void setAverageRating(float averageRating) {
	this.averageRating = averageRating;
  }

private String name;
  private String city;
  public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public Date getAvailableDate() {
	return availableDate;
}

public void setAvailableDate(Date availableDate) {
	this.availableDate = availableDate;
}

public int getNoofroomsavailable() {
	return noofroomsavailable;
}

public void setNoofroomsavailable(int noofroomsavailable) {
	this.noofroomsavailable = noofroomsavailable;
}

  private Date availableDate;
  private int noofroomsavailable;
  
  @OneToOne(mappedBy = "hotel",cascade=CascadeType.ALL)
  /*@JsonBackReference*/
 /* @JsonIgnore*/
  private Facility facility;
  


public List<Review> getReviews() {
	return reviews;
}

public void setReviews(List<Review> reviews) {
	this.reviews = reviews;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


public Facility getFacility() {
	return facility;
}

public void setFacility(Facility facility) {
	this.facility = facility;
}

public BigDecimal getCostOfStay() {
	return costOfStay;
}

public Hotel() {
	super();
	// TODO Auto-generated constructor stub
}

public void setCostOfStay(BigDecimal costOfStay) {
	this.costOfStay = costOfStay;
}

private BigDecimal costOfStay;
  
 
}
