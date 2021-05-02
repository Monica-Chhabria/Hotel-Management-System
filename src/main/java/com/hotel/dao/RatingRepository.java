package com.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entities.Review;
@Repository
public interface RatingRepository extends JpaRepository<Review, Long> {

}
