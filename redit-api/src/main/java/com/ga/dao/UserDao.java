package com.ga.dao;

import javax.persistence.EntityNotFoundException;

import com.ga.entity.User;

public interface UserDao {

	User signup(User user);

	User login(User user) throws EntityNotFoundException;
	
	public User getUserByUsername(String username);
	
	public User updateUser(User user, Long userId);

}