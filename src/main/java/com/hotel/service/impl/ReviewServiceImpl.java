package com.hotel.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.RatingRepository;
import com.hotel.entities.Review;
import com.hotel.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private RatingRepository dao;

	@Override
	public void deleteReviewById(long reviewId) {
		this.dao.deleteById(reviewId);
	}

	@Override
	public Review addRating(Review review) {
		return this.dao.save(review);

	}

	@Override
	public Optional<Review> getReviewById(long reviewid) {
		// TODO Auto-generated method stub
		return this.dao.findById(reviewid);
	}

}
