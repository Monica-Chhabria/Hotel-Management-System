package com.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Review {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
@ManyToOne  
  @JoinColumn(name = "hotel_id", nullable = false)
 /* @JsonBackReference*/
@JsonIgnore
  private Hotel hotel;
  
  public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
/*@JsonBackReference*/
@JsonIgnore
  private User user;
  
  private float rating;

public Hotel getHotel() {
	return hotel;
}
public void setHotel(Hotel hotel) {
	this.hotel = hotel;
}

public float getRating() {
	return rating;
}
public Review() {
	super();
	// TODO Auto-generated constructor stub
}
public Review(long id, Hotel hotel, User user, float rating, String usercomments) {
	super();
	this.id = id;
	this.hotel = hotel;
	this.user = user;
	this.rating = rating;
	this.usercomments = usercomments;
}
public void setRating(float rating) {
	this.rating = rating;
}
public String getUsercomments() {
	return usercomments;
}
public void setUsercomments(String usercomments) {
	this.usercomments = usercomments;
}
@Lob
  private String usercomments;
  

}
