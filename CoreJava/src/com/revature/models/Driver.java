package com.revature.models;

import com.revature.exceptions.NegativeSpeedException;

public class Driver {

	public static void main(String[] args) {
		
//		Vehicle car1 = new Vehicle();
//		Vehicle car2 = new Vehicle();
//		
//		System.out.println(car1);
//		System.out.println(car2);
//		System.out.println(car1.equals(car2));
//
//		HybridCar fusion = new HybridCar();
		
//		comment
//		Control + /
//		System.out.println("Declaring a vehicle object:");
//		Vehicle myVehicle = new Vehicle();
//		myVehicle.setSpeed(5);
//		myVehicle.setSpeed(10);
//		System.out.println();
//		System.out.println("Declare a car object:");
//		HybridCar myHybrid = new HybridCar();
//		myHybrid.setSpeed(5);
//		myHybrid.setSpeed(10);
//		
////		not overriding, but method hiding - there is no runtime polymorphism
//		Vehicle.staticMethod();
//		HybridCar.staticMethod();
	
		Vehicle v1 = new Vehicle();
		try {
			v1.setSpeed(-2);
		} catch (NegativeSpeedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
