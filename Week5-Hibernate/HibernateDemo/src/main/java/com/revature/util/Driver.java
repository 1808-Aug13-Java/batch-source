package com.revature.util;

import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.revature.daos.BearDao;
import com.revature.daos.BearDaoImpl;
import com.revature.models.Bear;

public class Driver {

	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		s.close();
		
		/*
		CaveDao cd = new CaveDaoImpl();
		Cave myCave = new Cave("Mammouth Cave");
		cd.createCave(myCave);
		cd.createCave(new Cave("Jelly Stone Cave"));
		cd.createCave(new Cave("Warren's Cave"));
		
		Beehive bh1 = new Beehive(25);
		Beehive bh2 = new Beehive(40);
		Beehive bh3 = new Beehive(10);
		
		List<Cave> caves = cd.getCaves();
		Bear b1 = new Bear("Bearnie Mac",Date.valueOf("1984-01-23"), caves.get(0));
		Bear b2 = new Bear("Grizz", Date.valueOf("1991-08-15"), caves.get(0));
		Bear b3 = new Bear("Ice Bear", Date.valueOf("1992-04-02"),caves.get(1));
		
		b1.addBeehive(bh1);
		b1.addBeehive(bh2);
		b2.addBeehive(bh3);
		b3.addBeehive(bh2);
		
		BearDao bd = new BearDaoImpl();
		BeehiveDao bhd = new BeehiveDaoImpl();
		
		bhd.createBeehive(bh1);
		bhd.createBeehive(bh2);
		bhd.createBeehive(bh3);
		
		bd.createBear(b1);
		bd.createBear(b2);
		bd.createBear(b3);
		*/
		
		BearDao bd = new BearDaoImpl();
		//List<Bear> bears = bd.getBearsByName("Ice Bear");
//		List<Bear> bears = bd.getSBears();
//		System.out.println(bears);
//		System.out.println(bd.getBearCount());
		//List<Bear> bears = bd.get90andGBears();
//		List<Bear> bears = bd.get90orGBears();
		List<Bear> bears = bd.getBearsByCave(7);
		for(Bear b: bears) {
			System.out.println(b);
		}
	}

}
