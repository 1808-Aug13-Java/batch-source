package com.revature.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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

	@Override
	public List<Bear> getBearsByName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM BEAR WHERE BEAR_NAME = ?").addEntity(Bear.class);
		q.setString(0, name);
		List<Bear> bears = q.list();
		s.close();
		return bears;
	}

	@Override
	public List<Bear> getSBears() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Bear.class);
		c.add(Restrictions.like("name", "S%"));
		c.addOrder(Order.asc("name"));
		List<Bear> bears = c.list();
		s.close();
		return bears;
	}

	@Override
	public long getBearCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Bear.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return rows.get(0);
	}

}
