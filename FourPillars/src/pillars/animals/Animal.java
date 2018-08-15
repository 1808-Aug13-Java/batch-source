package pillars.animals;

import pillars.exceptions.InsufficientHeatException;

/**
 * abstract class Animal - provides methods for Animals to work
 * @author jljac
 *
 */
public abstract class Animal 
{
	// Unknown Heat Source value! Needs to be sufficiently high so all animals
	// Can be heated. Because it's impossible to know this value, related exceptions 
	// Should be considered Checked Exceptions
	private static int heatSource =  ((int)Math.random()) % 25;
	
	// Name and Species - not needed by supclasses, just given
	private String name, species;
	
	// Handles Hair. For Reptiles, this should be 0. For Mammals, hair can be grown, but only to a certain length
	// Exception here should be considered Unchecked because it is possible to check for this error before riking 
	// an exception
	protected final int maxHairLength;
	protected int hairLength;
	
	/**
	 * Constructor
	 * @param n - name of the animal
	 * @param s - species of the animal - determined by the subclass
	 * @param mhl - max hair length - determined by subclass
	 */
	protected Animal(String n, String s, int mhl)
	{
		name = n;
		species = s;
		maxHairLength = hairLength = mhl;
	}
	
	/**
	 * getName - Retrieves the name of the animal
	 * @return String - the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * getSpecies - Retrieves the species of the animal
	 * @return String - the species
	 */
	public String getSpecies()
	{
		return species;
	}
	
	/**
	 * safeToGrowHair - whether or not hair can "SAFELY" be grown
	 * @return boolean - true if not overriden
	 */
	public boolean safeToGrowHair() {
		return true;
	}
	
	/**
	 * heatUp - heats up the animal
	 * @throws InsufficientHeatException
	 */
	public abstract void heatUp() throws InsufficientHeatException;
	
	/**
	 * heatSufficient - checks whether the heat requested is available
	 * @param h - heat desired
	 * @return boolean - whether there is enough heat
	 */
	protected boolean heatSufficient(int h)
	{
		return h <= heatSource;
	}
	
	
	
}
