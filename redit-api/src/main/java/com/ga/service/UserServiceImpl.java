package com.ga.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.exception.LoginException;
import com.ga.util.JwtUtil;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public String signup(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		if(userDao.signup(user).getUserId() != null) {
		    UserDetails userDetails = loadUserByUsername(user.getUsername());
		    
		    return jwtUtil.generateToken(userDetails);
		}
		
		return null;
	}
	
	@Autowired
    JwtUtil jwtUtil;

	@Override
	public Map<String, Object> login(User user) throws LoginException,EntityNotFoundException {
		User foundUser = userDao.login(user);
		if(foundUser != null && 
				foundUser.getUserId() != null && 
				bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
		    UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
		    
		    Map<String, Object> result = new HashMap<String,Object>();
		    result.put("token", jwtUtil.generateToken(userDetails));
		    result.put("username", foundUser.getUsername());
		    return result;
		}
		
		throw new LoginException("Username/password incorrect.");
	}
	
	@Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);

        if(user==null)
            throw new UsernameNotFoundException("Unknown user: " +username);
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, authorities);
    }

	@Override
	public User updateUser(User user, Long userId) {
		return userDao.updateUser(user, userId);
	}
}
