package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment createComment(String username, Long postId, Comment comment) {
		return commentDao.createComment(username, postId, comment);
	}

	@Override
	public List<Comment> getComments(Long postId) {
		return commentDao.getComments(postId);
	}
	
	

}
