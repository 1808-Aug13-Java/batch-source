package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Bear;
import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	@Override
	public List<Bear> getBears() {
		Session s=HibernateUtil.getSession();
		String hql="from Bear";
		Query q= s.createQuery(hql);
		List<Bear> bears=q.list();
		s.close();
		
		return bears;
	}
	public Bear getBearById (int id) {
		Session s = HibernateUtil.getSession();
//		Cave c=(Cave) s.load(Cave.class, id);
		Bear b=(Bear) s.load(Bear.class, id);
		System.out.println(b);
		s.close();
		
		return b;
	}
	
	public int createBear(Bear b) {
		Session s= HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		int bPK=(int) s.save(b);
		tx.commit();
		s.close();
		return bPK;
	}
	
}
