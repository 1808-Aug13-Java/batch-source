package pillars.animals;

import pillars.exceptions.HairException;
import pillars.exceptions.InsufficientHeatException;

public class Cat extends Animal implements Mammal {

	/**
	 * Creates the cat
	 * @param n - cat's name
	 */
	public Cat(String n) {
		super(n, "Cat", 25);
		hairLength = 10;
	}

	/**
	 * growHair - mammals have the ability to grow hair
	 * @return - length of the hair after operation
	 * @throws HairException - thrown if hair gets too long
	 */
	@Override
	public int growHair() throws HairException 
	{
		if((3 + hairLength) > maxHairLength)
			throw new HairException(3+hairLength, maxHairLength);
		return hairLength += 3;
	}

	/**
	 * growHair - mammals have the ability to grow hair
	 * @return - length of the hair after operation
	 * @throws HairException - thrown if hair gets too long
	 */
	@Override
	public boolean safeToGrowHair() 
	{
		return (3 + hairLength) <= maxHairLength;
	}

	/**
	 * heatUp - meant to heat the animal
	 * @throws InsufficientHeatException - does not throw under this class
	 */
	@Override
	public void heatUp() throws InsufficientHeatException {
		System.out.println("purring!");
	}

}
