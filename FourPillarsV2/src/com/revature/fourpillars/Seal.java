package com.revature.fourpillars;

import com.revature.customexceptions.CustomCheckedException;
import com.revature.customexceptions.CustomUncheckedException;

//As stated in the Animals abstract class, the use of subclasses such as Seal is an example of 
//inheritance (superclasses giving behavior to subclasses). The use of the private access modifier for
//the variable numFlippers, which is needed only for Seal, along with public methods is an example of encapsulation.
//Polymorphism is evident in Seal using the same method signature as Animals for many of its methods. And abstraction,
//while not as obvious in this class, takes place because of the use of the Animals abstract class as the parent class.

public class Seal extends Animals {
	public Seal() {
		super();
	}
	
	public Seal(int numFlippers) {
		super();
		this.numFlippers = numFlippers;
	}
	
	private int numFlippers;//this variable occurs only here, because flippers are not common enough for all animals
	
	public int getNumFlippers() {
		return numFlippers;
	}

	//custom checked exception
	public void setNumFlippers(int numFlippers) throws CustomCheckedException {
		if(numFlippers < 0) {
			try {
				throw new CustomCheckedException("Error: numFlippers cannot be negative");
			} catch (CustomCheckedException e) {
				System.out.println("Error: numFlippers cannot be negative");
			}
		} else {
		this.numFlippers = numFlippers;
		}
	}

	//note that this method has an identical signature to the one in the abstract class Animals
	public int getNumLegs() {
		return numLegs;
	}
	 
	public void setNumLegs(int numLegs) throws CustomUncheckedException {
		if(numLegs < 0) {
			try {
				throw new CustomUncheckedException("Error: numLegs cannot be negative"); 
			} catch (CustomUncheckedException e) {
				System.out.println("Error: numLegs cannot be negative");
			}
		} else {
		this.numLegs = numLegs;
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void speak() {
		System.out.println("arf!");
	}
	
	public void move() {
		System.out.println("flops around");
	}
}
