package com.hotel.service;
import com.hotel.entities.User;
import java.util.Optional;


public interface UserService {
  User addUser(User user);
  
  void deleteUserById(long userid);
  
  Optional<User> getUserById(long userid);
  
  User updateUser(User user,long userid);
  
}
