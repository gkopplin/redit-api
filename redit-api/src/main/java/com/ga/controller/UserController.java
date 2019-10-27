package com.ga.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user) {
		 Map<String, Object> result = new HashMap<String,Object>();
		 result.put("username",user.getUsername());
		 result.put("token", new JwtResponse(userService.signup(user)).getToken());
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws LoginException, EntityNotFoundException {
		return new ResponseEntity<Map<String,Object>>(userService.login(user), HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Long userId) {
		return userService.updateUser(user, userId);
	}

}
