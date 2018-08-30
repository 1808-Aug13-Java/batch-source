
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
	console.log(email.match(regexp));
	// Works properly on regexer, but not here for whatever reason. 
	console.log(email.match("[!#$%&'*+\-/=?^_`{|}~.a-zA-Z0-9]+@([a-zA-Z]+\.[a-zA-Z]+)+"));
	return ((email.match(regexp)||[""])[0].length == email.length) &&
			(email.match("[!#$%&'*+\-/=?^_`{|}~.a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+") != null);
}

let testEmail = "david.calkins.1000@gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins@.1000@gmail.com";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins.1000@gmail";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));
testEmail = "david.calkins.1000@";
console.log("'" + testEmail + "' validity: " + isValidEmail(testEmail));

