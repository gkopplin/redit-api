package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.dao.CommentDao;
import com.ga.dao.PostDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

	@InjectMocks
	PostServiceImpl postService;

	@Mock
	private PostDao postDao;
	
	
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private User user;

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
	
	@Test
	public void createPost_Post_Success() throws Exception {
		when(postDao.createPost(any(), any())).thenReturn(post);
		Post result = postService.createPost("name3", post);
		
		assertNotNull(result);
		assertEquals(result, post);
	}
	
	@Test
	public void getPost_Post_Success() throws Exception {
		when(postDao.getPost(any())).thenReturn(post);
		Post result = postService.getPost(1L);
		
		assertNotNull(result);
		assertEquals(result, post);

	}
	
	@Test
	public void deletePost_Post_Success() throws Exception {
		when(postDao.deletePost(any())).thenReturn(post);
		Post result = postService.deletePost(1L);
		
		assertNotNull(result);
		assertEquals(result, post);
		
	}
	
	@Test
	public void updatePost_Post_Success() throws Exception {
		when(postDao.updatePost(any(), any())).thenReturn(post);
		Post updatedPost = post;
		updatedPost.setBody("updated body");
		
		Post result = postService.updatePost(1L, updatedPost);
		
		assertNotNull(result);
		assertEquals(result, updatedPost);
		
	}
	
	@Test
	public void getAllPosts_PostList_Success() throws Exception {
		List<Post> postsList = new ArrayList<>();
		postsList.add(post);
		
		when(postDao.getAllPosts()).thenReturn(postsList);
		List<Post> result = postService.getAllPosts();
		
		assertNotNull(result);
		assertEquals(result, postsList);
		
	}
}
