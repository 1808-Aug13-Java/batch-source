package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Bear;
import com.revature.models.Beehive;
import com.revature.util.HibernateUtil;

public class BeehiveDaoImpl implements BeehiveDao{

	@Override
	public List<Beehive> getBeehives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beehive getBeehiveById(int id) {
		Session s = HibernateUtil.getSession();
//		Cave c=(Cave) s.load(Cave.class, id);
		Beehive b=(Beehive) s.load(Beehive.class, id);
		System.out.println(b);
		s.close();
		return b;
	}

	@Override
	public int createBeehive(Beehive bh) {
		Session s=HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		int bhPK=(int) s.save(bh);
		tx.commit();
		s.close();
		
		return bhPK;
	}
	public void deleteBeehive(Beehive bh) {

	}

}
