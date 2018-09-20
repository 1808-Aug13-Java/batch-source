package com.revature.main;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

//	src\\com\\..\\log.txt 
	private static Logger log = Logger.getRootLogger();
	public static void main(String[] args) {
		log.info("hey");      // check src/main/java for a "log.txt". if you want to change the location of log.txt, 
							//update in log4j.properties, then update Maven project
//		log in a separate file the method signature of each operation as it is executed
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		Calculator c1 = new Calculator(3, 5);
		Calculator c1 = (Calculator) ac.getBean("calculator"); //do special way to get Calculator
			//so that LoggingAspect stuff still counts
//		log the operation arithmetically as it is performed - include the result (obtained by the join point, not from repeating the operation)
//		E.g. if add(2, 3) is called, “2 + 3 = 5” is logged
		c1.setA(10);
		c1.setB(2);
		System.out.println(c1.add());
		System.out.println(c1.divide());
		System.out.println(c1.multiply());
		c1.setA(1);
		System.out.println(c1.subtract());
//		prevent the division method from executing if division is attempted by 0 and log an error
		c1.setB(0);
		System.out.println(c1.divide());

		
	}

}
