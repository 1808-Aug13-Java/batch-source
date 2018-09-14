package com.revature.util;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

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

	public Driver() {
		super();
	}
	
	public static void main(String[] args) {
		BearDao bd = new BearDaoImpl();
		
		System.out.println(bd.getBearCount());
	
	}

}
