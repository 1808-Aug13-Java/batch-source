package com.revature.fourpillars;

import com.revature.exceptions.NegativeTailLengthException;
import com.revature.exceptions.WrongTypeCastException;

public class Cat extends Animal {	//example of Inheritance and polymorphism
									//inherits the Animal class' variables and functions
									//now when I make a Cat object, it is also an Animal object,
									//so that's an example of structures that are polymorphic
	int tailLength;		//<== The Cat class is an example of an abstraction because the code models
						//certain characteristics of a real live cat - it's tail length, sex, and number of legs
						//However, the audience or other programs wouldn't be able to see the implementation
						//of the way the Cat class works. They can just interact with the class and receive
						//a service from this class
	
	public Cat() {}
	public Cat(int numLeg, String sex, int taiLength) //example of inheritance. numlegs and sex from Animal
	{
//		this.numLegs = numLeg;
//		this.sex = sex;
		super(numLeg, sex);		//** super will call the constructor of Animal, the super class
		this.tailLength = taiLength;
	}	//constructor
	
	@Override							//Example of Polymorphism because we're overriding the
	public void displayInfo()		//abstract method from the superclass Animal and filling out a 
	{								//implementation specific to Cat class
		System.out.println("Sex, number of legs, and tail length: " + sex+", "+numLegs+", "+tailLength);
	}
	
	@Override
	public boolean equals(Object obj)	//Overriding the same signature of java's equals function
						//Example of Polymorphism, where we have a function of the same name and signature
					//but we are making it specific to a new class Cat, with a different implementation
	{
		if(this == obj)	//if memory locations are the same of both Cats. ONLY happens for 
			return true;		//A.equals(A)  -> A equals itself
		if(obj == null)
			return false;
//		if(this.getClass() != obj.getClass())	//if classes arent the same
//			return false;
		
		
		if(obj.getClass() != this.getClass())			//Unchecked Exception: for bad type casting
		{
			try {
				throw new WrongTypeCastException("Object being compared isn't typed Cat.");
			} catch(WrongTypeCastException e) {
				e.printStackTrace();
			}
			
		}
		Cat cat = (Cat) obj;	//this is already a cat, but typecast obj to cat as well
								//FIXME do another try exception for if obj isn't a Cat
		
		if(this.numLegs != cat.numLegs)
			return false;
		if(sex != cat.sex)
			return false;
		if(tailLength != cat.tailLength)
			return false;
		return true;
	}
	
	//getters
	public int getTailLength() { return this.tailLength; }
	
	//setters
	public void setTailLength(int tailLength) 
	{
		if(tailLength < 0)													//checked Exception	HERE						
		{																	
			try {															
				throw new NegativeTailLengthException("Invalid tail length");
			} catch(NegativeTailLengthException e) {
//				System.out.println("Exception handled");
				e.printStackTrace();
			}
		}
		
		this.tailLength = tailLength;
	}
	
	
	
	
}
