import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.validation.Validator;

public class ServiceLayerTest {

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
	
	
	
	
	
	
	
}