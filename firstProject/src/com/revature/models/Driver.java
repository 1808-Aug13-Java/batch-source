package com.revature.models;

public class Driver {

	public static void main(String[] args) {
		
		/* Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car1.equals(car2));
		*/
		System.out.println("Declaring a vehicle object:	");
		Vehicle myVehicle = new Vehicle();
//		myVehicle.setSpeed(5);
//		myVehicle.setSpeed(10);
//		myVehicle.setSpeed(-5);
//		
		
		
		//not overridden, this is method hiding. there is no runtime polymorphism
		Vehicle.staticMethod();
		HybridCar.staticMethod();
	}
}
