package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import com.revature.models.Post;
import com.revature.util.HibernateUtil;

public class PostDaoImpl implements PostDao {

	@Override
	public List<Post> getPosts() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post");
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public Post getPostsByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPost(Post post) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int postId = (int) s.save(post);
		tx.commit();
		s.close();
		return postId;
	}

	

}
