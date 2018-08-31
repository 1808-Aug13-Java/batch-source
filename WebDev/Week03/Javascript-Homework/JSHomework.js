
// Returns the index of the longest string in an array. 
// Returns undefined for a zero length array.
function maxLength(array) {
	if (array.length == 0) {
		return;
	}
	
	// Set the current max to the first one. 
	let maxLength = array[0].length;
	let maxIndex = 0;
	
	let tempLength;
	
	// Loop through each index. 
	for (i in array) {
		tempLength = array[i].length;
		
		// If the length of the current string is greater than the current max 
		// replace the current max. 
		if (tempLength > maxLength) {
			maxLength = tempLength;
			maxIndex = i;
		}
	}
	
	return maxIndex;
} // end of maxLength

let myArr = ["asdf", "qwer", "zxcvb", "123"];
console.log(myArr);
console.log("Max Index: " + maxLength(myArr));


// Reverses the elements of an array
function reverseArray(array) {
	let temp;
	// For each element, swap it with the element the same distance
	// from the end. 
	for (var i=0; i<array.length/2; i++) {
		temp = array[i];
		array[i] = array[array.length-1-i];
		array[array.length-1-i] = temp;
	}
} // end of reverseArray

reverseArray(myArr);
console.log("Reversed: " + myArr);



// Counts the number of vowels in a provided string
function vowelCount(string) {
	//Initialize a set of vowels. 
	let vowels = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
	
	// A count of the number of values
	let vowelCount = 0;
	
	// Count the number of values by iterating through each character
	for (character of string) {
		if (vowels.has(character)) {
			vowelCount++;
		}
	}
	
	return vowelCount;
} // end of vowelCount

console.log("Vowels: " + vowelCount("asdfqwer"));


// Returns a stirng with all instances of the string "Script" removed
function removeScript(string) {
	return string.replace(/Script/g, "");
}

let scriptString = "Scripted";
console.log("'" + scriptString + "': '" + removeScript(scriptString) + "'");
scriptString = "Scuplted";
console.log("'" + scriptString + "': '" + removeScript(scriptString) + "'");
scriptString = "scripted";
console.log("'" + scriptString + "': '" + removeScript(scriptString) + "'");
scriptString = "A Scripted Script";
console.log("'" + scriptString + "': '" + removeScript(scriptString) + "'");


// Returns if the specified date is a leap year. 
function isLeapYear(date) {
	if (date.getYear() == 0) {
		
	}
	return (date.getFullYear() % 4) == 0;
}

let testDate = new Date("Jan 20 1969");
console.log(testDate + " is leap year: " + isLeapYear(testDate));
testDate = new Date("Jan 20 2004");
console.log(testDate + " is leap year: " + isLeapYear(testDate));


// Returns true if the email format is valid. False otherwise. 
function isValidEmail(email) {
	// Handle special case when string is empty
	if (email.length == 0) {
		return false;
	}
	var regexp = /[^@]+@[^@]+/g;
	//Assert that there is only one '@' symbol, and only allowed characters for the 
	// local name, and one or more groups of letters surrounding a '.' after the '@'
	return ((email.match(regexp)||[""])[0].length == email.length) &&
			(email.match("[!#$%&'*+\-/=?^_`{|}~.a-zA-Z0-9]+@([a-zA-Z]+\\.[a-zA-Z]+)+") != null);
}

let testEmail = "david.calkins.1000@gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins@.1000@gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins.1000@gmail";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins.1000@";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins.1000@not.gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "@not.gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));


// Returns a string with the charcter at the specified index removed. 
function removeChar(string, index) {
	// Note: second parameter is length of substring, not second index. 
	return string.substr(0, index) + string.substr(index+1);
}
let testString = "asdfqwer";
console.log("'" + testString + "' removed 0: " + removeChar(testString, 0));
console.log("'" + testString + "' removed 7: " + removeChar(testString, 7));
console.log("'" + testString + "' removed 3: " + removeChar(testString, 3));


// Sorts the provided array using bubble sort. Provided elements must be 
// numbers. 
function bubbleSort(numArray) {
	//If the length is less than 2, do nothing, as it is already sorted. 
	if (numArray.length < 2) {
		return numArray;
	}
	
	// For each index above 0...
	for (let i=1; i<numArray.length; i++) {
		// Bubble down each element until i-1 by comparing it with the previous. 
		for (let j=numArray.length-1; j>=i; j--) {
			// If the previous element is greater than the current, swap them
			if (numArray[j-1]>numArray[j]) {
				let tempNum = numArray[j];
				numArray[j] = numArray[j-1];
				numArray[j-1] = tempNum;
			}
		}
	}
	
	return numArray;
} // end of bubbleSort

console.log(bubbleSort([5, 1, 3, 2, 9, 6, 4, 7, 8]));



// Returns true if a number is even, false otherwise. Non-numbers will return false.
function isEven(someNum) {
	// Account for special cases, like NaN and Infinity. 
	if (someNum == NaN || someNum == Infinity || someNum == -Infinity) {
		return false;
	}
	
	// If a number divided by two, floored, and then multiplied by two equals 
	// the original number, it is even. Flooring an even number/2 will do nothing, 
	// while flooring a non-even number/2 will change it. 
	return (Math.floor(someNum/2)*2) === someNum;
} // end of isEven

console.log("0 isEven: " + isEven(0));
console.log("1 isEven: " + isEven(1));
console.log("29 isEven: " + isEven(29));
console.log("1000 isEven: " + isEven(1000));
console.log("7.5 isEven: " + isEven(7.5));
console.log("-6 isEven: " + isEven(-6));
console.log("-2.1 isEven: " + isEven(-2.1));
console.log("EmptyString isEven: " + isEven(""));


// Returns true if the string is a palindrome, false otherwise. 
function isPalindrome(someString) {
	// Get rid of whitespace. 
	let replaceRegex = / /g;
	someString = someString.replace(replaceRegex, "");
	
	// For each index from the front and back respectively, compare the chatracter
	// from the fron and back. If not equal, it is not a palindrome. 
	for (let i=0; i<someString.length / 2; i++) {
		// If the lefthand character isn't whitespace, add it to the queue. 
		if (someString.charAt(i) !== someString.charAt(someString.length-1-i)) {
			return false;
		}
	}
	
	return true;
} // end of isPalindrome

let testPalindrome = "rats live on no evil star";
console.log("'" + testPalindrome + "' isPalindrome: " + isPalindrome(testPalindrome));
testPalindrome = "asd ffdsa";
console.log("'" + testPalindrome + "' isPalindrome: " + isPalindrome(testPalindrome));
testPalindrome = "qwertrewq";
console.log("'" + testPalindrome + "' isPalindrome: " + isPalindrome(testPalindrome));
testPalindrome = "qwertrrewq";
console.log("'" + testPalindrome + "' isPalindrome: " + isPalindrome(testPalindrome));


// Returns a new array with the given contents rotatesd left 'n' times. 
function rotate(array, n) {
	
}


