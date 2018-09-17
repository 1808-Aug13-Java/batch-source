package com.revature.util;

import java.util.List;

import org.hibernate.Session;

import com.revature.daos.BearDao;
import com.revature.daos.BearDaoImpl;
import com.revature.models.Bear;

public class Driver {

	public static void main(String[] args) {

		
			Session s =HibernateUtil.getSession();
			s.close();
			
			
//			CaveDao cd= new CaveDaoImpl();
//			BeehiveDao bhd= new BeehiveDaoImpl();
//			BearDao bd = new BearDaoImpl();
			BearDao bd = new BearDaoImpl();
			
//			List<Bear> bears= bd.getBearsByName("Ice Bear");
//			List <Bear> bears=bd.get90andGBears();
			
			
			
					
			
	}
	

}
