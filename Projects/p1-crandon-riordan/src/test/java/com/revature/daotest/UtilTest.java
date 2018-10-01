package com.revature.daotest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.util.Hasher;
import com.revature.util.Validator;

public class UtilTest {
	
	static final Logger logger = Logger.getLogger(UtilTest.class);


	@Test
	public void hashingWorksWithSamePassword() {
		Hasher hasher = new Hasher();
		String password = "theoffice";
		String hashedPassword = hasher.getHashPassword(password);
		assertTrue(hasher.getHashPassword("theoffice").equals(hashedPassword));
	}
	
	@Test
	public void incorrectEmailFails() {
		assertFalse(Validator.validEmail("michaelscott@"));
	}
	
	@Test
	public void correctEmailPasses() {
		assertTrue(Validator.validEmail("michaelscott@gmail.com"));
	}
	
	@Test
	public void complexUsernameFails() {
		assertFalse(Validator.validUsername("hey bro it's me"));
	}
	
	@Test
	public void simpleUsernamePasses() {
		assertTrue(Validator.validUsername("bobifer"));
	}
	
	@Test
	public void shortPasswordFails() {
		assertFalse(Validator.validPassword("hey"));
	}
	
	@Test
	public void spacesInPasswordFails() {
		assertFalse(Validator.validPassword("hey it's me, bro"));
	}
	
	@Test
	public void passwordWithCorrectComplexityPasses() {
		assertTrue(Validator.validPassword("RevaturE1!"));
	}

}
