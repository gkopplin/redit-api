package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.PostServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PostDaoTest {
	@InjectMocks
	PostDaoImpl postDao;
	
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private User user;
	
	@Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Mock
    UserDao userDao;

    @Mock
    Query<Post> query;
    
	@Before
	public void initializeDummyPost() {
		post.setPostId(1L);
		post.setTitle("title");
		post.setBody("body");
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
	}
	
	@Before
	public void initializeDummyUser() {
		user.setUserId(1L);
		user.setUsername("name3");
	    user.setPassword("pass");
	    user.setEmail("name@domain.com");

	}
	
	@Test
	public void createPost_Post_Success() throws Exception {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		Post result = postDao.createPost("name3", post);
		
		post.setAuthor(user);
		assertNotNull(result);
		assertEquals(result, post);
	}
	
	@Test
	public void getPost_Post_Success() throws Exception {
		when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(post);
		Post result = postDao.getPost(1L);
		
		assertNotNull(result);
		assertEquals(result, post);

	}
	
	@Test
	public void deletePost_Post_Success() throws Exception {
		when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(post);
		Post result = postDao.deletePost(1L);
		
		assertNotNull(result);
		assertEquals(result, post);
		
	}
	
	@Test
	public void updatePost_Post_Success() throws Exception {
		when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(post);
    	
    	Post updatedPost = post;
    	updatedPost.setBody("updated body");
    	
		Post result = postDao.updatePost(1L, post);
		
		assertNotNull(result);
		assertEquals(result, post);
		
	}
	
	@Test
	public void getAllPosts_PostList_Success() throws Exception {
		List<Post> postsList = new ArrayList<>();
		postsList.add(post);
		
		when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getResultList()).thenReturn(postsList);
		List<Post> result = postDao.getAllPosts();
		
		assertNotNull(result);
		assertEquals(result, postsList);
		
	}
}

