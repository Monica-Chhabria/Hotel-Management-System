package com.hotel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 https://github.com/Java-Techie-jt/spring-boot-mockito
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.entities.Facility;
import com.hotel.entities.Hotel;
import com.hotel.entities.Review;
import com.hotel.service.HotelService;
import com.hotel.service.ReviewService;
import com.hotel.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@Transactional
@Rollback*/
public class HotelManagementSystemApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserService userservice;

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

	public ReviewService getReviewservice() {
		return reviewservice;
	}

	public void setReviewservice(ReviewService reviewservice) {
		this.reviewservice = reviewservice;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	public WebApplicationContext getContext() {
		return context;
	}

	public void setContext(WebApplicationContext context) {
		this.context = context;
	}

	public ObjectMapper getOm() {
		return om;
	}

	public void setOm(ObjectMapper om) {
		this.om = om;
	}

	@Autowired
	private HotelService hotelservice;

	@Autowired
	private ReviewService reviewservice;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	
	
	@Test 
	public void BasicJsonTester(){
		assertEquals(1, 1);
	}
	@Test
	public void testAddHotel() throws Exception {
		// employee = new ();
		List<Review> reviews = new ArrayList<Review>();
		Hotel hotel = new Hotel(6, reviews, 9, "Monica", "Mumbai", new Date(), 4,
				new Facility(6, "No", "Yes", "Yes", "Yes", null), new BigDecimal(1000));
		/*
		 * employee.setName("Basant"); employee.setDept("IT");
		 */
		String jsonRequest = om.writeValueAsString(hotel);
		MvcResult result = mockMvc
				.perform(post("/api/addHotel").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();

		JsonFactory factory = om.getFactory();
		JsonParser parser = factory.createParser(resultContent);
		JsonNode actualObj = om.readTree(parser);

		Assert.assertEquals("success", actualObj.get("status").asText());
		System.out.println(resultContent);
		// Response response = om.readValue(resultContent, Response.class);
		// Assert.assertTrue(response.isStatus() == Boolean.TRUE);

	}
	
	@Test
	public void testdeleteHotel() throws Exception {
		// employee = new ();
		/*
		 * employee.setName("Basant"); employee.setDept("IT");
		 */
	
		MvcResult result = mockMvc
				.perform(delete("/api/deleteHotel/13").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();

		JsonFactory factory = om.getFactory();
		JsonParser parser = factory.createParser(resultContent);
		JsonNode actualObj = om.readTree(parser);

		Assert.assertEquals("success", actualObj.get("status").asText());
		//System.out.println(resultContent);
		// Response response = om.readValue(resultContent, Response.class);
		// Assert.assertTrue(response.isStatus() == Boolean.TRUE);

	}

/*	@Test
	public void testtotalCalPerMealPerDay() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/totalCalPerMealPerDay/foo/BreakFast").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();

		JsonFactory factory = om.getFactory();
		JsonParser parser = factory.createParser(resultContent);
		JsonNode actualObj = om.readTree(parser);

		Assert.assertEquals("success", actualObj.get("message").asText());
		// delta - the maximum delta between expected and actual for which both
		// numbers are still considered equal.
		Assert.assertEquals(2000.00, actualObj.get("totalcalpermeal").asDouble(), 2);

		// Assert.assertTrue(response.isStatus() == Boolean.TRUE);

	}*/

}
