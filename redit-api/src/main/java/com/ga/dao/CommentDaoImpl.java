package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDao postDao;

	@Override
	public Comment createComment(String username, Long postId, Comment comment) {
		
		User author = userDao.getUserByUsername(username);
		
		Post post = postDao.getPost(postId);
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			comment.setAuthor(author);
			comment.setPost(post);
			
			session.save(comment);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return comment;
	}

}
