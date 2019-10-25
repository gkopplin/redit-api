package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.exception.LoginException;

public class UserServiceTest {
	@Mock
	UserDao userDao;
	
	@Mock
	private JwtUtil jwtUtil;
	
	@Mock
    private PasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private User user;
	
    @InjectMocks
    private UserServiceImpl userService;
	
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Before 
	public void initializeDummyUser() {
		user.setUserId(1L);
		user.setUsername("batman");
		user.setEmail("batman@ga.com");
		user.setPassword("bat");
	}
	
	@Test
	public void signup_ReturnsJwt_Success() {
		String expectedToken = "12345";
		String expectedUsername = "batman";
		
		when(userDao.signup(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("bat");
        
        String actualToken = userService.signup(user);
        
        assertEquals(actualToken, expectedToken);
	}
	
	 @Test
	    public void signup_UserNotFound_Error() {
	    	User tempUser = user;
	    	tempUser.setUserId(null);

	        when(userDao.signup(any())).thenReturn(tempUser);

	        String token = userService.signup(user);

	        assertEquals(token, null);
	    }
	 
	 @Test
	 public void login_ReturnJwt_Success() throws EntityNotFoundException, LoginException {
		 Map<String, Object> expectedToken = new HashMap<String,Object>();
		    expectedToken.put("token", "12345");
		    expectedToken.put("username", "batman");
		  
		 
		 when(userDao.login(any())).thenReturn(user);
		 when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
		 when(userDao.getUserByUsername(anyString())).thenReturn(user);
		 when(jwtUtil.generateToken(any())).thenReturn((String)expectedToken.get("token"));
	     when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("bat");
		 
	    
	     Map<String, Object> actualToken = userService.login(user);
	        
	     assertEquals(actualToken, expectedToken);
		  
	}
	
	 
	 @Test
	    public void login_UserNotFound_Error() throws EntityNotFoundException, LoginException {
	    	
	    	User tempUser = user;
	    	tempUser.setUserId(null);

	        when(userDao.login(any())).thenReturn(null);
	       
	        try {
	        	assertEquals(userService.login(user), null);
	        }
	        catch(Exception e){
	        	String expectedMessage = "Username/password incorrect.";
	 	        assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
	        }
	        
	   }
	 
	 
	 @Test
	    public void updateUser_User_Success() {
	        when(userDao.updateUser(any(), anyLong())).thenReturn(user);
	        
	        User tempUser = userService.updateUser(user, user.getUserId());

	        assertEquals(tempUser.getUsername(), user.getUsername());
	    }
	    

}
