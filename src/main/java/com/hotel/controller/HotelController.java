package com.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Facility;
import com.hotel.entities.Hotel;
import com.hotel.exception.NoSuchHotelException;
import com.hotel.model.output.HotelRatingResponseModel;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin
public class HotelController {

	@Value("${app.successmessage}")
	private String successStatusMsg;
	@Value("${app.failuremessage}")
	private String failureStatusMsg;
	@Autowired
	private HotelService service;

	@PostMapping(value = "/addHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
		Map<String, Object> response = new HashMap<String, Object>();
		hotel.getFacility().setHotel(hotel);
		response.put("status", this.failureStatusMsg);
		Hotel retHotel = this.service.addHotel(hotel);
		if (retHotel != null) {
			response.put("status", this.successStatusMsg);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/updateHotel/{hotelid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHotel(@RequestBody Hotel hotel, @PathVariable("hotelid") long hotelid) {
		Map<String, Object> response = new HashMap<String, Object>();
		/* hotel.getFacility().setHotel(hotel); */
		response.put("status", this.failureStatusMsg);

		Hotel retHotel = this.service.updateHotel(hotel, hotelid);
		if (retHotel != null) {
			response.put("status", this.successStatusMsg);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = ("/deleteHotel/{hotelid}"), consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteHotel(@PathVariable("hotelid") long hotelid) {
		Map<String, Object> response = new HashMap<String, Object>();
		Optional<Hotel> hotel = this.service.getHotelById(hotelid);
		response.put("status", this.failureStatusMsg);
		if (hotel.isPresent()) {
			this.service.deleteHotelById(hotelid);
			response.put("status", this.successStatusMsg);
		} else {
			throw new NoSuchHotelException("Hotel does not exist in the System.Please provide valid Hotel id");

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findHotelByFilter")
	public ResponseEntity<?> findHotelsByFilter(@RequestParam(value = "search") String search) {

		List<Hotel> hotels = this.service.searchHotelByFilter(search);
		Map<String, Object> response = new HashMap<String, Object>();

		response.put("hotels", hotels);
		response.put("status", this.successStatusMsg);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getHotel/{hotelid}/{search}")
	public ResponseEntity<?> getHotelRatingWithFilter(@PathVariable("hotelid") String hotelid,
			@PathVariable("search") String searchparam) {

		Optional<Hotel> hotel = this.service.getHotelById(Integer.parseInt(hotelid));
		Hotel hotelfinal = null;
		if (hotel.isPresent()) {
			hotelfinal = hotel.get();
		} else {
			throw new NoSuchHotelException("Hotel does not exist in the System.Please provide valid Hotel id");

		}
		List<Hotel> hotels = this.service.getRatingByFilter(searchparam, hotelfinal);

		Map<String, Object> response = new HashMap<String, Object>();

		Optional<Hotel> optionalHotel = hotels.stream().findFirst();
		if (optionalHotel.isPresent()) {
			hotelfinal = optionalHotel.get();
		} else {
			throw new NoSuchHotelException("No Hotel exists in the System with this filter");

		}
		HotelRatingResponseModel hotelRatingResponseModel = new HotelRatingResponseModel(hotelfinal.getAverageRating(),
				hotelfinal.getReviews());
		response.put("hoteldetails", hotelRatingResponseModel);
		response.put("status", this.successStatusMsg);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getHotel/{hotelid}")
	public ResponseEntity<?> getHotelRating(@PathVariable("hotelid") String hotelid) {
		Optional<Hotel> hotel = this.service.getHotelById(Integer.parseInt(hotelid));
		Hotel hotelfinal = null;
		if (hotel.isPresent()) {
			hotelfinal = hotel.get();
		} else {
			throw new NoSuchHotelException("Hotel does not exist in the System.Please provide valid Hotel id");
		}
		Map<String, Object> response = new HashMap<String, Object>();

		HotelRatingResponseModel hotelRatingResponseModel = new HotelRatingResponseModel(hotelfinal.getAverageRating(),
				hotelfinal.getReviews());
		response.put("hoteldetails", hotelRatingResponseModel);
		response.put("status", this.successStatusMsg);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
