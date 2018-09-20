package com.revature.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.dao.BearDao;
import com.revature.models.Bear;

public class Driver {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BearDao bd = (BearDao) ac.getBean("bearDaoImpl");

		List<Bear> bears = bd.getBears();
//		System.out.println(bears);
		for (Bear b : bears) {
			System.out.println(b);
		}
	}

}
