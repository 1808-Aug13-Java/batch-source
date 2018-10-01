package com.revature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.revature.dao.EmpDaoImpl;
import com.revature.models.Employee;

public final class Hasher {
	
	Logger logger = Logger.getLogger(Hasher.class);
	private final String SALT_PHRASE = "theoffice";
	
	public Hasher() {
		super();
	}
	
	
	
	public String getHashPassword(String password) {
		String saltedPassword = SALT_PHRASE + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}
	
	public Boolean loginByUsername(String username, String password) {
		Boolean isAuthenticated = false;
		EmpDaoImpl edi = new EmpDaoImpl();
		Employee emp = edi.getEmployeeByUsername(username);
		if(emp == null) {
			logger.info("employee couldn't be found to authenticate");
			return false;
		}
		String storedPasswordHash = emp.getPassword();
		// remember to use the same SALT value use used while storing password
		// for the first time.
		String saltedPassword = SALT_PHRASE + password;
		String hashedPassword = generateHash(saltedPassword);

		if(hashedPassword.equals(storedPasswordHash)){
			isAuthenticated = true;
		}else{
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	private String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.info("Error hashing password");
		}

		return hash.toString();
	}

}
