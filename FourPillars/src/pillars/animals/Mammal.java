package pillars.animals;

import pillars.exceptions.HairException;

/**
 * interface Mammal - provides method signatures unique to mammals
 * @author jljac
 *
 */
public interface Mammal {

	/**
	 * growHair - mammals have the ability to grow hair
	 * @return - length of the hair after operation
	 * @throws HairException - thrown if hair gets too long
	 */
	int growHair() throws HairException;
}
