package com.hotel.model.output;

import com.hotel.entities.Review;
import java.util.List;

public class HotelRatingResponseModel {

	private float averageRating;

	public float getAverageRating() {
		return averageRating;
	}

	public HotelRatingResponseModel(float averageRating, List<Review> review) {
		super();
		this.averageRating = averageRating;
		this.review = review;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	private List<Review> review;
}
