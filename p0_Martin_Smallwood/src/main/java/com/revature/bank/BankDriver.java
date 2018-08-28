package com.revature.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.BankUserDaoImpl;
import com.revature.model.BankUser;
import com.revature.util.ConnectionUtil;

public class BankDriver {

	public static void main(String[] args) {
		Logger log = Logger.getRootLogger();
		Connection con=null;
		try {
			con = ConnectionUtil.getConnection();
			log.info(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			log.error(e);
		}
		
		Commands com = new Commands(con);
		BankUserDaoImpl bdi = new BankUserDaoImpl();
		List<BankUser> bul = bdi.getBankUsers();
		for(BankUser b: bul) {
			log.info(b);
		}
		while(!com.isLoggedIn())
				com.logIn();
		while(com.isLoggedIn()) {
			com.menu();
		}
		
	}
}
