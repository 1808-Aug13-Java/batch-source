package com.revature.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.revature.models.HybridCar;

public class ReflectionDriver {

	public static void main(String[] args) {
		
		try {
			// getting our HybridCar class
			Class c1 = Class.forName("com.revature.models.HybridCar");
			System.out.println("Class: "+c1.getName());
			System.out.println("Parent Class: "+c1.getSuperclass());
			System.out.println("Grandparent Class: "+c1.getSuperclass().getSuperclass());
			System.out.println();
			
			// get the interfaces implemented by our HybridCar class
			Class[] interfaces = c1.getInterfaces();
			for(Class interf : interfaces) {
				System.out.println(interf);
			}
			System.out.println();
			
			// access the methods within the class
			System.out.println("Methods: ");
			Method[] methods = c1.getDeclaredMethods();
			for(Method method : methods) {
				System.out.println(method.toString());
			}
			System.out.println();
			
			// access the fields within the class
			System.out.println("Fields: ");
			Field[] fields = c1.getDeclaredFields();
			for(Field field : fields) {
				System.out.println(field);
			}
			Field[] superFields = c1.getSuperclass().getDeclaredFields();
			for(Field field : superFields) {
				System.out.println(field);
			}
			System.out.println();
			
			// we can create an instance of a HybridCar using only its class
			HybridCar car = (HybridCar) c1.newInstance();
			System.out.println(car.toString());
			
			// we have the ability to modify and manipulate fields within the class
			Field chargeTimeField = c1.getDeclaredField("chargeTime");
			chargeTimeField.setAccessible(true);
			chargeTimeField.set(car, 60);
			chargeTimeField.setAccessible(false);
			System.out.println(car.toString());
			
			// we also have the ability to call methods within our class at runtime
			Method setChargeTimeMethod = c1.getDeclaredMethod("setChargeTime", int.class);
			setChargeTimeMethod.invoke(car, 70);
			System.out.println(car.toString());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
