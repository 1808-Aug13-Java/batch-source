package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBeehive(Beehive bh) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int bhPK = (int) s.save(bh);
		tx.commit();
		s.close();
		return bhPK;
	}

	@Override
	public void updateBeehive(Beehive bh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBeehive(Beehive bh) {
		// TODO Auto-generated method stub
		
	}

}
