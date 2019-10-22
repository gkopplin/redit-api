package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.ga.entity.Post;
import com.ga.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping
	public Post createPost(Authentication auth, @RequestBody Post post) {
		String username = auth.getName();
		return postService.createPost(username, post);
	}
}
