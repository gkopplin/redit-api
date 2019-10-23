package com.ga.service;

import com.ga.entity.Comment;

public interface CommentService {
	public Comment createComment(String username, Long postId, Comment comment);
}
