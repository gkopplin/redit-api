package com.ga.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	UserDao userDao;

	@Override
	public Post createPost(String username, Post post) {

		User author = userDao.getUserByUsername(username);
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			post.setAuthor(author);
			session.save(post);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return post;
	}
	
	
	@Override
	public Post getPost(Long postId) {
		Post post = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			post = (Post)session.createQuery("FROM Post p WHERE p.postId = " + postId).getSingleResult();
		}
		finally {
			session.close();
		}
		
		return post;
		
	}
	
	@Override
	public Post deletePost(Long postId) {
		Post post = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			post = (Post)session.createQuery("FROM Post p WHERE p.postId = " + postId).getSingleResult();
			
			session.delete(post);
			
			session.getTransaction().commit();
	
		}
		finally {
			session.close();
		}
		
		return post;
	}


	@Override
	public Post updatePost(Long postId, Post post) {
		Post updatedPost = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			updatedPost = (Post) session.createQuery("FROM Post p WHERE p.postId = " + postId).getSingleResult();
			
			if (post.getTitle() != null)
				updatedPost.setTitle(post.getTitle());
			
			if (post.getBody() != null)
				updatedPost.setBody(post.getBody());
			
			session.update(updatedPost);
			
			session.getTransaction().commit();
	
		}
		finally {
			session.close();
		}
		
		return updatedPost;
	}


	@Override
	public List<Post> getAllPosts() {
		List<Post> posts = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			posts = (List<Post>) session.createQuery("FROM Post").getResultList();
			
			List<User> authors = new ArrayList<>();
			posts.forEach(post -> authors.add(post.getAuthor()));
			Hibernate.initialize(authors);
			
		}
		finally {
			session.close();
		}
		
		return posts;
	}

}
