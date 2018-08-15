package com.revature.fourpillars;

import com.revature.fourpillars.NotHungryException;

public class Elephant extends Mammals implements Animals{
/* THe Elephants class is a subclass of the Mammals superclass. Encapsulation is shown here through the use of access modifiers 
 * The data members of the Mammals class as private and the creating public methods that can be used to retrieve or modify them 
 * The setters in the heal() interface implementation show this.*/
	private static final int ELE_LEGS = 4;
	
	public Elephant() {
		super(); //parent constructor of Elephant, the Mammals() constructor is called.
		this.setNumLegs(ELE_LEGS);
	}
	
	public Elephant(boolean isInjured, boolean isHungry ) {
	super(ELE_LEGS, isInjured, isHungry);	
	//here the parameterized constructor of Mammals() is called instead
	}
	//begin interface implementation
	public void heal() {
		if ( this.isInjured()) {
			this.setInjured(false); /*uses the public methods within Mammals to change the value of the isInjured field
			 this shows both encapsulation and inheritance since .setInjured is an inherited method of Mammals that Elephant gets 
			 access to as a child class */
		System.out.println("This Elephant is now healed");
		}
		else {
			System.out.println("This elephant is isn't injured");
		}
	}
	
	public void eatThis(String foodType) throws NotHungryException {
		if( !this.isHungry()) {
			throw new NotHungryException("Mammal is not hungry");
		}
		System.out.println("This elephant will eat "+ foodType);
		this.setHungry(false);
	}

	public void sleep()  {
		//could be a runtime exception
		if (this.isHungry()) {
			throw new NotSleepyRuntimeException("Elephant is still hungry and cannot sleep");
			
		}
		else {
			 System.out.println("Elephant is not hungry and can sleep"); 
			 this.setSleeping(true);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
Elephant ele = new Elephant();
// creates an elephant that is neither injured or hungry
Tiger tig = new Tiger();
//creates a tiger that is neither injured not hungry 
Monkey monk= new Monkey();

Elephant ele2 = new Elephant(false, true); 
// creates an elephant that is not injured but is hungry
Tiger tig2 = new Tiger (true, true); 
// creates a tiger that is injured and hungry
Monkey monk2= new Monkey(true,false);

System.out.println("A monkey has : " +monk.getNumLegs()+" legs");
System.out.println("An elephant has : " +ele.getNumLegs()+" legs");
// this shows how both the Elephant and Monkey class use inheritance to access the same methods 
// of the parent class mammal


try {
	ele.eatThis("grass");
} catch (NotHungryException e) {
	// TODO Auto-generated catch block
	e.printStackTrace(); /* this shows the custom, unchecked exception, running the Elephant.java file will
	produce the exception
	*/
}
ele.animalConfirm();
tig.animalConfirm();
//verifies that the default case if the interface works


tig.eatThis("raw meat"); /*both of these class show how each of them are implemented differently
and achieve polymorphism through the use of interfaces.*/
monk.eatThis("fruit");

monk2.isHungry();
monk2.sleep(); 
System.out.println("The monkey is now sleeping: "+monk2.isSleeping());

monk2.eatThis("apple");
System.out.println( "This mammal is hungry: " +ele2.isHungry());
ele2.sleep(); // this throws the unchecked Runtime exception, NotSleepyRuntimeException 
// ele2 tries to go to sleep even though it is still hungry and the exception is thrown





 
	}

}
