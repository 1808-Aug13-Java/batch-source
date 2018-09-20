package com.revature.dao;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Operator;

public class OperatorDaoImpl implements OperatorDao {
	private static Scanner sc= new Scanner(System.in);
	 Operator o = new Operator();
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public void compute(float in1, float in2, String operation) {
		switch(operation) {
			case "addition":{
				o.setResult(in1+in2);
				log.info(o.getResult());
				break;
			}
			case "multiplication":{
				o.setResult(in1*in2);
				log.info(o.getResult());
				break;
			}
			case "division":{
				o.setResult(in1/in2);
				log.info(o.getResult());
				break;
				}
			case "subtraction":{
				o.setResult(in1-in2);
				log.info(o.getResult());
				break;
			}
			default:{
				log.info("Please select a valid option");
				menu();
			}
		}
	}
	
	public void menu() {
		log.info("Please select an operation");
		String choice = sc.next();
		if (choice == "quit") {
			System.exit(0);
		}
		else {
			log.info("Enter your first input");
			float first= sc.nextFloat();
			log.info("Enter your second input");
			float second =sc.nextFloat();
			o.setInput1(first);
			o.setInput2(second);
			o.setChoice(choice);
			compute(first, second, choice);
			menu();
		}
	}
	
	

	
	

}
