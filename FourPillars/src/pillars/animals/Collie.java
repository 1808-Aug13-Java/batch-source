package pillars.animals;

import pillars.exceptions.HairException;
import pillars.exceptions.InsufficientHeatException;

/**
 * class Collie - represents Collies
 * implements Mammal - Collies are Mammels
 * extends Animal
 * @author jljac
 *
 */
public class Collie extends Animal implements Mammal {

	/**
	 * Creates the Collie
	 * @param n - collie's name
	 */
	public Collie(String n) {
		super(n, "Collie", 50);
		hairLength = 20;
	}

	/**
	 * growHair - mammals have the ability to grow hair
	 * @return - length of the hair after operation
	 * @throws HairException - thrown if hair gets too long
	 */
	@Override
	public int growHair() throws HairException
	{
		if((4 + hairLength) > maxHairLength)
			throw new HairException(4+hairLength, maxHairLength);
		return hairLength += 4;
	}

	/**
	 * heatUp - meant to heat the animal
	 * @throws InsufficientHeatException - Does not throw under this class
	 */
	@Override
	public void heatUp() throws InsufficientHeatException {
		System.out.println("Panting");

	}

	/**
	 * safeToGrowHair - checks if it is safe to call the "growHair" method without an exception
	 * @return boolean - whether "growHair" will run without an exception
	 */
	@Override
	public boolean safeToGrowHair() {
		
		return (4 + hairLength) <= maxHairLength;
	}

}
