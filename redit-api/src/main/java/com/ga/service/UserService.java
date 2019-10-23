package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;

public interface UserService extends UserDetailsService {

    String signup(User user);

	String logins(User user) throws EntityNotFoundException;
	
	User updateUser(User user, Long userId);
		
}