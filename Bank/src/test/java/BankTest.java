package test.java;

import com.revature.util.UserInputValidation;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankTest {
	
	@Test
	public void emailWithLowercaseUppercaseAndSymbols() {
	boolean emailsValid = UserInputValidation.emailAddressIsValid("kjafhs!!!LKHJF");
	assertFalse(emailsValid);
	}
	
	@Test
	public void anActuallyValidEmail() {
		boolean emailsValid = UserInputValidation.emailAddressIsValid("alexbumpers@gmail.com");
		assertTrue(emailsValid);
	}
	
	@Test
	public void emptyUsername() {
		boolean usernamesValid = UserInputValidation.userNameIsValid("");
		assertFalse(usernamesValid);
	}
	
	@Test
    public void usernameHasASpaceCharacter() {
    	boolean usernamesValid = UserInputValidation.userNameIsValid("alexbu mpers@gmail.com");
    	assertFalse(usernamesValid);
    }
	
	@Test
	public void passwordIsEmpty() {
		boolean passwordsValid = UserInputValidation.passwordIsValid("");
		assertFalse(passwordsValid);
	}
	
	@Test
	public void anActuallyValidUsername() {
		boolean usernamesValid = UserInputValidation.userNameIsValid("alexbumpers");
		assertTrue(usernamesValid);
	}
}
