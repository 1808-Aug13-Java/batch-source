package com.revature.arrays;

import java.util.Arrays;

public class ArrayDriver {

	public static void main(String[] args) {
		
		// 1D Arrays
		int[] intArray1 = new int[5]; // this is the preferred method
		int intArray2[] = new int[5]; // technically, also works
		int[] intArray3 = {1,2,7,9};
		
		System.out.println(intArray3[0]);
		System.out.println(intArray3[3]);

		// accessing an index out of the alloted elements gives us an ArrayIndexOutOfBounds exception
//		System.out.println(intArray3[10]);
		System.out.println(Arrays.toString(intArray3));
		
		// 2D Array
		int[] int2Darray[] = new int[3][4]; // technically this works
		int[][] int2Darray2 = {{2}, {3,4}, {2,1}, {0,3,2,7}};
		
		for (int i = 0; i < int2Darray2.length; i++) {
			for (int j = 0; j < int2Darray2[i].length; j++) {
				System.out.print(int2Darray2[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int[] i: int2Darray2) {
			for (int j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}

}
