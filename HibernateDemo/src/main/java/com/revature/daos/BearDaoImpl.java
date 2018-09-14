package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Bear;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	public BearDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Bear> getBears() {
		Session s = HibernateUtil.getSession();
		List<Bear> bears = s.createQuery("from Bear").list();
		s.close();
		return bears;
	}

	@Override
	public Bear getBearById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBear(Bear b) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int bearPK = (int) s.save(b);
		tx.commit();
		s.close();
		return bearPK;
		
	}
	
	@Override
	public List<Bear> getBearsByCave(int id) {
		Session s = HibernateUtil.getSession();
		String hql = "from Bear where cave = :caveVar";
		Query q = s.createQuery(hql);
		q.setInteger("caveVar", id);
		List<Bear> bears = q.list();
		s.close();
		return bears;
	}

}
