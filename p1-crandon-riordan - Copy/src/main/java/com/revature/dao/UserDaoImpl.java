package com.revature.dao;

import org.hibernate.*;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	
	// Retrieving user by Auth0 token
	@Override
	public User getUserById(String id) {
		Session s = HibernateUtil.getSession();
		User user = (User) s.get(User.class, id);
		s.close();
		return user;
		
	}

	// User creation.
	@Override
	public String createUser(User user) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		String userPK = (String) s.save(user);
		tx.commit();
		s.close();
		return userPK;
	}

	
	
}
