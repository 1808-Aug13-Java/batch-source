package com.revature.resources;

/** A class full of mathematical helper methods. */
public class MathHelper {
	
	/** Converts the specified argument from degrees to radians. 
	 * @param deg - A value of degrees to convert to radians
	 * @returns The provided value converted from degrees to radians. */
	public static double degreesToRadians(double deg) {
		return (deg / 180d) * Math.PI;
	} // end of degreesToRadians
	
	/** Converts the specified argument from radians to degrees. 
	 * @param rad - A value of radians to convert to degrees
	 * @returns The provided value converted from radians to degrees. */
	public static double radiansToDegrees(double rad) {
		return (rad / Math.PI) * 180d;
	} // end of radiansToDegrees
	
	
} // end of class MathHelper


