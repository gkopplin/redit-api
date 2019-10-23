package com.ga.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ga.entity.User;
import com.ga.service.UserService;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	UserController userController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Mock
	private UserService userService;

	
	@InjectMocks
	private User user;
	
	
	  @Before
	  public void initializeDummyUser() {
	        user.setUserId(1L);
	        user.setUsername("batman");
	        user.setPassword("bat");
	        user.setEmail("batman@email.com");
	  }
	
	
	
	//test signUp
	@Test
	public void signup_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/signup")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson("batman","bat","batman@email.com"));
		
		when(userService.signup(any())).thenReturn("123456");
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"token\":\"123456\"}"))
	              .andReturn();
	      
	      System.out.println(result.getResponse().getContentAsString());
	}
	
	
	
	//create user in Json
	private static String createUserInJson(String username, String password, String email) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"," + "\"email\":\"" + email + "\"}";
    }
    
    
    
    //test login
    @Test
	public void login_User_Success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/login")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson("batman","bat","batman@email.com"));
		
		when(userService.login(any())).thenReturn("123456");
		
		mockMvc.perform(requestBuilder)
          .andExpect(status().isOk())
          .andExpect(content().json("{\"token\":\"123456\"}"));
	}
    
    
    
    
   //test update
   @Test
   public void update_User_Success() throws Exception {
	   User updatedUser = user;
	   updatedUser.setAddress("address");
	   
	   RequestBuilder requestBuilder = MockMvcRequestBuilders
			   .put("/user/1")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(updateUserInJson("address"));
	   
	   
	   when(userService.updateUser((any()), any())).thenReturn(updatedUser);
	   
	   MvcResult result = mockMvc.perform(requestBuilder)
	   .andExpect(status().isOk())
	   .andReturn();
	
	   
	   assertNotNull(result);
	   System.out.println(result.getResponse().getContentAsString());  
   }



   private String updateUserInJson(String address) {
	   return "{ \"address\": \"" + address + "\"}";
   }

    

}
