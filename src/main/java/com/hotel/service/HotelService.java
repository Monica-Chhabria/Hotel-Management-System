package com.hotel.service;

import com.hotel.entities.Hotel;
import java.util.List;
import java.util.Optional;

public interface HotelService {
  List<Hotel> searchHotelByFilter(String search);
  List<Hotel> getRatingByFilter(String search,Hotel hotel); 
  Optional<Hotel> getHotelById(long hotelid);
  Hotel addHotel(Hotel hotel);
  void deleteHotelById(long hotelid);
  Hotel updateHotel(Hotel hotel,long hotelid);
}
