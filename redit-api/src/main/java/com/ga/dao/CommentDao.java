package com.ga.dao;

import com.ga.entity.Comment;

public interface CommentDao {
	public Comment createComment(String username, Long postId, Comment comment);
}
