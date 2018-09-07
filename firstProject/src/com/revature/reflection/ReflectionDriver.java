package com.revature.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.revature.models.HybridCar;

public class ReflectionDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//getting hybridcar class
			Class c1 = Class.forName("com.revature.models.HybridCar");
			System.out.println("Class: " + c1.getName());
			System.out.println("Parent Class: " + c1.getSuperclass());
			System.out.println("Grandparent Class: " + c1.getSuperclass().getSuperclass());
			
			//get interfaces
			Class[] interfaces = c1.getInterfaces();
			for(Class interf : interfaces) {
				System.out.println(interf);
			}
			//access methods within the class
			System.out.println("Methods: ");
			Method[] methods = c1.getDeclaredMethods();
			for(Method meth: methods) {
				System.out.println(meth);
			}
			
			//get fields within class
			System.out.println("Fields: ");
			Field[] fields = c1.getDeclaredFields(); 
			for(Field field: fields) {
				System.out.println(field);
			}
			
			Field[] superFields = c1.getSuperclass().getDeclaredFields();
			for(Field sField: superFields) {
				System.out.println(sField);
			}
		
			//create instance of hybridcar using only its class
			HybridCar car = (HybridCar) c1.newInstance();
			System.out.println(car.toString());
			
			//we have the ability to modify and manipulate fields within the class
			Field chargeTime = c1.getDeclaredField("chargeTime");
			chargeTime.setAccessible(true);
			chargeTime.set(car, 60);
			chargeTime.setAccessible(false);
			
			System.out.println(car.toString());
			
			Method setChargeTime = c1.getDeclaredMethod("setChargeTime", int.class);
			setChargeTime.invoke(car, 70);
			System.out.println(car.toString());
			
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
