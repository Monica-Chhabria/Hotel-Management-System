package com.hotel.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.entities.Review;
import com.hotel.entities.User;
import com.hotel.exception.NoSuchUserException;
import com.hotel.model.input.ReviewRequestModel;
import com.hotel.service.HotelService;
import com.hotel.service.ReviewService;
import com.hotel.service.UserService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	@Value("${app.successmessage}")
    private String successStatusMsg;
	@Value("${app.failuremessage}")
	private String failureStatusMsg;
	@Autowired
	private ReviewService service;
	
	@Autowired
	private UserService userservice;
	
	public String getSuccessStatusMsg() {
		return successStatusMsg;
	}
	public void setSuccessStatusMsg(String successStatusMsg) {
		this.successStatusMsg = successStatusMsg;
	}
	public String getFailureStatusMsg() {
		return failureStatusMsg;
	}
	public void setFailureStatusMsg(String failureStatusMsg) {
		this.failureStatusMsg = failureStatusMsg;
	}
	public ReviewService getService() {
		return service;
	}
	public void setService(ReviewService service) {
		this.service = service;
	}
	
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public HotelService getHotelservice() {
		return hotelservice;
	}
	public void setHotelservice(HotelService hotelservice) {
		this.hotelservice = hotelservice;
	}

	@Autowired
	private HotelService hotelservice;
	@PostMapping(value="/addReview")
	public ResponseEntity<?> addReview(@RequestBody ReviewRequestModel reviewRequestModel)  {
		
		 Optional<User> user =  this.userservice.getUserById(reviewRequestModel.getUserid());

		 Optional<Hotel> hotel =  this.hotelservice.getHotelById(reviewRequestModel.getHotelid());
		 if(user.isPresent() && hotel.isPresent()){
			 reviewRequestModel.getReview().setHotel(hotel.get());

			 reviewRequestModel.getReview().setUser(user.get());
			 //System
		 }
		 
		  Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", this.failureStatusMsg);
		Review retReview = this.service.addRating(reviewRequestModel.getReview());
		if(retReview !=null){
			response.put("status", this.successStatusMsg);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@DeleteMapping(value=("/deleteReview/{reviewid}"),consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteReview(@PathVariable("reviewid") long reviewid)  {
		Map<String, Object> response = new HashMap<String, Object>();
		Optional<Review> review = this.service.getReviewById(reviewid); 
		response.put("status", this.failureStatusMsg);
		if(review.isPresent()){
			this.service.deleteReviewById(reviewid);
			response.put("status", this.successStatusMsg);
		}
		else{
			 throw new NoSuchUserException("Review does not exist in the System.Please provide valid Review id");	

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
