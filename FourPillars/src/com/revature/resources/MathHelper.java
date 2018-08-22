package com.revature.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
	 * Returns the set of prime numbers up to and including a specified long. 
	 * The primes are in their natural ordering. 
	 * @param l - The limit on prime numbers to add to the set
	 * @return The set of prime numbers up to the provided number
	 */
	private ArrayList<Long> getPrimes(long l) {
		ArrayList<Long> primes = new ArrayList<>();
		
		// Used in optimizing the nubmer needed to investigate
		long sqrt;
		
		// A boolean used to when evaluating if the current number is prime
		boolean isPrime;
		
		// Go through each number from 2 to l inclusive, (as 0 & 1 are not prime). 
		// It needs to be inclusive as l itself might be prime. 
		// If a number is prime, add it to the set. 
		for (long i=2; i<=l; i++) {
			isPrime = true;
			// Get the square root of the long, as there will never be a factor 
			// greater than its square root. The loss of precision is accounted 
			// by incrementing by one, which will cover cases where we might miss 
			// a number if l > 2^52. 
			sqrt = (long) Math.sqrt(i) + 1L;
			
			// Loop through all previous prime numbers. If the current number doesn't 
			// contain one of these numbers as a factor, it too is prime as all 
			// composite numbers are composed of prime factors. 
			for (int j=0; j < primes.size() && isPrime; j++) {
				// Check if the current number has a prime factor. If so, it is 
				// not prime. 
				if (i % primes.get(j) == 0) {
					isPrime = false;
				}
				// We do not need to check primes greater than the square root of the 
				// current number we are checking. 
				if (primes.get(j) > sqrt) {
					break;
				}
			}
			
			// If the number is prime, add it to the list of primes
			if (isPrime) {
				primes.add(i);
			}
		}
		
		return primes;
	}
	
	/** Returns true if the specified number is prime. False otherwise. */
	private boolean isPrime(long l) {
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
	}
	
	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		
		// A list of prime factors of l
		LinkedList<Long> primeFactors = new LinkedList<>();
		
		// Get the set of primes up to l. That way, it doesn't need to be
		// recomputed for every factor extracted.
		ArrayList<Long> primes = getPrimes(l);
		
		// Used to hold a prime factor of l
		long prime = 0;
		
		
		// Continue extracting prime factors from l until we have extracted all
		// prime factors. Also, doesn't execute for anything less than 2. 
		while (l > 1) {
			// Find a prime factor of l, by iterating through the ordered prime 
			// numbers. 
			for (int i=0; i<primes.size(); i++) {
				prime = primes.get(i);
				// Test to see if the number provided is divisible by the specified 
				// prime. If so, add that prime to the list, extract this factor by 
				// dividing l by this factor, and break this loops 
				if (l % prime == 0) {
					primeFactors.add(prime);
					l /= prime;
					break;
				}
			}
		}
		
		return primeFactors;
	}
	
} // end of class MathHelper


