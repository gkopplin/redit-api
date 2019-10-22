package com.ga.dao;

import com.ga.entity.Post;

public interface PostDao {
	public Post createPost(String username, Post post);
	
	public Post getPost(Long postId);
}
