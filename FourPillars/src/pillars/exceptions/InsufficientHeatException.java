package pillars.exceptions;

/**
 * class InsufficientHeatException - whether heat is sufficient or not
 * Serves as checked exception as condition cannot be checked beforehand
 * extends Exception
 * @author jljac
 *
 */
public class InsufficientHeatException extends Exception {

	/**
	 * Constructs exception with the message
	 * @param val - heat requested
	 */
	public InsufficientHeatException(int val)
	{
		super("Error! Heat Source of " + val + " is needed!");
	}
}
