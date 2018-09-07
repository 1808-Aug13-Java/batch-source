package com.revature.eval.java.core;

import static java.lang.Math.toIntExact;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * Using a for loop I grab each element form the string starting at the end and
	 * decrement backgrounds placing each grabbed element into the reversedStr using
	 * concat
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		String reversedStr = "";
		for (int i = (string.length() - 1); i >= 0; i--) {
			reversedStr = reversedStr.concat(Character.toString(string.charAt(i)));
		}
		return reversedStr;
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
		if (phrase.length() < 1) {
			return null;
		}
		String[] eachWord = phrase.split("\\s+|-");
		String acronym = "";

		for (String word : eachWord) {
			acronym = acronym.concat(Character.toString(word.charAt(0)));
		}
		return acronym.toUpperCase();
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
			if ((sideOne == sideTwo) && (sideOne == sideThree) && (sideTwo == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			if ((sideOne == sideTwo) || (sideOne == sideThree) || (sideTwo == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			if ((sideOne != sideTwo) && (sideOne != sideThree) && (sideTwo != sideThree)) {
				return true;
			}
			return false;
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
	public int getScrabbleScore(String string) {

		char[] letters = new char[string.length()];
		string = string.toLowerCase();
		int score = 0;

		for (int j = 0; j < string.length(); j++) {
			letters[j] = string.charAt(j);
		}

		for (char letter : letters) {
			switch (letter) {
			case 'a':
				score = score + 1;
				break;
			case 'b':
				score = score + 3;
				break;
			case 'c':
				score = score + 3;
				break;
			case 'd':
				score = score + 2;
				break;
			case 'e':
				score = score + 1;
				break;
			case 'f':
				score = score + 4;
				break;
			case 'g':
				score = score + 2;
				break;
			case 'h':
				score = score + 4;
				break;
			case 'i':
				score = score + 1;
				break;
			case 'j':
				score = score + 8;
				break;
			case 'k':
				score = score + 5;
				break;
			case 'l':
				score = score + 1;
				break;
			case 'm':
				score = score + 3;
				break;
			case 'n':
				score = score + 1;
				break;
			case 'o':
				score = score + 1;
				break;
			case 'p':
				score = score + 3;
				break;
			case 'q':
				score = score + 10;
				break;
			case 'r':
				score = score + 1;
				break;
			case 's':
				score = score + 1;
				break;
			case 't':
				score = score + 1;
				break;
			case 'u':
				score = score + 1;
				break;
			case 'v':
				score = score + 4;
				break;
			case 'w':
				score = score + 4;
				break;
			case 'x':
				score = score + 8;
				break;
			case 'y':
				score = score + 4;
				break;
			case 'z':
				score = score + 10;
				break;
			default:
				System.out.println("Your scrabble word contains an invalid character. Can not compute score.");
				break;

			}

		}
		return score;
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
		/*
		 * Using a regular expression to remove all non-valid characters for a phone
		 * number I create a new string with all non alpha numeric characters and white
		 * spaces removed. Any strings that have too many or too few characters or
		 * letters after this process are not valid and throw an exception. Also since
		 * we are not handling any international numbers all of the phone numbers after
		 * processing will only have length of ten.
		 */
		String processedStr = string.replaceAll("\\s+|\\p{Punct}|-|!|@|#|$|%|^|&|\\*|=|\\+", "");

		if ((processedStr.matches("[0-9]*[a-z][a-z]*[0-9]*")) || (processedStr.length() > 11)
				|| (processedStr.length() < 10)) {
			throw new IllegalArgumentException();
		} // I originally did a try catch block, but could not get it to be a success in
			// the junit test. for time sake I did this if statement

		return processedStr;
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
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> wCount = new HashMap<String, Integer>();
		String[] stringP = string.split("\\s+|\\p{Punct}");

		for (String word : stringP) {
			if (word.isEmpty()) {
			} else {
				Integer freq = wCount.get(word);
				wCount.put(word, (freq == null) ? 1 : freq + 1);
			}
		}
		return wCount;
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

		public int indexOf(T t) {
			int start = 0;
			int end = sortedList.size();
			List<Integer> sortedList2 = new ArrayList<Integer>();
			for (T str : sortedList) {
				sortedList2.add(Integer.valueOf(str.toString()));
			}

			while (start <= end) {
				int mid = Math.floorDiv((start + end), 2);
				if (t.toString().equals(sortedList.get(mid).toString())) {
					return mid;
				}
				if (Integer.valueOf(t.toString()) < sortedList2.get(mid)) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}

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
	public String toPigLatin(String string) {

		String[] eachWord = string.split("\\s+");
		StringBuffer pigStr = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (String str : eachWord) {
			char firstChar = str.charAt(0);
			pigStr.append(str);

			switch (firstChar) {

			case 'a':
				pigStr.append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			case 'e':
				pigStr.append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			case 'i':
				pigStr.append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			case 'o':
				pigStr.append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			case 'u':
				pigStr.append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			default:
				pigStr.deleteCharAt(0);
				pigStr.append(firstChar).append("ay ");
				result.append(pigStr);
				pigStr.delete(0, pigStr.length());
				break;
			// TODO handle edge cases - prob write method to call from default to handle a
			// recursive switch case
			}
		}

		return result.toString().trim();
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
		String stringifiedInput = String.valueOf(input);
		int[] intArr = new int[stringifiedInput.length()];
		int i = 0;
		int j = 0;
		int result = 0;
		for (int k : intArr) {
			i++;
		}
		for (int k : intArr) {
			intArr[j] = Character.getNumericValue(stringifiedInput.charAt(j));
			j++;
			result = result + (int) Math.pow(intArr[j - 1], i);
		}
		if (result == input) {
			return true;
		}
		return false;
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
		List<Long> result = new ArrayList<Long>();
		int num = toIntExact(l); // new to java 8 this safely casts a long to int
		/*
		 * this loop efficiently checks for a prime number using the modulo operator the
		 * while loop is used so that it maintains persistence of the l=l/i statement.
		 * this would probably be better as a recursive method, especially if we were
		 * going to use larger values
		 */
		for (int i = 2; i <= num; i++) {
			while (l % i == 0) {
				result.add((long) i);
				l = l / i;
			}
		}

		return result;
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
			final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
			int charPos;
			String cypheredTxt = "";

			for (int i = 0; i < string.length(); i++) {
				boolean isUpper = Character.isUpperCase(string.charAt(i));
				charPos = ALPHA.indexOf(Character.toLowerCase(string.charAt(i)));
				if (charPos == -1) {
					if (string.charAt(i) == ' ') {
						cypheredTxt = cypheredTxt.concat(" ");
					} else if (string.substring(i, i + 1).matches("[0-9]|\\p{Punct}")) {
						cypheredTxt = cypheredTxt.concat(string.substring(i, i + 1));
					}
				} else {
					int keyValue = (key + charPos) % 26;
					char replaceVal = ALPHA.charAt(keyValue);
					if (isUpper) {
						cypheredTxt = cypheredTxt.concat(Character.toString(replaceVal).toUpperCase());
					} else {
						cypheredTxt = cypheredTxt.concat(Character.toString(replaceVal));
					}
				}

			}
			return cypheredTxt;
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
		int nthPrime;
		int j = 0;
		if(i ==0) {
			throw new IllegalArgumentException();
		}
		for (nthPrime = 2; j < i; nthPrime++) {
			if (isPrime(nthPrime)) {
				j++;
			}
		}
		return nthPrime - 1;
	}

	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if ((n % i) == 0) {
				return false;
			}
		}
		return true;
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
	 * Examples Encoding test gives gvhg :Decoding gvhg gives test: Decoding gsvjf
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
		public static String encode(String string) {
			final String ALPHA = "abcdefghijklmnopqrstuvwxyz0123456789";
			final String RALPHA = "zyxwvutsrqponmlkjihgfedcba0123456789";
			int charPos;
			String atbashTxt = "";
			String processedStr = string.replaceAll("\\s+|\\p{Punct}", "");

			for (int i = 0; i < processedStr.length(); i++) {
				charPos = ALPHA.indexOf(Character.toLowerCase(processedStr.charAt(i)));
				int j = i;
				char replaceVal = RALPHA.charAt(charPos);
				if (((j % 5) == 0)) {
					atbashTxt = atbashTxt.concat(" ");
					atbashTxt = atbashTxt.concat(Character.toString(replaceVal));
				} else {
					atbashTxt = atbashTxt.concat(Character.toString(replaceVal));
				}

			}
			return atbashTxt.trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			final String RALPHA = "abcdefghijklmnopqrstuvwxyz0123456789";
			final String ALPHA = "zyxwvutsrqponmlkjihgfedcba0123456789";
			int charPos;
			String rAtbashTxt = "";
			String processedStr = string.replaceAll("\\s+|\\p{Punct}", "");

			for (int i = 0; i < processedStr.length(); i++) {
				charPos = ALPHA.indexOf(Character.toLowerCase(processedStr.charAt(i)));
				char replaceVal = RALPHA.charAt(charPos);
				rAtbashTxt = rAtbashTxt.concat(Character.toString(replaceVal));
			}
			return rAtbashTxt.trim();
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
	public boolean isValidIsbn(String string) {
		String processedStr = string.replaceAll("\\s+|\\p{Punct}", "");
		char[] ISBN = processedStr.toCharArray();
		int procISBN = 0;
		int j = 10;
		for(char c: ISBN) {
			if((c == 'X') && (j == 1)){
				procISBN = procISBN + 10 * j;
				return procISBN % 11 == 0 ? true : false;
			}else if(Character.toString(c).matches("[a-z]*|[A-Z]*")) {
				return false;
			}
			procISBN = procISBN + (Character.getNumericValue(c) * j);
			j--;
		}
		return procISBN % 11 == 0 ? true : false;
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
	public boolean isPangram(String string) {
		String processedStr = string.replaceAll("[^a-z]", "");
		char[] chars = processedStr.toCharArray();
        Set<Character> charSet = new HashSet<Character>();

        for(char c: chars) {
        	charSet.add(c);
        }
		return charSet.size() == 26 ? true : false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime updated;

		if (!given.isSupported(ChronoField.HOUR_OF_DAY)) {
			updated = (LocalDateTime.of(given.get(ChronoField.YEAR), given.get(ChronoField.MONTH_OF_YEAR),
					given.get(ChronoField.DAY_OF_MONTH), 0, 0, 0));

			return updated.plusSeconds(1_000_000_000);
		}

		updated = (LocalDateTime) given;
		return updated.plusSeconds(1_000_000_000);
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
	public int getSumOfMultiples(int i, int[] set) {
		ArrayList<Integer> allMul = new ArrayList<Integer>();
		HashSet<Integer> comMul = new HashSet<Integer>();
		int result = 0;
		for (int j : set) {
			for (int k = 1; k < i; k++) {
				if ((k % j) == 0) {
					allMul.add(k);
				}
			}
		}
		for (int l : allMul) {
			comMul.add(l);
		}
		for (int m : comMul) {
			result = result + m;
		}
		return result;
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
	public boolean isLuhnValid(String string) {
		int count = 0;
		int parsed;
		String processedStr = string.replaceAll("\\s+", "");
		if(processedStr.matches("[0-9]*[[A-Z]+|[a-z]][0-9]*+|\\p{Punct}")) {
			return false;
		}
		char[] cCardNum = processedStr.toCharArray();
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int luhn = 0;

		for (char c : cCardNum) {
			parsed = Integer.parseInt(String.valueOf(c));
			if ((processedStr.length() % 2) != 0) {
				if ((count % 2 != 0)) {
					parsed = parsed * 2;
					if (parsed > 9) {
						parsed = parsed - 9;
					}
				}
			} else {
				if ((count % 2 == 0)) {
					parsed = parsed * 2;
					if (parsed > 9) {
						parsed = parsed - 9;
					}
				}
			}
			count++;
			digits.add(parsed);
		}
		for (int d : digits) {
			luhn = luhn + d;
		}
		return luhn % 10 == 0 ? true : false;
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
	public int solveWordProblem(String string) {

		String processedStr = string.replaceAll("[What is]|\\s+|\\p{Punct}", "");
		
		String[] words = string.split("\\s+|\\?");
		ArrayList<String> problem = new ArrayList<String>();
		int result = 0;
		int first = 0;
		int second = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		String operator = "";
		for (String w : words) {
			if (w.equalsIgnoreCase("what") || w.equalsIgnoreCase("is") || w.equalsIgnoreCase("by")) {
				// do not add to list
			} else {
				problem.add(w);
			}
		}
		for (String p : problem) {
			if (p.equalsIgnoreCase("plus") || p.equalsIgnoreCase("minus") || p.equalsIgnoreCase("multiplied")
					|| p.equalsIgnoreCase("divided")) {
				operator = p;
			} else {
				nums.add(Integer.parseInt(p));
			}
		}

		if (operator.equalsIgnoreCase("plus")) {
			for (int n : nums) {
				result = result + n;
			}
		} else if (operator.equalsIgnoreCase("minus")) {
			int a = 0;
			for (int n : nums) {
				a++;
				if (a == 1) {
					first = n;
				} else {
					second = n;
				}
			}
			result = first - second;
		} else if (operator.equalsIgnoreCase("multiplied")) {
			int a = 0;
			for (int n : nums) {
				a++;
				if (a == 1) {
					first = n;
				} else {
					second = n;
				}
			}
			result = first * second;
		} else if (operator.equalsIgnoreCase("divided")) {
			int a = 0;
			for (int n : nums) {
				a++;
				if (a == 1) {
					first = n;
				} else {
					second = n;
				}
			}
			result = first / second;
		} else {
			/*
			 * invalid operator
			 */
		}

		return result;
	}

}
