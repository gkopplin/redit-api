package com.ga.dao;

import java.util.List;

import com.ga.entity.Post;

public interface PostDao {
	public Post createPost(String username, Post post);
	
	public Post getPost(Long postId);
	
	public Post deletePost(Long postId);
	
	public Post updatePost(Long postId, Post post);
	
	public List<Post> getAllPosts();
	
}
