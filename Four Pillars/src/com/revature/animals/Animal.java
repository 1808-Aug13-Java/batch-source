package com.revature.animals;

//This class will be abstracted and inherited by the animals 
//This class can therefore never be instantiated but serves as a template for all animals 
public abstract class Animal {
	
	//These have protected access modifiers meaning they cannot be accessed directly by other classes
	//This applies to the OOP Pillar of encapsulation 
	protected int age;
	protected int numLegs;
	protected String species;
	protected String diet; //Herbivore, Omnivore, Carnivore
	protected String huntMethod; //defines the primary method of eating
	protected double hunger; //defines how hungry the animal is as a percentage 
	
	
	//Constructors
	public Animal()
	{
		super();
	}
	
	//Custom Constructor
	public Animal(int age, int numLegs, String species, String diet, 
					String huntMethod, double hunger)
	{
		this.age = age;
		this.numLegs = numLegs;
		this.species = species;
		this.diet = diet;
		this.huntMethod = huntMethod;
		this.hunger = hunger;
	}
	
	//Eating 
	public void eatFood(double foodValue)
	{
		//Check if the animal is still hungry 
		if(hunger > 0.0)
		{
			//Decrease the animals hunger 
			hunger -= foodValue;
			if(hunger < 0.0)
			{
				//In the case that the animal over eats
				try {
					throw new TooMuchFoodException("This animal is stuffed!");
					
				} catch(TooMuchFoodException e){
					//assign hunger to its minimum value instead of a negative 
					System.out.println(e.getMessage());
					hunger = 0.0;
				}			
			}
		}
	}
	
	//Check Hunger
	public double checkHunger()
	{
		//Displays hunger as a percentage 
		return hunger * 100;
	}

	//Setters and Getters 
	int getNumLegs() {
		return numLegs;
	}
	
	public void setNumLegs(int numLegs) {
		this.numLegs = numLegs;
	}
	
	public String getHuntMethod() {
		return huntMethod;
	}
	
	public void setHuntMethod(String huntMethod) {
		this.huntMethod = huntMethod;
	}

	public double getHunger() {
		return hunger;
	}

	//We have an marked exception for setting our hunger variable so we can't set it to negative
	public void setHunger(double hunger) {
		if(hunger < 0)
		{
			try {
				throw new NegativeHungerException("Cannot set hunger to negative");
			} catch(NegativeHungerException e) {
			hunger = 0;
			}
		} 
		this.hunger = hunger;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSpecies() {
		return species;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getDiet() {
		return diet;
	}
	
	public void setDiet(String diet) {
		this.diet = diet;
	}
	
	
	/*Each of the following methods are being overridden by the animal class over the object
	 * class's methods therefore being an example of polymorhism 
	*/
	
	//Hashcode Override
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((diet == null) ? 0 : diet.hashCode());
		result = prime * result + ((species == null) ? 0 : species.hashCode());
		return result;
	}
	
	//Equals Override
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (age != other.age)
			return false;
		if (diet == null) {
			if (other.diet != null)
				return false;
		} else if (!diet.equals(other.diet))
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		return true;
	}
	
	//toString override
	@Override
	public String toString() {
		return "Animal [age=" + age + ", species=" + species + ", diet=" + diet + "]";
	}
	
	
	

}
