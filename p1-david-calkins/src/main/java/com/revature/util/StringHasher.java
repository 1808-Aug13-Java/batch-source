package com.revature.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHasher {

	// Prevent instantiation
	private StringHasher() {}
	
	
	/** Takes a string and returns a 64 character hexadecimal representation 
	 * of the resulting bytes from putting the input string through a 
	 * sha-256 hash algorithm.  */
	public static String sha256Hash(String input) {
		// Used to perform the SHA-256 Hash
		MessageDigest digest = null;
		
		try {
			// Attempt to get an instance of a SHA-256 hasher. Shouldn't fail. 
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// If we get this, there is no such algorithm, and we have issues. 
			// Theoretically, this shouldn't happen if the right string is passed
			throw new RuntimeException(e);
		}
		
		byte[] encodedHash = digest.digest(
				input.getBytes(StandardCharsets.UTF_8));
		
		StringBuilder sBuilder = new StringBuilder();
		
		// Convert each byte to hexadecimal
		for (int i=0; i<encodedHash.length; i++) {
			// Convert the byte to an int from 0 to 255, (not -128 to 127)
			int tempNumb = (0xFF & encodedHash[i]);
			
			// Convert the high four bits to hex, and append to sBuilder
			sBuilder.append(Integer.toHexString(tempNumb >> 4));
			// Convert the low four bits to hex, and append to sBuidler
			sBuilder.append(Integer.toHexString(tempNumb & 0xF));
		}
		
		return sBuilder.toString();
	} // end of sha256Hash
}
