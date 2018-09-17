package com.revature.util;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.dao.BearDao;
import com.revature.dao.BearDaoImpl;
import com.revature.dao.BeehiveDao;
import com.revature.dao.BeehiveDaoImpl;
import com.revature.dao.CaveDao;
import com.revature.dao.CaveDaoImpl;
import com.revature.models.Bear;
import com.revature.models.Beehive;
import com.revature.models.Cave;

public class Driver {

	public static void main(String[] args) {
	/*
		Session s = HibernateUtil.getSession();
		s.close();
	*/
	

		CaveDao cd = new CaveDaoImpl();

		cd.createCave(new Cave("Jelly Stone Cave"));
		cd.createCave(new Cave("Mammouth Cave"));
		cd.createCave(new Cave("Meramec Caverns"));
		cd.createCave(new Cave("Warren's Cave"));
		
		List<Cave> caves = cd.getCaves();
		for (Cave c : caves) {
			System.out.println(c);
		}

		
		BeehiveDao bhd = new BeehiveDaoImpl();
		Beehive bh1 = new Beehive(25);
		Beehive bh2 = new Beehive(40);
		Beehive bh3 = new Beehive(32);
		

		Bear b1 = new Bear("Bearnie Mac", Date.valueOf("1984-01-23"), caves.get(0));
		Bear b2 = new Bear("Grizz", Date.valueOf("1991-08-15"), caves.get(0));
		Bear b3 = new Bear("Ice Bear", Date.valueOf("1992-04-02"), caves.get(1));

		
		b1.addBeehive(bh1);
		b1.addBeehive(bh2);
		b2.addBeehive(bh2);
		b3.addBeehive(bh3);
		b3.addBeehive(bh2);
		

		System.out.println(b1.toString());
		BearDao bd = new BearDaoImpl();

		bd.createBear(b1);
		bd.createBear(b2);
		bd.createBear(b3);
		
		BearDao bdi = new BearDaoImpl();
		List <Bear> bears = bdi.getBearsByCave(7);
		for(Bear b : bears) {
			System.out.println(b);
		}
		
		bdi.getBearsByName("Ice Bear");
		System.out.println(bears);
		
		System.out.println(bdi.getBearCount());

	}

}
