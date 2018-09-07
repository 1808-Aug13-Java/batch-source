package cam.revature.types;

import java.util.Arrays;

public class ArrayDriver {

	public static void main(String[] args) {

		//id arrays
		int[] intArray = new int[5]; //prefered method
		int intArray2[] = new int[5];
		int[] intArray3 = {1,2,7,9};

		System.out.println(intArray3[1]);
		System.out.println(intArray3[0]);
		System.out.println(intArray3[3]);
		
		System.out.println(Arrays.toString(intArray3));
		
		//2d arrays
		int[][] int2dArray = new int[3][4];
		int[][] int2dArray2 = {{2},{3,4},{2,1},{0,3,2,7}};
		
		for(int i = 0; i<int2dArray2.length;i++) {
			for(int j = 0; j<int2dArray2[i].length;j++) {
				System.out.print(int2dArray2[i][j] + " ");
			}
			System.out.println("");
			
		}
	}

}
