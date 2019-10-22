package com.ga.service;

import com.ga.entity.Post;

public interface PostService {
	public Post createPost(String username, Post post);
	
	public Post getPost(Long postId);
}
