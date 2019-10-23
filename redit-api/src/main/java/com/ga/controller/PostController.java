package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable Long postId) {
		return postService.getPost(postId);
	}
	
	@DeleteMapping("/{postId}")
	public Post deletePost(@PathVariable Long postId) {
		return postService.deletePost(postId);
	}
	
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
		return postService.updatePost(postId, post);
	}
}

		