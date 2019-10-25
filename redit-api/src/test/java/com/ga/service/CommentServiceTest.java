package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
	@InjectMocks
	CommentServiceImpl commentService;

	@Mock
	private CommentDao commentDao;
	
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private User user;
	
	@InjectMocks
	private Comment comment;

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
	
	@Test
	public void createComment_Comment_Success() throws Exception {
		when(commentDao.createComment(any(), any(), any())).thenReturn(comment);
		Comment result = commentService.createComment("name3", 1L, comment);
		assertNotNull(result);
		assertEquals(result, comment);
	}
	
	@Test
	public void getComments_CommentList_Success() throws Exception {
		List<Comment> commentsList = new ArrayList<>();
		commentsList.add(comment);
		
		when(commentDao.getComments(any())).thenReturn(commentsList);
		List<Comment> result = commentService.getComments(1L);
		
		assertNotNull(result);
		assertEquals(result, commentsList);
	}
}
