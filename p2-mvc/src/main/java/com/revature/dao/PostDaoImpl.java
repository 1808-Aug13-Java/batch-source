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
	public List<Post> getPostsByUserId(String id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post p where p.user.id = :userId");
		q.setString("userId", id);
		List<Post> posts = q.list();
		s.close();
		return posts;
		
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

	@Override
	public List<Post> getPostsByPage(int page) {
		int maxPage = page * 10;
		int minPage = maxPage - 10;
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post");
		q.setFirstResult(minPage);
		q.setMaxResults(maxPage);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByGroupId(int id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post where Post.group.id = :groupId");
		q.setInteger("groupId", id);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByGroupName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post where Post.GROUP_ID.GROUP_NAME = :groupName");
		q.setString("groupName", name);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	

}
