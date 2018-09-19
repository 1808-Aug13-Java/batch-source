package com.revature.driver;

import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;



class CalcMain {

	public static void main(String[] args) 
	{
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator)ac.getBean("calculator");	
		Scanner scan = new Scanner(System.in);

		System.out.println("Please enter calculations in the form '[operation] [x] [y]'!");
		System.out.println("[operation] includes 'add', 'sub'/'subtract', 'mul'/'multiply', 'div'/'divide'");
		System.out.println("'mod', and 'pow'. [x] and [y] are integers!");
		
		System.out.println("\n\nType 'exit' to quit!");
		
		String input = "";
		
		while(true)
		{
			System.out.print("-->");
			input = scan.nextLine().trim().toLowerCase();
			
			String lines[] = input.split(" ");
			
			if(input.trim().toLowerCase().equals("exit"))
				break;
			
			if(lines.length < 3)
			{
				System.out.println("Error! Each operation requires at least three tokens!");
				continue;
			}
			
			int x = 0, y = 0;
			
			try
			{
				x = Integer.parseInt(lines[1]);
				y = Integer.parseInt(lines[2]);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Error! The second and third operands need to be integers");
			}
			
			switch(lines[0])
			{
			case "add":
				c.add(x,y);
				break;
			case "sub":
			case "subtract":
				c.sub(x,y);
				break;
			case "mul":
			case "multiply":
				c.mul(x,y);
				break;
			case "div":
			case "divide":
				c.div(x,y);
				break;
			case "mod":
				c.mod(x,y);
				break;
			case "pow":
				c.pow(x,y);
				break;
			default:
				System.out.println("Error! Non-valid operand provided!");
			}
		}
		
		
		scan.close();
		ac.close();
	}

	
}
