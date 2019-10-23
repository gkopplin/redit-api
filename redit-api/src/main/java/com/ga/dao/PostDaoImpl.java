package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.config.JwtRequestFilter;
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

}
