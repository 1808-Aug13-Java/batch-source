package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Bear;

@Repository		// still a bean
public class BearDaoImpl implements BearDao{

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
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
	public void updateBear(Bear b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBearById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bear> getBearsByCave(int caveId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> getBearByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> getSBears() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getBearCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> get90andGBear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bear> get90orGBear() {
		// TODO Auto-generated method stub
		return null;
	}

}
