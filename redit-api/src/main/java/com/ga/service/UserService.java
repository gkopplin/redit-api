package com.ga.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;
import com.ga.exception.LoginException;

public interface UserService extends UserDetailsService {

    String signup(User user);

	String login(User user) throws LoginException,EntityNotFoundException;
	
	User updateUser(User user, Long userId);
		
}