package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class CommentDaoTest {
	@InjectMocks
	CommentDaoImpl commentDao;
	
	@InjectMocks
	private Comment comment;
	
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
    PostDao postDao;

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
	
	@Before
	public void initializeDummyComment() {
		comment.setCommentId(1L);
		comment.setText("text body");
	}
	
	@Test
	public void createComment_Comment_Success() throws Exception {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		when(postDao.getPost(any())).thenReturn(post);
		Comment result = commentDao.createComment("name3", 1L, comment);
		
		comment.setAuthor(user);
		comment.setPost(post);
		
		assertNotNull(result);
		assertEquals(result, comment);
		
	}
	
	@Test
	public void getComments_CommentList_Success() throws Exception {
		List<Comment> commentsList = new ArrayList<>();
		commentsList.add(comment);
		
		Post commentedPost = post;
		commentedPost.setComments(commentsList);
		
		when(postDao.getPost(any())).thenReturn(post);
		
		List<Comment> result = commentDao.getComments(1L);
		
		assertNotNull(result);
		assertEquals(result, commentsList);
	
	}
}
