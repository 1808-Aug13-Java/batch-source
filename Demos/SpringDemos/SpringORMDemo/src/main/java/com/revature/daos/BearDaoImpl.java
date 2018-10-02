package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Bear;

public class BearDaoImpl implements BearDao {
	@Autowired
	private SessionFactory sf;
	@Override
	public List<Bear> getBears() {
		Session s = sf.getCurrentSession();
		List<Bear> bears = s.createQuery("from Bear").list();
		return bears;
	}

	@Override
	public Bear getBearById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBear(Bear b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bear> getBearsByCave(int caveId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
