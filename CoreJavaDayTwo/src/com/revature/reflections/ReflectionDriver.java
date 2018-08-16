package com.revature.reflections;

import java.lang.reflect.*;

import com.revature.models.Hybrid;

public class ReflectionDriver {

	public static void main(String[] args) {
		
		try {
			Class c1 = Class.forName("com.revature.models.Hybrid");
			
			System.out.println("Class: " + c1.getName());
			System.out.println("Parent Class: " + c1.getSuperclass());
			System.out.println("Grandparent Class: " + c1.getSuperclass().getSuperclass());
			
			
			// get the interfaces implementd by our HybridCar class
			Class[] interfaces = c1.getInterfaces();
			for(Class interf : interfaces) {
				System.out.println(interf);
			}
			
			System.out.println();
			
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
			
			// we can create an instance of a Hybrid using only its class
			Hybrid car = (Hybrid) c1.newInstance();
			System.out.println(car.toString());
			
			// modify and manipulate diels within the class
			Field chargeTimeField = c1.getDeclaredField("chargeTime");
			chargeTimeField.setAccessible(true);
			chargeTimeField.set(car, 60);
			chargeTimeField.setAccessible(false);
			System.out.println(car);
			
			// ability to invoke methods
			Method setChargeTimeMethod = c1.getDeclaredMethod("setChargeTime", int.class);
			setChargeTimeMethod.invoke(car, 70);
			
			System.out.println(car);
			
		} catch (ClassNotFoundException e) {
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
