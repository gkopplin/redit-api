package com.ga.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
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

import com.ga.config.AuthUtil;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.PostService;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	PostController postController;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Mock
	private PostService postService;
	
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private User user;
	
	@Mock
	private AuthUtil authUtil;

	@Before
	public void initializeDummyPost() {
		post.setPostId(1L);
		post.setTitle("title");
		post.setBody("body");
		
		user.setUserId(1L);
        user.setUsername("name3");
        user.setPassword("pass");
        user.setEmail("name@domain.com");
        post.setAuthor(user);
	}
	
	private static String createPostInJson(String title, String body) {
		return "{ \"title\": \"" + title + "\", " +
                "\"body\":\"" + body + "\"}";
	   }
	
	@Test
	public void createPost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(createPostInJson("title", "body"));
	

//		when(postService.createPost((any()), any())).thenReturn(post);
//		when(authUtil.getUsername()).thenReturn("name3");
		
//		MvcResult result = mockMvc.perform(requestBuilder)
//				.andExpect(status().isOk()).andReturn();
//
//		assertNotNull(result);
//		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getPost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/1")
				.contentType(MediaType.APPLICATION_JSON);
	

		when(postService.getPost((any()))).thenReturn(post);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		System.out.println(result.getResponse().getContentAsString());
	}
}
