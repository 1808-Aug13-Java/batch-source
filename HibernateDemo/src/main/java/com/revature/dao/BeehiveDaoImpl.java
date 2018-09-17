package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Beehive;
import com.revature.util.HibernateUtil;

public class BeehiveDaoImpl implements BeehiveDao {

	@Override
	public List<Beehive> getBeehives() {
		Session s = HibernateUtil.getSession();
		String hql = "from Beehive";
		Query q = s.createQuery(hql);
		List<Beehive> beehives = q.list();
		s.close();
		return beehives;
	}

	@Override
	public Beehive getBeehiveById(int id) {
		Session s = HibernateUtil.getSession();
		Beehive bh = (Beehive) s.get(Beehive.class, id);
		return bh;
	}

	@Override
	public int createBeehive(Beehive bh) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int beehivePK = (int) s.save(bh);
		tx.commit();
		s.close();
		return beehivePK;
	}

	@Override
	public void updateBeehive(Beehive bh) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(bh);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteBeehive(Beehive bh) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(bh);
		tx.commit();
		s.close();
	}
}
