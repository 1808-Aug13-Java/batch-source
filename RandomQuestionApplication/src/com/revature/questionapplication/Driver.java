package com.revature.questionapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Driver {

	public static void main(String[] args) {
		
		Hashtable<Student, Question> studentHashTable = new Hashtable<Student, Question>();
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		Question[] questions = new Question[4];
		
		studentList.add(new Student("Jan", "Balangue"));
		studentList.add(new Student("John", "Jacko"));
		studentList.add(new Student("David", "Calkins"));
		studentList.add(new Student("Cirey", "Francis"));
		
		questions[0] = new Question("What language are we currently learning?", "Java");
		questions[1] = new Question("What company do we work for?", "Revature");
		questions[2] = new Question("True or false: Java uses functions, not methods.", "false");
		questions[3] = new Question("What are we having for lunch today?", "sandwiches");
	
		Comparator<Student> compareNames = new CompareNames();
		Collections.sort(studentList, compareNames);
		for (Student student: studentList) {
			for(int i = 0; i < studentList.size(); i++) {
					int randomValue = (int) Math.floor(Math.random() * questions.length);
					studentHashTable.put(student, questions[randomValue]);
			}
		}
		System.out.println(studentHashTable);
	}
}
