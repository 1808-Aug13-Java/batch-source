package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import com.revature.models.Comment;
import com.revature.models.Group;
import com.revature.util.HibernateUtil;

public class CommentDaoImpl implements CommentDao {

	@Override
	public List<Comment> getComments() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Comment");
		List<Comment> comments = q.list();
		s.close();
		return comments;
	}
	
	@Override
	public Comment getCommentById(int id) {
		Session s = HibernateUtil.getSession();
		Comment c = (Comment) s.get(Comment.class, id);
		s.close();
		return c;
	}

	@Override
	public List<Comment> getCommentsByUserId(String id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Comment where Comment.USER_ID = :userId");
		q.setString("userId", id);
		List<Comment> comments = q.list();
		s.close();
		return comments;
	}

	@Override
	public int createComment(Comment comment) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int commentPK = (int) s.save(comment);
		tx.commit();
		s.close();
		return commentPK;
	}
}