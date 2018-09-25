package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Car;
import com.revature.services.Garage;

public class ClientDriver {

	public static void main(String[] args) {

		String serviceUrl = "http://192.168.60.87:8083/SoapClientExercise/Garage";
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Garage.class); // provide our interface
		factory.setAddress(serviceUrl);
		
		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		Garage garage = (Garage) factory.create();
		List<Car> cars = garage.getAllCars();
		
//		garage.addCar(new Car(2007, "honda", "accord"));
		for (Car c: cars) {
			System.out.println(c);
		}
	}

}
