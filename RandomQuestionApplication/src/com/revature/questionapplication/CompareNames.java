package com.revature.questionapplication;

import java.util.Comparator;

public class CompareNames implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getLastName().charAt(0) < o2.getLastName().charAt(0)) {
			return 1;
		} else if (o1.getLastName().charAt(0) == o2.getLastName().charAt(0)) {
			if (o1.getLastName().charAt(1) < o2.getLastName().charAt(1)) {
				return 1;
			} else {
				if (o1.getFirstName().charAt(0) < o2.getFirstName().charAt(0)) {
					return 1;
				} else if (o1.getFirstName().charAt(1) < o2.getFirstName().charAt(1)) {
					return 1;
				}
			}
		}
		return -1;
	}
}
