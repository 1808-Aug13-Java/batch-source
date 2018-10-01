package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Bear;

@Repository
public class BearDaoImpl implements BearDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
//	@Transactional
//	@Transactional(propagation=Propagation.REQUIRED)
	@Transactional(propagation=Propagation.MANDATORY)
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

	@Override
	public List<Bear> getBearsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> getSBears() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getBearCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bear> get90andGBears() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> get90orGBears() {
		// TODO Auto-generated method stub
		return null;
	}

}
