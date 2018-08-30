import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.dao.BankDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Bank;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.validation.Validator;

public class ServiceLayerTest {
	
	static final Logger logger = Logger.getLogger(ServiceLayerTest.class);
	
	@Test
	public void usernameNotValidEmail() {
		boolean isValidEmail = Validator.validEmail("michaelscott");
		assertFalse(isValidEmail);
	}
	
	@Test
	public void nameWithAtSignNotValidEmail() {
		boolean isValidEmail = Validator.validEmail("mich1234@");
		assertFalse(isValidEmail);
	}
	
	@Test
	public void nameWithAtSignAndNoDomainNameNotValidEmail() {
		boolean isValidEmail = Validator.validEmail("mich1234@google");
		assertFalse(isValidEmail);
	}
	
	@Test
	public void nameWithAtSignAndNoDomainWithDotNotValidEmail() {
		boolean isValidEmail = Validator.validEmail("mich1234@google.");
		assertFalse(isValidEmail);
	}
	
	@Test
	public void emailWithProperDomainNameIsValid() {
		boolean isValidEmail = Validator.validEmail("mich1234@google.com");
		assertTrue(isValidEmail);
	}
	
	@Test
	public void shortUsernameNotValid() {
		boolean isValidUsername = Validator.validUsername("mit");
		assertFalse(isValidUsername);
	}
	
	@Test
	public void usernameWithSpecialCharactersNotValid() {
		boolean isValidUsername = Validator.validUsername("mit@!!!");
		assertFalse(isValidUsername);
	}
	
	@Test
	public void usernameWithProperLengthIsValid() {
		boolean isValidUsername = Validator.validUsername("mitchup");
		assertTrue(isValidUsername);
	}
	
	@Test
	public void shortPasswordIsInvalid() {
		boolean isValidPassword = Validator.validPassword("123");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void shortComplexPasswordIsInvalid() {
		boolean isValidPassword = Validator.validPassword("ac@1s");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void longComplexPasswordIsValid() {
		boolean isValidPassword = Validator.validPassword("RevaturE1!");
		assertTrue(isValidPassword);
	}
	
	@Test
	public void incorrectScaleIsInvalid() {
		boolean isValidScale = Validator.isCorrectScale(10.005f);
		
		assertFalse(isValidScale);
	}
	
	@Test
	public void incorrectScaleIsInvalid2() {
		boolean isValidScale = Validator.isCorrectScale(60.006f);
		
		assertFalse(isValidScale);
	}
	
	@Test
	public void correctScaleIsValid() {
		assertTrue(Validator.isCorrectScale(10.00f));
	}
	
	@Test
	public void properlyFormatted() {
		assertEquals("10.00", Validator.formatDecimals(10f));
	}
	@Test
	public void properlyFormatted2() {
		assertEquals("10.33", Validator.formatDecimals(10.33f));
	}
	@Test
	public void properlyFormatted3() {
		assertEquals("100.33", Validator.formatDecimals(100.33f));
	}
	
	@Test
	public void createUserWorks() {
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			UserDaoImpl udi = new UserDaoImpl();
			User user = new User(1005, "JimmyHalpert", "jimmy@gmail.com", "RevaturE1!");
			udi.createUser(user);
			assertEquals(1, udi.createUser(user));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("createUserWorks() test threw exception " + e.getMessage());
		}
		
	}
	
	@Test
	public void createBankWorks() {
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			BankDaoImpl bdi = new BankDaoImpl();
			Bank bank = new Bank(1005, 0, 1005);
			assertEquals(1, bdi.createBank(bank));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("createBankWorks() test threw exception " + e.getMessage());
		}
	}
	
}