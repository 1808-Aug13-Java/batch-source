package com.revature.resources;

import java.util.HashSet;
import java.util.LinkedList;

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
	
	
	/**  
	 * Returns the set of prime numbers up to a specified long. 
	 * @param l - The limit on prime numbers to add to the set
	 * @return The set of prime numbers up to the provided number
	 */
	public HashSet<Long> getPrimes(long l) {
		HashSet<Long> primes = new HashSet<>();
		
		// Go through each number from 2 to l, (as 0 & 1 are not prime). 
		// If a number is prime, add it to the set. 
		for (long i=0; i<l; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	} // end of getPrimes
	
	
	/** Returns true if the specified number is prime. False otherwise. */
	public boolean isPrime(long l) {
		// Get the square root of the long, as there will never be a factor greater than
		// its square root. The loss of precision is accounted by incrementing by one, which
		// will cover cases where we might miss a number if l > 2^52. 
		long sqrt = (long) Math.sqrt(l) + 1L;
		
		// A count of the number of factors
		long factors = 0;
		
		// There are only two factors in a prime number, itself and 1. 
		final long FACTORS_OF_A_PRIME = 2;
		
		// Go through each number from 1 up to the square root and check to see if it is 
		// a factor of l. If it is, add 2, one for it, and one for its compliment. 
		// If there are ever more than 2 factors, it is not a prime number
		for (long i=1; i < sqrt && factors <= FACTORS_OF_A_PRIME; i++) {
			if (l % i == 0) {
				factors++;
				
				// Only add the complementing factor if it isn't a perfect square or 1
				if (i * i != l) {
					factors++;
				}
			}
		}
		
		// If there are not two factors, it is not prime.
		return factors == FACTORS_OF_A_PRIME;
	} // end of isPrime
	
	/** 
	 * Provided a long, computes the factors of said long. 
	 * Negative numbers not implemented. 
	 * @param l - The long to compute the factors of. 
	 * @return A list of factors
	 */
	public LinkedList<Long> getFactors(long l) {
		// The list of factors
		LinkedList<Long> factors = new LinkedList<>();
		
		// Get the square root of the long, as there will never be a factor greater than
		// its square root. The loss of precision is accounted by incrementing by one, which
		// will cover cases where we might miss a number if l > 2^52. 
		long sqrt = (long) Math.sqrt(l) + 1L;
		
		// Go through each number from 1 up to the square root and check to see if it is 
		// a factor of l. If it is, add it and its complementary factor to the list
		for (long i=1; i<sqrt; i++) {
			if (l % i == 0) {
				factors.add(i);
				
				// Only add the complementing factor if it isn't a perfect square
				if (i * i != l) {
					factors.add(l / i);
				}
			}
		}
		
		return factors;
	} // end of getFactors
	
} // end of class MathHelper


