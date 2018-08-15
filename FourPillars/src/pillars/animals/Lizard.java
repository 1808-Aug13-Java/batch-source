package pillars.animals;

import pillars.exceptions.InsufficientHeatException;

/**
 * Class Lizard - represents lizards
 * Implements Reptile (lizards are reptiles)
 * extends Animal - also animals
 * @author jljac
 *
 */
public class Lizard extends Animal implements Reptile {

	/**
	 * Creates the Lizard
	 * @param n - lizard's name
	 */
	public Lizard(String n) {
		super(n, "Lizard", 0);
		
	}

	/**
	 * craw - enables the lizard to craw
	 * @return int - how much movement was done
	 */
	@Override
	public int craw() {
		System.out.println("Crawling");
		return 4;
	}

	/**
	 * heatUp - meant to heat the animal
	 * @throws InsufficientHeatException - in case the heat source is insufficient
	 */
	@Override
	public void heatUp() throws InsufficientHeatException 
	{
		if(!heatSufficient(12))
			throw new InsufficientHeatException(12);
	}

}
