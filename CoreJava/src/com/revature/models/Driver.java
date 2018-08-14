package com.revature.models;

import com.revature.exceptions.NegativeSpeedException;

public class Driver {
	
	public static void main(String[] args) {
		
//		Vehicle car1 = new Vehicle();
//		Vehicle car2 = new Vehicle();
		
		// this is a comment
		/*
		 * this is a multiline comment
		 *  */
//		System.out.println(car1);
//		System.out.println(car2);
//		System.out.println(car1.equals(car2));
		
//		System.out.println("Declaring a vehicle object:");
//		Vehicle myVehicle = new Vehicle();
//		myVehicle.setSpeed(5);
//		myVehicle.setSpeed(10);
//		System.out.println();
//		
//		System.out.println("Declare a car object:");
//		HybridCar myHybrid = new HybridCar();
//		myHybrid.setSpeed(5);
//		myHybrid.setSpeed(10);
//		System.out.println();
//		
//		//not overriding, but method hiding - there is no runtime polymorphism
//		Vehicle.staticMethod();
//		HybridCar.staticMethod();
		
		Vehicle v1 = new Vehicle();
		try {
			v1.setSpeed(-2);
		} catch (NegativeSpeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// if any other exception is thrown
		}
		
		
	}

}
