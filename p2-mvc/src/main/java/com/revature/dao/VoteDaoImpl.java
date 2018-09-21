package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Comment;
import com.revature.models.Vote;
import com.revature.util.HibernateUtil;

public class VoteDaoImpl implements VoteDao {

	@Override
	public List<Vote> getVotes() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Vote");
		List<Vote> votes = q.list();
		s.close();
		return votes;
	}
	
	@Override
	public Vote getVoteById(int id) {
		Session s = HibernateUtil.getSession();
		Vote v = (Vote) s.get(Vote.class, id);
		s.close();
		return v;
	}

	@Override
	public List<Vote> getVotesByUserId(String id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Vote where Vote.USER_ID = :userId");
		q.setString("userId", id);
		List<Vote> votes = q.list();
		s.close();
		return votes;
	}

	@Override
	public int createVote(Vote vote) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int votePK = (int) s.save(vote);
		tx.commit();
		s.close();
		return votePK;
	}
}