package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User signup(User user) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			session.save(user);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();

		User resultUser;

		try {
			session.beginTransaction();
			resultUser = (User) session.createQuery("FROM User u WHERE u.username = '" + user.getUsername()
					+ "'").getSingleResult();
		} finally {
			session.close();
		}

		return resultUser;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user = (User)session.createQuery("FROM User u WHERE u.username = '" + 
				username + "'").uniqueResult();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	@Override
	public User updateUser(User user, Long userId) {
		User savedUser = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();	
			
			savedUser = session.get(User.class, userId);
			savedUser.setPassword(user.getPassword() != null ? user.getPassword() : savedUser.getPassword());
			savedUser.setAddress(user.getAddress() != null ? user.getAddress() : savedUser.getAddress());
			savedUser.setUsername(user.getUsername() != null ? user.getUsername() : savedUser.getUsername());
			savedUser.setMobile(user.getMobile() != null ? user.getMobile() : savedUser.getMobile());
			savedUser.setAddlEmail(user.getAddlEmail() != null? user.getAddlEmail() : savedUser.getAddlEmail());
			savedUser.setEmail(user.getEmail() != null ? user.getEmail() : savedUser.getEmail());
			
			session.update(savedUser);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
		return savedUser;
	}
}
