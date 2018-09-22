package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class CaveDaoImpl implements CaveDao {

	@Override
	public List<Cave> getCaves() {
		Session s = HibernateUtil.getSession();
		String hql = "from Cave";
		Query q = s.createQuery(hql);
		List<Cave> caves = q.list();
		s.close();
		return caves;
	}

	@Override
	public Cave getCaveById(int id) {
		Session s = HibernateUtil.getSession();
//		Cave c = (Cave) s.load(Cave.class, id);
//		System.out.println(c);
		Cave c = (Cave) s.get(Cave.class, id);
		s.close();
		return c;
	}

	@Override
	public int createCave(Cave c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int cavePK = (int) s.save(c);
		tx.commit();
		s.close();
		return cavePK;
	}

	@Override
	public void updateCave(Cave c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteCaveById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Cave c = (Cave) s.get(Cave.class, id);
		if(c != null) {
			s.delete(c);
		}
		tx.commit();
		s.close();
	}

	
	
}
