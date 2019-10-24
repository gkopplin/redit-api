package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;

public interface CommentService {
	public Comment createComment(String username, Long postId, Comment comment);
	
	public List<Comment> getComments(Long postId);
}
