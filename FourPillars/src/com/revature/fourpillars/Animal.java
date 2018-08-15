package com.revature.fourpillars;

//import com.revature.exceptions.InvalidSexOptionException;  //right click where you use ref teh customised 
	//exception and one of the solutions will let you import from the other package

//									X                          X            X           X            X
//need to implement 4 pillars: encapsulation, polymorphism: (objects), (methods),  abstraction, inheritance
public abstract class Animal {				
	//Encapsulation: Animal member variables use default access modifiers,
	//so they are accessed only by classes within this package. However, getter, setter functions, 
	// and displayInfo() functions and constructors
	//on lines 19-56 have public access modifiers, making it so that the Animal class 
	//is modular and unitized, working independently of other classes while hiding its implementation 
	//details
	int numLegs;
	String sex;						//TODO make catch/exception for sex outside of m/f/
	
	
	public Animal() {
		super();
	}
	
	public Animal(int numLegs, String sex) {	//this class Animal is an abstraction because it models
		super();						// "animals" and how they have legs and a sex. The inside of the 
										// class is only accessible within this package, however, so that
									// the class works while not letting the user know how it works
		this.numLegs = numLegs;
		
		this.sex = sex;
	}
	
	public abstract void displayInfo();			//expecting that each sub class needs this kind of 
									//function, but needs an implementation more tailored specifically
								//otherwise it would have been better to make this method concrete. The same
						//so that every class can reuse this code 

	//getters
	public int getNumLegs() { return this.numLegs; }
	public String getSex() { return this.sex; }
		
	//setters
	public void setNumLegs(int numLegs) 
	{
		this.numLegs = numLegs;
	}
		
	public void setSex(String sex) 
	{
//		if(!sex.equals("female") || !sex.equals("male"))	//checked Exception
//		{
//			try {
//				throw new InvalidSexOptionException("Invalid sex option");
//			} catch(InvalidSexOptionException e) {
//				System.out.println("Exception handled");
//			}
//		}
		this.sex = sex;
	}
	
//	public abstract int getNumLegs(); 		Dont have to make abstract because every class inheriting this one
//	public abstract String getSex();		can just use these functions .___. 
//	
//	public abstract void setNumLegs(int numLegs);
//	public abstract void setSex(String sex);

	
}
