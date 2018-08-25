package com.revature.arrays;

import java.util.Arrays;

public class ArrayDriver {
	
	public static void main(String[] args) {
		
		
		// 1d array
		int[] intArray1 = new int[5]; // preferred
		int intArray2[] = new int[5];
		int[] intArray3 = { 1, 2, 7, 9 };
	
		System.out.println(intArray3[3]);
		
		// index out of bounds EXCEPTION
		// System.out.println(intArray3[5]);
		
		System.out.println(Arrays.toString(intArray3));
		
		// 2d arrays
		int[][] int2Darray = new int[3][3];
		int[][] int2Darray2 = {
				{ 2 },
				{ 3, 4 },
				{ 2, 1, 3 }
		};
		
//		for(int i = 0; i < int2Darray2.length; i++) {
//			for(int j = 0; j < int2Darray2[i].length; j++) {
//				System.out.print(int2Darray2[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for( int[] i : int2Darray2) {
			for(int j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}
}