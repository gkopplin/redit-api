package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;

public interface CommentDao {
	public Comment createComment(String username, Long postId, Comment comment);
	
	public List<Comment> getComments(Long postId);
}
