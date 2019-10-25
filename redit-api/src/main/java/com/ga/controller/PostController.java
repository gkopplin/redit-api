package com.ga.controller;

import java.util.List;

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

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.service.CommentService;
import com.ga.service.PostService;
import com.ga.util.AuthUtil;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(authUtil.getUsername(), post);
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
	
	@PostMapping("/{postId}/comment")
	public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
		return commentService.createComment(authUtil.getUsername(), postId, comment);
	}
	
	@GetMapping("{postId}/comment")
	public List<Comment> getComments(@PathVariable Long postId){
		return commentService.getComments(postId);
	}
	
	
	@GetMapping("/list")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
}

		