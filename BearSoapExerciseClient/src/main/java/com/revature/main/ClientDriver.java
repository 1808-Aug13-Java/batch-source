package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Bear;
import com.revature.service.BearService;

public class ClientDriver {
	
	public static void main(String[] args) {
		String serviceUrl = "http://192.168.60.143:8082/LibrarySoapService/Bear";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(BearService.class);
		factory.setAddress(serviceUrl);
		
		LoggingInInterceptor liic = new LoggingInInterceptor();
		LoggingOutInterceptor loic = new LoggingOutInterceptor();
		
		factory.getInInterceptors().add(liic);
		factory.getOutInterceptors().add(loic);
		
		liic.setPrintWriter(new PrintWriter(System.out));
		loic.setPrintWriter(new PrintWriter(System.out));
		
		BearService bearService = (BearService) factory.create();
		
		List<Bear> bears = bearService.getAllBears();
		
		for(Bear b : bears) {
			System.out.println(b);
		}
		
		Bear bear = new Bear("Ryan Gosling", "Yellowstone", 1985);
		
		bearService.addBear(bear);
		
		
	}
}
