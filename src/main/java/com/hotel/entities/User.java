package com.hotel.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
   


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}
/*@JsonBackReference*/
@JsonIgnore
  @OneToMany(mappedBy = "user",cascade= CascadeType.REMOVE)
  private List<Review> reviews;
  
  private String firstName;
  private String lastName;
  private String city;
  public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
	 this.firstName = firstName;
  }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
  public User(long id, List<Review> reviews, String firstName, String lastName, String city, String gender) {
		super();
		this.id = id;
		this.reviews = reviews;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.gender = gender;
	}

public User() {
		super();
		// TODO Auto-generated constructor stub
	}
private String gender;

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
  
}
