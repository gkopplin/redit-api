package com.ga.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.CommentService;
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
	
	@Mock
	private CommentService commentService;
	
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private User user;
	
	@InjectMocks
	private Comment comment;
	
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
	
	@Before
	public void initializeDummyComment() {
		comment.setCommentId(1L);
		comment.setText("text body");
		
		user.setUserId(1L);
        user.setUsername("name3");
        user.setPassword("pass");
        user.setEmail("name@domain.com");
        
        post.setPostId(1L);
		post.setTitle("title");
		post.setBody("body");
        
        comment.setAuthor(user);
        comment.setPost(post);
	}
	
	
	private static String createPostInJson(String title, String body) {
		return "{ \"title\": \"" + title + "\", " +
                "\"body\":\"" + body + "\"}";
	   }
	
	private static String createCommentInJson(String text) {
		return "{ \"text\": \"" + text + "\"}";
	   }
	
	@Test
	public void createPost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createPostInJson("title", "body"));
	

		when(postService.createPost((any()), any())).thenReturn(post);
		when(authUtil.getUsername()).thenReturn("name3");
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"postId\":1,\"title\":\"title\",\"body\":\"body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null},\"comments\":null}"))
				.andReturn();
	}
	
	@Test
	public void getPost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/post/1")
				.contentType(MediaType.APPLICATION_JSON);
	

		when(postService.getPost((any()))).thenReturn(post);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"postId\":1,\"title\":\"title\",\"body\":\"body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null},\"comments\":null}"))
				.andReturn();
	}
	
	@Test
	public void deletePost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/post/1")
				.contentType(MediaType.APPLICATION_JSON);
	

		when(postService.deletePost((any()))).thenReturn(post);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"postId\":1,\"title\":\"title\",\"body\":\"body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null},\"comments\":null}"))
				.andReturn();
	}
	
	@Test
	public void updatePost_Post_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/post/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createPostInJson("updated title", "body"));
		
		Post updatedPost = post;
		updatedPost.setTitle("updated title");
	

		when(postService.updatePost((any()), any())).thenReturn(updatedPost);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"postId\":1,\"title\":\"updated title\",\"body\":\"body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null},\"comments\":null}"))
				.andReturn();
	}
	
	@Test
	public void createComment_Comment_Success() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/post/1/comment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createCommentInJson("text body"));
	
		Comment temp = comment;
		when(commentService.createComment((any()), any(), any())).thenReturn(comment);
		when(authUtil.getUsername()).thenReturn("name3");
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"commentId\":1,\"text\":\"text body\",\"author\":{\"userId\":1,\"username\":\"name3\",\"password\":\"pass\",\"email\":\"name@domain.com\",\"address\":null,\"mobile\":null,\"addlEmail\":null}}"))
				.andReturn();
	}
}
