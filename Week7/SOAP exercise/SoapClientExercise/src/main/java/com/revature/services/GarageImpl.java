package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.GarageFullException;
import com.revature.models.Car;

public class GarageImpl implements Garage {
	private List<Car> carList = new ArrayList<>();
//	private int year;
//	private String make;
//	private String model
	
	public GarageImpl() {
		carList.add(new Car(2016, "lexus", "is200"));
		carList.add(new Car(2014, "bmw", "535i"));

//		carList.add(new Car(2007, "honda", "accord"));

	}

	@Override
	public List<Car> getAllCars() {
		return this.carList;
	}

	@Override
	public String addCar(Car car) throws GarageFullException {
		if(this.carList.size()>3) {
			throw new GarageFullException("Garage is full cannot add  " + car.getMake());
		}
		else {
			this.carList.add(car);
			return car.getMake() + " added to garage.";
		}
	}

}
