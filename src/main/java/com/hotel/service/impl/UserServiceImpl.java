package com.hotel.service.impl;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.hotel.dao.RatingRepository;
import com.hotel.dao.UserRepository;
import com.hotel.entities.Hotel;
import com.hotel.entities.Review;
import com.hotel.entities.User;
import com.hotel.service.UserService;

@Service
public class UserServiceImpl implements UserService{
  
  @Autowired
  private UserRepository userdao;
	public UserRepository getUserdao() {
	return userdao;
}

public void setUserdao(UserRepository userdao) {
	this.userdao = userdao;
}

	@Override
	public User addUser(User user) {
		User savedUser = this.userdao.save(user);
		return savedUser;
	}

	@Override
	public void deleteUserById(long userid) {
		// TODO Auto-generated method stub
		this.userdao.deleteById(userid);
	}



	@Override
	public User updateUser(User user, long userid) {
		user.setId(userid);
		return this.userdao.save(user);
	}

	@Override
	public Optional<User> getUserById(long userid) {
		// TODO Auto-generated method stub
		Optional<User> user = this.userdao.findById(userid);

		return user;
		
	}

}
