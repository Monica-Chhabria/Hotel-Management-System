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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.entities.User;
import com.hotel.exception.NoSuchHotelException;
import com.hotel.exception.NoSuchUserException;
import com.hotel.service.HotelService;
import com.hotel.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Value("${app.successmessage}")
    private String successStatusMsg;
	@Value("${app.failuremessage}")
	private String failureStatusMsg;
	@Autowired
	private UserService service;
	@PostMapping(value="/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user)  {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", this.failureStatusMsg);
		User retUser = this.service.addUser(user);
		if(retUser !=null){
			response.put("status", this.successStatusMsg);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@DeleteMapping(value=("/deleteUser/{userid}"),consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable("userid") long userid)  {
		Map<String, Object> response = new HashMap<String, Object>();
		Optional<User> user = this.service.getUserById(userid); 
		response.put("status", this.failureStatusMsg);
		if(user.isPresent()){
			this.service.deleteUserById(userid);
			response.put("status", this.successStatusMsg);
		}
		else{
			 throw new NoSuchUserException("User does not exist in the System.Please provide valid User id");	

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateUser/{userid}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userid") long userid)  {
		Map<String, Object> response = new HashMap<String, Object>();
		/*hotel.getFacility().setHotel(hotel);*/
		response.put("status", this.failureStatusMsg);
		
		User retUser = this.service.updateUser(user, userid);
		if(retUser !=null){
			response.put("status", this.successStatusMsg);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
