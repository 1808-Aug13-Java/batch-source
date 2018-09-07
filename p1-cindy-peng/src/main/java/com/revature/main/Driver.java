package com.revature.main;

import org.apache.log4j.Logger;

import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Staff;
import com.revature.util.LoggerSingleton;

public class Driver {

//	private static Logger log = Logger getRootLogger
	public static void main(String[] args) {
		Logger log = LoggerSingleton.getLogger();
	//testing getting the connection
//		try 
//			Connection con = ConnectionUtil getConnection()
//			Sysout (con.getMetaDat() getDriverName()  )   //<-- how to do "Oracle JDBC Driver"
//		 catch IOException | SQLException e 
//			log error e
//		 
	//testing logger
//		log info ("hello")
		
	//testing daos
//		String sql = "SELECT * FROM P1_STAFF WHERE NOT STAFF_ID = 1"; 
//		StaffDao staffDao = new StaffDaoImpl();	
//		for(Staff s: staffDao.listStaffMembers(sql))
//			log.info( s );
		
//		log.info( staffDao.getStaffByUserName("cindy") );
		
//		Staff s = staffDao.getStaffByUserName("stanleigh");
//		s.setPassW("passwordchanged");
//		int i = staffDao.updateStaffProfile(s);
//		if( i == 1 )
//			log.info("Updated a table row");
		
	}

}
