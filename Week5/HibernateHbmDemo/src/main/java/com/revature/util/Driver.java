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
		CaveDao cd= new CaveDaoImpl();
		BeehiveDao bhd= new BeehiveDaoImpl();
		BearDao bd = new BearDaoImpl();
//		Cave myCave = new Cave("Mammouth Cave");
////		cd.createCave(myCave);
////		System.out.println("my cave called");
//		Cave c= cd.getCaveById(4);
//		System.out.println(c);
		
//		Cave c= new Cave(1, "Not Mammouth Cave");
//		cd.deleteCaveById(1);
		
		
//		cd.updateCave(c);
		
//		Cave myCave= new Cave("Mammoth");
//		cd.createCave(myCave);
//		Cave myCave= new Cave("Jelly Stone Cave");
//		cd.createCave(myCave);
//		Cave myCave= new Cave("Warren's Cave");
//		cd.createCave(myCave);
		
		
		List<Cave> caves= cd.getCaves();
		for(Cave c:caves) {
			System.out.println(c);
		}
		/*
		Beehive b1= new Beehive(25);
		Beehive b2= new Beehive(26);
		Beehive b3= new Beehive(27);
		
		Bear b11= new Bear("Bearnie Mac", Date.valueOf("1984-01-23"),caves.get(0));
		Bear b12= new Bear("Remington", Date.valueOf("1999-03-05"),caves.get(0));
		Bear b13= new Bear("Pooh", Date.valueOf("1990-11-14"),caves.get(1));
		b11.addBeehive(b1);
		b12.addBeehive(b2);
		b13.addBeehive(b3);
		
		
		bhd.createBeehive(b1);
		bhd.createBeehive(b2);
		bhd.createBeehive(b3);
		
		bd.createBear(b11);
		bd.createBear(b12);
		bd.createBear(b13);
		
		*/
			
		List<Bear> bears = bd.getBears();
		for (Bear b:bears) {
			System.out.println(b);
		}
	}
}
