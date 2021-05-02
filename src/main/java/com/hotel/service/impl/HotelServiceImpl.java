package com.hotel.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.hotel.dao.HotelRepository;
import com.hotel.entities.Facility;
import com.hotel.entities.Hotel;
import com.hotel.service.HotelService;
import com.hotel.util.HotelSpecificationBuilder;
import com.hotel.util.SearchOperation;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository dao;

	@Override
	public List<Hotel> searchHotelByFilter(String search) {
		HotelSpecificationBuilder builder = new HotelSpecificationBuilder();
		String entityType = "";
		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {

			try {
				Class<?> someClass = Facility.class;
				entityType = "";
				Field someField = someClass.getDeclaredField(matcher.group(1));
				if (someField != null) {
					entityType = "Facility";
				}
			} catch (NoSuchElementException | NoSuchFieldException ex) {
				// ex.printStackTrace();
				entityType = "";
			}
			builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5),
					entityType);
		}
		Sort sort = Sort.by(Sort.Order.desc("averageRating"), Sort.Order.asc("costOfStay"));
		Specification<Hotel> spec = builder.build();
		// Sort.by(Sort.Direction.ASC, "averageRating")
		return dao.findAll(spec, sort);

	}

	@Override
	public List<Hotel> getRatingByFilter(String search, Hotel hotel) {
		HotelSpecificationBuilder builder = new HotelSpecificationBuilder();
		String entityType = "";
		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		entityType = "Rating";
		/*
		 * final String key, final String operation, final Object value
		 */
		Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
		Matcher matcher = pattern.matcher(search + ",");
		builder.with("averageRating", ":", hotel.getAverageRating(), "", "", "");
		builder.with("id", ":", hotel.getId(), "", "", "");
		while (matcher.find()) {

			/*
			 * try{ Class<?> someClass = Facility.class; entityType = ""; Field
			 * someField = someClass.getDeclaredField(matcher.group(1));
			 * if(someField!=null){ entityType="Facility"; } }
			 * catch(NoSuchElementException | NoSuchFieldException ex){
			 * //ex.printStackTrace(); entityType=""; }
			 */
			builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5),
					entityType);
		}
		Sort sort = Sort.by(Sort.Order.desc("averageRating"), Sort.Order.asc("costOfStay"));
		Specification<Hotel> spec = builder.build();
		// Sort.by(Sort.Direction.ASC, "averageRating")
		return dao.findAll(spec, sort);

	}

	@Override
	public Optional<Hotel> getHotelById(long hotelid) {

		Optional<Hotel> hotel = this.dao.findById(hotelid);

		return hotel;
	}

	@Override
	public Hotel addHotel(Hotel hotel) {
		Hotel resHotel = this.dao.save(hotel);
		return resHotel;
	}

	@Override
	public void deleteHotelById(long hotelid) {
		// TODO Auto-generated method stub
		this.dao.deleteById(hotelid);
	}

	@Override
	public Hotel updateHotel(Hotel hotel, long hotelid) {
		// TODO Auto-generated method stub
		hotel.getFacility().setHotel(hotel);
		hotel.setId(hotelid);
		
		this.dao.save(hotel);
		return hotel;
	}

	/*
	 * public List<Hotel> searchHotel(HotelSearchRequestModel
	 * hotelSearchRequestModel) {
	 * 
	 * CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	 * CriteriaQuery<Hotel> criteriaQuery =
	 * criteriaBuilder.createQuery(Hotel.class); Root<Hotel> root =
	 * criteriaQuery.from(Hotel.class);
	 * 
	 * String firstName = hotelSearchRequestModel.getFirstName(); String
	 * lastName = hotelSearchRequestModel.getLastName(); LocalDate
	 * startRangeDateOfBirth = hotelSearchRequestModel.getStartRangeBirthDate();
	 * LocalDate endRangeDateOfBirth =
	 * hotelSearchRequestModel.getEndRangeBirthDate(); Long mobile =
	 * hotelSearchRequestModel.getMobile();
	 * 
	 * 
	 * Adding search criteria's for query using CriteriaBuilder
	 * 
	 * List<Predicate> searchCriterias = new ArrayList<>();
	 * 
	 * if( (firstName != "") && (firstName != null) ) { searchCriterias.add(
	 * criteriaBuilder.like( root.get("firstName"), "%"+firstName+"%") ); } if(
	 * (lastName != "") && (lastName != null) ) { searchCriterias.add(
	 * criteriaBuilder.like( root.get("lastName"), "%"+lastName+"%") ); } if(
	 * startRangeDateOfBirth!=null && endRangeDateOfBirth!=null &&
	 * startRangeDateOfBirth.isAfter(endRangeDateOfBirth) ) {
	 * searchCriterias.add( criteriaBuilder.between( root.get("birthDate"),
	 * startRangeDateOfBirth, endRangeDateOfBirth) ); } if( mobile!=null &&
	 * mobile!=0 ) { searchCriterias.add( criteriaBuilder.equal(
	 * root.get("mobile"), mobile) ); } criteriaQuery.select( root ).where(
	 * criteriaBuilder.and( searchCriterias.toArray(new
	 * Predicate[searchCriterias.size()]) )); return
	 * entityManager.createQuery(criteriaQuery).getResultList(); }
	 */
}
