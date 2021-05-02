package com.hotel.service;

import java.util.Optional;

import com.hotel.entities.Review;

public interface ReviewService {
void deleteReviewById(long reviewId);

Review addRating(Review review);

Optional<Review> getReviewById(long reviewid);
}
