package com.revature.animals;
import java.util.Scanner; 
/*
 * The following is a class labled Animals. It is the parent class of the classes Birds, FlightlessBirds, and SoaringBirds
 * This FlightlessBirds and SoaringBirds classes are subclasses of the Bird Class which means they are also subclasses 
 * of the Animals class. This is an example of Inheritance in OOP programming where a class inherits the members and 
 * methods of another class by declaring the keyword "extends" after the class name.
 */
public class Animals {
	/*
	 * The following private variables, constructors, and methods declared within the Animals class is an example of
	 * Abstraction in OOP where the characteristics of a specific object are defined using methods and variables that grouped
	 * in the same class and packages
	 */
	private int height;
	private int weight;
	private int numberOfExtremities;
	private int numberOfOffspring;
	private String type;
	private int speed;
	private int lifeSpan;
	private int averageWeight;

	/*
	 * The access modifies of the variables above are private which protect them being access and changed from outside the class
	 * and the getters and setters functions have public access modifiers which allow these functions to access and change the
	 * private variables. This is an example of encapsulation which allows us to hide the members and methods we want of the 
	 * class from outside environments whenever necessary.
	 */

	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNumberOfExtremities() {
		return numberOfExtremities;
	}
	public void setNumberOfExtremities(int numberOfExtremities) {
		this.numberOfExtremities = numberOfExtremities;
	}
	public int getNumberOfOffspring() {
		return numberOfOffspring;
	}
	public void setNumberOfOffspring(int numberOfOffspring) {
		this.numberOfOffspring = numberOfOffspring;
	}
	public String getType() {
		return type;
	}
	
	/* this setter function has a coding error where when a valid string "Birds" is entered
	 * the speed variable of the object is set to null which is a runtime exception. this 
	 * is a coded error which the user is not expected to handle, so the exception is just thrown
	 */
	public void setType(String type) {
	
		if(type.equals("Birds")) {
			this.speed = null;
			throw new MyUncheckedException("Coded Error int variable set to null", this ,"Birds",21);

			}
		else {
			this.type = type;
		}
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getLifeSpan() {
		return lifeSpan;
	}
	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}
	public int getAverageWeight() {
		return averageWeight;
	}
	public void setAverageWeight(int averageWeight) {
		this.averageWeight = averageWeight;
	}
	
	//default constructor
	public Animals() {
		super();
		// TODO Auto-generated constructor stub
	}
	//overloaded constructors are examples of polymorphism where methods with the same name
	//have different implementations through different parameter declarations
	//Overloaded Constructor with all class variables intialized
	public Animals(int height, int weight, int numberOfExtremities, int numberOfOffspring, String type, int speed,
			int lifeSpan, int averageWeight) {
		super();
		this.height = height;
		this.weight = weight;
		this.numberOfExtremities = numberOfExtremities;
		this.numberOfOffspring = numberOfOffspring;
		this.type = type;
		this.speed = speed;
		this.lifeSpan = lifeSpan;
		this.averageWeight = averageWeight;
	}
	
	//Overloaded Constructor with all class variables except weight initialized.
	public Animals(int height, int numberOfExtremities, int numberOfOffspring, String type, int speed, int lifeSpan,
			int averageWeight) {
			super();
			if(height < 0) {
				this.height = height;
				try {
					throw new NegativeValueException("Height cannot be negative!");
				} catch (NegativeValueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			else
				this.height = height;
			if(numberOfExtremities < 0)
			{
				this.numberOfExtremities = numberOfExtremities;
				try {
					throw new NegativeValueException("Number of extremities cannot be negative!");
				} catch (NegativeValueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				this.numberOfExtremities = numberOfExtremities;
			if(numberOfOffspring < 0)
			{
				this.numberOfOffspring = numberOfOffspring;
				try {
					throw new NegativeValueException("Number of offspring the animal can have is not negative!");
				} catch (NegativeValueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				this.numberOfOffspring = numberOfOffspring;
			/*The user defined TypeException checked exception is thrown here if the "type" parameter
			 * passed to this constructor is does not match the valid Strings. The exception is caught and the 
			 * user is given a chance to recover from the exception
			 */
			if(!(type.equals("Invertebrates") || type.equals("Fish") || type.equals("Amphibians") || type.equals("Reptiles") || type.equals("Birds") || type.equals("Mammals")))
			{
				try {
					throw new TypeException("This is type of animal is unkown!");
				} catch (TypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Enter a valid type of animal := Invertebrates, Fish, Amphibians, Reptiles, Birds ");
					Scanner input = new Scanner(System.in);
					String newType;
					do
					{
						newType = input.nextLine();
					}while(!(newType.equals("Invertebrates") || newType.equals("Fish") || newType.equals("Amphibians") || newType.equals("Reptiles") || newType.equals("Birds") || newType.equals("Mammals")));

					this.type = newType;
				}
			}
			else
				this.type = type;
			this.speed = speed;
			this.lifeSpan = lifeSpan;
			this.averageWeight = averageWeight;
	}
	
	@Override
	public String toString() {
		return "Animals [height=" + height + ", weight=" + weight + ", numberOfExtremities=" + numberOfExtremities
				+ ", numberOfOffspring=" + numberOfOffspring + ", type=" + type + ", speed=" + speed + ", lifeSpan="
				+ lifeSpan + ", averageWeight=" + averageWeight + "]";
	}

}
