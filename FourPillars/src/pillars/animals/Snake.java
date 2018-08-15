package pillars.animals;

import pillars.exceptions.InsufficientHeatException;

/**
 * Class Snake - represents snakes
 * Implements Reptile (snakes are reptiles)
 * extends Animal - also animals
 * @author jljac
 *
 */
public class Snake extends Animal implements Reptile{

	/**
	 * Creates the snake
	 * @param n - snakes's name
	 */
	public Snake(String n) {
		super(n, "Snake", 0);
	}

	/**
	 * craw - enables the snake to slither
	 * @return int - how much movement was done
	 */
	@Override
	public int craw() {
		System.out.println("Slithering!");
		return 2;
	}

	/**
	 * heatUp - meant to heat the animal
	 * @throws InsufficientHeatException - in case the heat source is insufficient
	 */
	@Override
	public void heatUp() throws InsufficientHeatException {
		if(!heatSufficient(18))
			throw new InsufficientHeatException(18);
	}


}
