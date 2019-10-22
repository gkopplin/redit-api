package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostDao postDao;

	@Override
	public Post createPost(String username, Post post) {
		return postDao.createPost(username, post);
	}
	
	@Override
	public Post getPost(Long postId) {
		return  postDao.getPost(postId);
	}

}
