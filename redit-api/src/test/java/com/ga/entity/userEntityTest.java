package com.ga.entity;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;



public class userEntityTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private User user;
	
	
	@Before
	public void init() {
		List<Comment> comments = new ArrayList<Comment>();
		List<Post> posts = new ArrayList<Post>();
		
		user.setUserId(1L);
		user.setUsername("batman");
		user.setEmail("batman@ga.com");
		user.setAddress("batman st");
		user.setMobile("000-000-0000");
		user.setAddlEmail("batman2@ga.com");
		user.setPassword("bat");
		user.setComments(comments);
		user.setPosts(posts);
	}
	
	@Test
	public void getUserId_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getUserId());
	}
	
	public void getUsername_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getUsername());
	}
	
	public void getEmail_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getEmail());
	}
	
	public void getAddress_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getAddress());
	}
	
	public void getMobile_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getMobile());
	}
	
	public void getAddlEmail_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getAddlEmail());
	}
	
	public void getPassword_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getPassword());
	}
	
	public void getComments_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getComments());
	}
	
	public void getPosts_Success() {
		assertNotNull("Test returned null object, expected non-null", user.getPosts());
	}
}
