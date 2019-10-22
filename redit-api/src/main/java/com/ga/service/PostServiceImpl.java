package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostDao PostDao;

	@Override
	public Post createPost(String username, Post post) {
		return PostDao.createPost(username, post);
	}

}
