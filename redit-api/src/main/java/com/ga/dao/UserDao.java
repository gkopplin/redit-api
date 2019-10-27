package com.ga.dao;

import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;

public interface UserDao {

	User signup(User user);

	User login(User user) throws EntityNotFoundException;
	
	public User getUserByUsername(String username);
	
	public User updateUser(User user, Long userId);

}