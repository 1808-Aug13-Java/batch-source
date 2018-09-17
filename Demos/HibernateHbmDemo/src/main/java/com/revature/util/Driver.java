package com.revature.util;

import java.sql.Date;
import java.util.List;

import com.revature.daos.BearDao;
import com.revature.daos.BearDaoImpl;
import com.revature.daos.BeehiveDao;
import com.revature.daos.BeehiveDaoImpl;
import com.revature.daos.CaveDao;
import com.revature.daos.CaveDaoImpl;
import com.revature.models.Bear;
import com.revature.models.Beehive;
import com.revature.models.Cave;

public class Driver {

	public static void main(String[] args) {
		CaveDao cd = new CaveDaoImpl();
		
		/*
		Cave myCave = new Cave("Mammouth Cave");
		cd.createCave(myCave);
		cd.createCave(new Cave("Jelly Stone Cave"));
		cd.createCave(new Cave("Warren's Cave"));
		*/
		// Cave myCave = new Cave("Mammouth Cave");
		// cd.createCave(myCave);
		
		// Cave c = cd.getCaveById(1);
		// System.out.println(c);
		
		// Cave c = new Cave(1, "Not Mammouth Cave");
		// cd.updateCave(c);
		
		// cd.deleteCaveById(1);
		/*
		List<Cave> caves = cd.getCaves();
		for (Cave c: caves) {
			System.out.println(c);
		}*/
		
		List<Cave> caves = cd.getCaves();
		
		// These objects are in transient state
		BeehiveDao bh = new BeehiveDaoImpl();
		Beehive bh1 = new Beehive(25);
		Beehive bh2 = new Beehive(40);
		Beehive bh3 = new Beehive(10);
		
		Bear b1 = new Bear("Bearnie Mac", Date.valueOf("1984-01-23"), caves.get(0));
		Bear b2 = new Bear("Grizz", Date.valueOf("1991-08-15"), caves.get(0));
		Bear b3 = new Bear("Ice Bear", Date.valueOf("1992-04-02"), caves.get(1));
		
		b1.addBeehive(bh1);
		b1.addBeehive(bh2);
		b2.addBeehive(bh3);
		b3.addBeehive(bh2);
		
		BearDao bd = new BearDaoImpl();
		BeehiveDao bhd = new BeehiveDaoImpl();
		
		// Now we're going to persist them
		bhd.createBeehive(bh1);
		bhd.createBeehive(bh2);
		bhd.createBeehive(bh3);
		
		bd.createBear(b1);
		bd.createBear(b2);
		bd.createBear(b3);
		
		BearDao bd = new BearDaoImpl();
		List<Bear> bears = bd.getBears();
		for (Bear b : bears) {
			System.out.println(b);
		}
	}
}
