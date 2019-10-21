package com.ga.dao;

import com.ga.entity.User;

public interface UserDao {

	User signup(User user);

	User login(User user);
	
	public User getUserByUsername(String username);
	
	public User updateUser(User user, Long userId);

}