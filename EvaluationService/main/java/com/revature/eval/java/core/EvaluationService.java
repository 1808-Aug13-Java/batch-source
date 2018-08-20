package com.revature.eval.java.core;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {
	

	

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		
		// Do a null check
		if(string == null)
			return null;
		
		// Start with an empty string
		String returnable = "";
		
		// Simply go through the original string backwards
		for(int rust = string.length() - 1; rust > -1; rust--)
			returnable += string.charAt(rust);
		
		return returnable;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		// Split by any word separator, whitespace or dash
		String[] words = phrase.split("[ \n\t\r-]++");
		
		String returnable = "";
		
		// Add the First letter of each word
		for(int rust = 0; rust < words.length;rust++)
		{
			returnable += words[rust].charAt(0);
		}
		
		// Return, but make sure they're all capital letters
		return returnable.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			return sideThree == sideTwo && sideTwo == sideOne;
		}

		public boolean isIsosceles() {

			return sideThree == sideTwo || sideTwo == sideOne
					|| sideThree == sideOne;
		}

		public boolean isScalene() {
			return !isIsosceles();
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) 
	{
		// Create an array whose values indicate the scrabble scores and
		// indices represent the letter
		int[] scrabbleScores =
			{
					1, // A = 1
					3, // B = 3
					3, // C = 3
					2, // D = 2
					1, // E = 1
					4, // F = 4
					2, // G = 2
					4, // H = 4
					1, // I = 1
					8, // J = 8
					5, // K = 5
					1, // L = 1
					3, // M = 3
					1, // N = 1
					1, // O = 1
					3, // P = 3
					10, // Q = 10
					1, // R = 1
					1, // S = 1
					1, // T = 1
					1, // U = 1
					4, // V = 4
					4, // W = 4
					8, // X = 8
					4, // Y = 4
					10 // Z =  10
			};
		
		// Make it lower-case to simplify the operation
		string = string.toLowerCase();
		int returnable = 0;
		
		// Go through each letter and use the letter as an index to the array
		for(int c = 0; c < string.length();c++)
		{
			char ch = string.charAt(c);
			// Add up the score pointed to by the letter
			if(ch >= 'a' && ch <= 'z')
				returnable += scrabbleScores[ch - 'a'];
		}
		return returnable;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		String returnable = "";
		
		// Go through the string and append only numbers
		for(int rust = 0; rust < string.length();rust++)
		{
			char ch = string.charAt(rust);
			if(ch >= '0' && ch <= '9')
				returnable += ch;
		}
		
		// The length needs to be that of a phone number
		if(returnable.length() != 10 && returnable.length() != 9)
			throw new IllegalArgumentException();
		
		// Remove the 1 in a ten digit phone number if present
		if(returnable.length() == 10 && returnable.charAt(0) == '1')
			returnable = returnable.substring(1);
		
		return returnable;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) 
	{
		// Declare what needs to be returned
		Map<String, Integer> ret = new HashMap<String, Integer>();
		
		// Split by what actually separates words
		String[] words = string.split("[ \n\t\r,]++");
		
		// Go through each word
		for(int rust = 0; rust < words.length; rust++)
		{
			// Get the current count of the word
			Integer i = ret.get(words[rust]);
			if(i == null)
			{
				// First occurrence of the word, set it as 1
				ret.put(words[rust], 1);
			}
			else
			{
				// Already present, increment its count
				i += 1;
				ret.put(words[rust], i);
			}
		}
		
		return ret;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) 
		{
			// First check for bad input
			if(t == null)
				return -1;
			if (sortedList == null)
				return -1;
			
			
			// Set up the half-way point as well as the mechanism for 
			// moving the index up or down
			int index = sortedList.size() / 2;
			int div = index / 2;
			
			int count = 0;
			
			try
			{
				// Under this scenario, we assume we are working with Integers
				while(div >= 0 && count <= 4)
				{
					// Get the item to check
					T item = sortedList.get(index);
					if((Integer)item > (Integer)t)
					{
						// We are too high, go lower
						index -= div;
					}
					else if((Integer)item < (Integer)t)
					{
						// We are too low, go higher
						index += div;
					}
					else // We found it!
						return index;
					
					// Reduce the index mover
					div /= 2;
					
					// If it gets to zero (and it has during testing), make it one
					// Else we're stuck
					if(div == 0)
						div++;
				
					// increment the one count so we don't search forever for what's
					// not there
					if(div == 1)
						count++;
				}
			}
			catch(ClassCastException e)
			{
				try
				{
					// Assuming it is a String
					Integer i = Integer.parseInt((String)t);
					
					// Follow the same procedure as above
					while(div >= 0 && count <= 4)
					{
						T sItem = sortedList.get(index);
						Integer item = Integer.parseInt((String)sItem);
						if(item > i)
						{
						index -= div;
						}
						else if(item < i)
						{
						index += div;
						}
						else
						return index;
					div /= 2;
					
					if(div == 0)
						div++;
					
					if(div == 1)
						count++;
					}
				}
				catch(ClassCastException | NumberFormatException e2)
				{
					return -1; // The String theory didn't work
				}
			}
			
			// We have not found our target
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * isVowel - helper method to report a letter's status as a vowel or not
	 * @param c - the character to look at
	 * @return boolean - whether the provided letter is a vowel or not
	 */
	private boolean isVowel(char c)
	{
		return c == 'a' || c == 'e' || c == 'i'
				|| c == 'o' || c == 'u';
	}
	
	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) 
	{
		// Split up the words
		String[] words = string.split("[ \n\t\r,]++");
		
		// Go through each word
		for(int rust = 0; rust < words.length; rust++)
		{
			String word = words[rust];
			
			// Get the first character.
			char fl = word.charAt(0);
			if(isVowel(fl))
			{
				// If first letter is vowel, simply add "ay" at the end
				word += "ay";
			}
			else
			{
				// Put non-vowels at the end
				fl = Character.toLowerCase(fl);
				word += fl;
				
				// Handle the "qu" case
				if(fl == 'q')
				{
					word += "u";
					word = word.substring(2);
				}
				else if(word.length() > 1 && !isVowel(word.charAt(1)))
				{
					// Need to put all non-vowels before the first vowel in the back 
					int c = 1;
					for( ; c < word.length() && !isVowel(word.charAt(c)); c++)
					{
						
					}
					for(int C = 1; C < c ; C++)
						word += word.charAt(C);
					
					// Remove the moved letters from the front
					word = word.substring(c);
				}
				else
					word = word.substring(1);
				word += "ay";
				
			}
			// Update word array
			words[rust] = word;
		}
		
		String ret = "";
		
		// Put spaces between the words...
		for(int c = 0; c < words.length; c++)
		{
			ret += words[c];
			
			// ... but not the end of the phrase
			if(c != words.length - 1)
				ret += " ";
		}
		
		return ret;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		int test = 1, digitCount = 0;
		
		// Need to figure out how many digits there are
		while(input / test > 0)
		{
			test *= 10;
			digitCount++;
		}
		
		// Make sure dividing by test gets us above zero
		test /= 10;
		int cutInput = input;
		
		int armComp = 0;
		
		// Go through all the digits
		while(cutInput > 0 && test > 0)
		{
			// Add by the digit value pow'd to the digit count
			armComp += Math.pow(cutInput/test, digitCount);
			
			// Remove the just used digit
			cutInput = cutInput - (cutInput / test) * test;

			// Reduce the divider to get the next digit
			test /= 10;
		}
		
		return input == armComp;
	}
	
	/**
	 * isPrime - brute-force helper method for determining if a number is prime
	 * @param l - the number to check
	 * @return boolean - whether @param l is prime
	 */
	private boolean isPrime(long l)
	{
		// Start at 2 and stop before l as (1 and l) are acceptable in a prime number
		for(long c = 2; c < l; c++)
		{
			// if true, then we found a factor that breaks l's status as a prime number
			if(l % c == 0)
			{
				return false;
			}
		}
		return true;
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
	public List<Long> calculatePrimeFactorsOf(long l) 
	{
		// Set up an arrayList to return
		ArrayList<Long> ret = new ArrayList<Long>();
		
		// If our parameter is already prime, we have a base case
		if(isPrime(l))
		{
			ret.add(l);
			return ret;
		}
		
		// We don't have our base case so break our number down further
		for(long rust = 2; rust <= (l / 2); rust++)
		{
			if(l % rust == 0)
			{
				// We found potential factors so check them both
				if(isPrime(rust))
					ret.add(rust);
				else
				{
					// We have a factor, but not a prime number so break it down further
					ArrayList<Long> r = (ArrayList<Long>) calculatePrimeFactorsOf(rust);
					ret.addAll(r);
				}
				
				// Get the other factor and repeat the break down
				long other = l / rust;
				if(isPrime(other))
					ret.add(other);
				else
				{
					ArrayList<Long> r = (ArrayList<Long>) calculatePrimeFactorsOf(other);
					ret.addAll(r);
				}
				break;
			}
		}
		
		return ret;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			// Start out with an empty string and perform a null check on the parameter
			String ret = "";
			if(string == null)
				return ret;
			
			// Go through each letter and perform the rotation
			for(int rust = 0; rust < string.length();rust++)
			{
				char ch = string.charAt(rust);
				if(ch >= 'a' && ch <= 'z')
				{
					// Add the key to the lower-case letter and reduce value by 26 if over 'z'
					ch += key;
					if (ch > 'z')
						ch -= 26;
				}
				else if(ch >= 'A' && ch <= 'Z')
				{
					// Add key to upper-case letter
					ch += key;
					if (ch > 'Z')
						ch -= 26;
				}
				
				// Regardless of the character, add it to the returnable string
				ret += ch;
			}
			
			return ret;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		// Don't work if target number is 0 or below
		if(i<1)
			throw new IllegalArgumentException();
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		
		// Handle basic cases of known prime numbers
		switch(i)
		{
		case 1:
			return 2;
		case 2: 
			return 3;
		case 3:
			return 5;
		case 4:
			return 7;
		case 5:
			return 11;
		case 6:
			return 13;
		}
		
		// keep track of prime numbers beyond the 6th
		ints.add(2);
		ints.add(3);
		ints.add(5);
		ints.add(7);
		ints.add(11);
		ints.add(13);
		
		// Keep adding prime numbers until the target one is found
		for(int rust = 17; ints.size() < i; rust += 2)
		{
			if(isPrime(rust))
				ints.add(rust);
		}
		
		// Return the number at the end, for it is the target number
		return ints.get(ints.size() - 1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) 
		{
			// Set up an empty string and null-check the parameter
			String ret = "";
			
			if(string == null)
				return ret;
			
			// Go through each letter and perform the operation
			for(int c = 0, rust = 0; c < string.length(); c++)
			{
				char ch = string.charAt(c);
				
				// For both capital and lower-case letters, set letter value 
				// to raw numeric value, perform the switching operation, and 
				// Restore it to letter status
				if(ch >= 'a' && ch <= 'z')
				{
					ch -= 'a';
					ch = (char) (25 - ch);
					ch += 'a';
				}
				else if(ch >= 'A' && ch <= 'Z')
				{
					ch -= 'A';
					ch = (char) (25 - ch);
					ch += 'A';
				}
				else if(!(ch >= '0' && ch <= '9'))
					continue; // make sure to include numbers
				ret += ch;
				
				// Check to ensure spaces are added after every five letters
				if(rust % 5 == 4)
					ret += " ";
				
				// rust (variable) is used since some characters are omitted from the resulting cipher
				rust++;
			}
			
			// Needs to be lower-case and trimmed
			return ret.toLowerCase().trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// Set up empty string and null-check the parameter
			String ret = "";
			
			if(string == null)
				return ret;
			
			// Perform the same loop as in decode but omitting the spacing step
			for(int c = 0; c < string.length(); c++)
			{
				char ch = string.charAt(c);
				if(ch >= 'a' && ch <= 'z')
				{
					ch -= 'a';
					ch = (char) (25 - ch);
					ch += 'a';
				}
				else if(ch >= 'A' && ch <= 'Z')
				{
					ch -= 'A';
					ch = (char) (25 - ch);
					ch += 'A';
				}
				else if(!(ch >= '0' && ch <= '9'))
					continue;
				ret += ch;
			}
			
			return ret;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) 
	{
		// Set up collection of Integers to operate on
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		for(int c = 0; c < string.length();c++)
		{
			char character = string.charAt(c);
			// If character is a number add it
			if(character >= 48 && character <= 57)
				ints.add(character - 48);
			
			// if Character is an 'x/X', treat it like a ten
			else if (character == 'x' || character == 'X')
				ints.add(10);
		}
		
		// Number needs to be 10 digits
		if(ints.size() != 10)
			return false;
	
		// Get the sum of all digits
		int operand = 0;
		for(int c = 0; c < 10; c++)
			operand += (ints.get(c) * (10 - c));
		// mod by 11 to get number's validity
		return operand % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string)
	{
		// Set up array of booleans to get the status of each letter
		boolean [] letterEncountered = new boolean[26];
		
		// Go through the entire string
		for(int c = 0; c < string.length();c++)
		{
			char character = string.charAt(c);
			
			// For each letter, mark it as encountered
			if(character >= 'a' && character <= 'z')
			{
				letterEncountered[(int)character - (int)'a'] = true;
			}
			else if(character >= 'A' && character <= 'Z')
			{
				letterEncountered[(int)character - (int)'A'] = true;
			}
		}
		
		// If one letter has not been encountered, not a pangram
		for(int rust = 0; rust < letterEncountered.length; rust++)
		{
			if(!letterEncountered[rust])
				return false;
		}
		
		// All letters have been encountered
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) 
	{
		// Return null if we're given null
		if(given == null)
			return null;
		
		// Set up values for if the given Temporal supports it or not
		int hour = 0, minute = 0, second = 0, sunSec = 0;
		
		try
		{
			hour = given.get(ChronoField.HOUR_OF_DAY);
			minute = given.get(ChronoField.MINUTE_OF_HOUR);
			second = given.get(ChronoField.SECOND_OF_MINUTE);
			sunSec = given.get(ChronoField.NANO_OF_SECOND);
		}
		catch(UnsupportedTemporalTypeException e)
		{
			// If the Temporal does not support a given field, simply leave it as 0
		}
		
		// Set up a new temporal to return, with most fields initialized
		Temporal t = LocalDateTime.of(given.get(ChronoField.YEAR),
				given.get(ChronoField.MONTH_OF_YEAR),
				given.get(ChronoField.DAY_OF_MONTH),
				hour,
				minute,
				second,
				sunSec);
		
		// add the Gigasecond to the time
		return t.plus((long)1000000000, ChronoUnit.SECONDS);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) 
	{
		// Check for invalid input
		if(set == null || i < 1 || set.length == 0)
			return 0;
		
		// Set up a collection of multiples
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		// Get the multiples of each value
		for(int C = 0; C < set.length; C++)
		{
			int mult = set[C];
			// Make sure their products are not above the max value
			for(int rust = 1; rust * mult < i; rust++)
			{
				// Needs to be unique, don't add if already present
				if(ints.contains(new Integer(rust*mult)))
					continue;
				
				// Add the value
				ints.add(new Integer(rust* mult));
			}
		}
		
		int returnable = 0;
		
		// Now add all the numbers up
		for(int rust = 0; rust < ints.size();rust++)
			returnable += ints.get(rust);
		
		
		return returnable;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) 
	{
		// Take care of invalid input
		if(string == null || string == "")
			return false;
		
		// Set up a string of only numbers
		String nums = "";
		for(int rust = 0; rust < string.length(); rust++)
		{
			char ch = string.charAt(rust);
			if(ch >= '0' && ch <= '9')
				nums += ch;
			
			// Spaces are allowed, other non-numerics are not
			else if(ch != ' ')
				return false;
		}
		
		// Start adding to the modified number
		String nums2 = "" + nums.charAt(nums.length()-1);
		
		// Go through, starting at the end and double every other digit
		for(int c = nums.length() - 2, rust = 0; c >= 0; c--, rust++)
		{
			if(rust % 2 == 0)
			{
				int chd = (int)(nums.charAt(c) - '0');
				
				// double the digit
				chd *= 2;
				
				// If above nine, reduce by 9
				if(chd > 9)
					chd -= 9;
				
				// Return number to a character number
				chd += '0';
				
				// Add to the updated string
				nums2 = (char)chd + nums2;
			}
			else
				nums2 = nums.charAt(c) + nums2;
		}
		
		// Convert the string to a collection of integers
		ArrayList<Integer> sums = new ArrayList<Integer>();
		
		for(int c = 0; c < nums2.length(); c++)
		{
			sums.add(nums2.charAt(c) - 48);
		}
		
		// Prepare to add these digits
		int sum = 0;
		
		for(int rust = 0; rust < sums.size();rust++)
		{
			sum += sums.get(rust);
		}
		
		// whether or not the number is divisible by 10
		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) 
	{
		// Handle null parameter case
		if(string == null)
			return 0;
		
		// Split the words by white-space
		String[] words = string.split("[ \n\t\r]++");
		
		// All expected input has at least 5 words in them
		if(words.length < 5)
			return 0;
		// Get the value of the question
		int a = 0, b = 0;
		try
		{
			// Get the first number easily
			a = Integer.parseInt(words[2]);
			
			// Filter out the question marks
			words[4] = words[4].replace("?", "");
			if(words.length > 5)
				words[5] = words[5].replace("?", "");
			
			// Figure out what operation we're actually doing
			// Plus/minus indicate the second number is index 4
			// Multiply/Divide "by" indicate 2nd number is index 5
			switch(words[3])
			{
			case "plus":
				b = Integer.parseInt(words[4]);
				return a + b;
			case "minus":
				b = Integer.parseInt(words[4]);
				return a - b;
			case "multiplied":
				b = Integer.parseInt(words[5]);
				return a * b;
			case "divided":
				b = Integer.parseInt(words[5]);
				return a / b;
			}
		}
		catch(NumberFormatException | ArithmeticException e)
		{
			return 0;
		}
		
		return 0;
	}

}
