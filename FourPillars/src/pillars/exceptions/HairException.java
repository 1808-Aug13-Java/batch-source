package pillars.exceptions;

/**
 * class HairException - thrown when the hair gets too long
 * Serves as unchecked exception as condition can be checked ahead of time
 * extends Exception
 * @author jljac
 *
 */
public class HairException extends Exception
{
	/**
	 * Creates Exception
	 * @param hairLength - length of heir expected
	 * @param maxHair - langth allowed
	 */
	public HairException(int hairLength, int maxHair)
	{
		super("Error! Hair Length " + hairLength + " exceeds the max length allowed: " + maxHair);
	}
}
