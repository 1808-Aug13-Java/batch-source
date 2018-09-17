/**
 * Chandrika Sinha JavaScript Assignment
 */

/********************************************
1.      Longest String
*********************************************/
function maxLength(array) {
	let length = 0;
	let index = -1;
	for (var i = 0; i < array.length; i++) {
		if (length < array[i].length) {
			index = i;
			length = array[i].length;
		}
	}
	return index;
}

/********************************************
2.      Reverse Array
*********************************************/
function reverseArray(array) {
	let reversedArray = [];
	while (typeof array !== 'undefined' && array.length > 0) {
		reversedArray.push(array.pop());
	}
	array = reversedArray;
	return array;
	
	// could also have done array.reverse();
}

/********************************************
3.     Count Vowels
*********************************************/
function vowelCount(string) {
	var vowels = string.match(/[aeiouAEIOU]/g);
	return vowels === null ? 0 : vowels.length;
}

/********************************************
4.      Remove Script
*********************************************/
function removeScript(string) {
	var withoutScript = string.replace(/Script/gi, '');
	return withoutScript;
}

/********************************************
5.      Find Leap Year
*********************************************/
function isLeapYear(date) {
	let hundredsMatch = date.getFullYear()%100!=0 || date.getFullYear()%400==0;
	return (date.getFullYear()%4==0 && hundredsMatch);
}

/********************************************
6.      Email Validation
*********************************************/
function isValidEmail(string) {
	return string.search(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)!=-1;
}

/********************************************
7.      Remove Character
*********************************************/
function removeChar(string, index) {
	if (index < 0 || index > string.length) {
		return string;
		// throw error? return -1? return false?
	}
	if (string.length==1) {
		return '';
	}
	return (string.slice(0,index) + string.slice(index+1));
}

/********************************************
8.      Bubble Sort
*********************************************/
function bubbleSort(numArray) {
	var numSwaps = numArray.length;
	while (numSwaps > 0) {
		numSwaps = 0;
		for (var i = 0; i < numArray.length-1; i++) {
			if (numArray[i] > numArray[i+1]) {
				var temp = numArray[i];
				numArray[i] = numArray[i+1];
				numArray[i+1] = temp;
				numSwaps++;
			}
		}
	}
	return numArray;
}

/********************************************
9.      Even Number
*********************************************/
function isEven(someNum) {
	let someStringNum = someNum.toString();
	let lastDigit = someStringNum.slice(-1);
	return lastDigit.match(/[02468]/)!=null;
}

/********************************************
10.     Palindrome
*********************************************/
function isPalindrome(someStr) {
	for (var i = 0; i < someStr.length/2; i++) {
		if (someStr.charAt(i)!=someStr.charAt(someStr.length-1-i)) {
			return false;
		}
	}
	return true;
}

/********************************************
11.     Shapes
*********************************************/
function printShape(shape, height, character) {
	let output = '';
	switch (shape) {
	case 'Square':
		for (var i = 0; i < height; i++) {
			output = output + character.repeat(height) + '\n';
		}
		break;
	case 'Triangle':
		for (var i = 0; i < height; i++) {
			output = output + character.repeat(i+1) + '\n';
		}
		break;
	case 'Diamond':
		for (var i = 0; i < height/2; i++) {
			output = output + ' '.repeat(height/2 - i);
			output+=(character.repeat(2*i+1) + '\n');
		}
		for (var i = height/2-1; i > 0; i--) {
			output = output + ' '.repeat(height/2 - i);
			output+=(character.repeat(2*i) + '\n');
		}
		break;
	default:
		// idk
	}
	console.log(output);
	return;
}

/********************************************
12.   Rotate Left
*********************************************/
function rotate(array, n) {
	n = n%array.length;
	for (var i = 0; i < n; i++) {
		array.push(array.shift());
	}
	return array;
}

/********************************************
13.   Balanced Brackets
*********************************************/
function balanced(string) {
	var bracketArray = [];
	var brackets = '';
	for (var i = 0; i < string.length; i++) {
		if (string.charAt(i)=='(') {
			bracketArray.push(string.charAt(i));
		} else if (string.charAt(i)==')') {
			if (bracketArray[bracketArray.length-1]!='(') {
				return false;
			}
			bracketArray.pop();
		} else if (string.charAt(i)=='[') {
			bracketArray.push(string.charAt(i));
		} else if (string.charAt(i)==']') {
			if (bracketArray[bracketArray.length-1]!='[') {
				return false;
			}
			bracketArray.pop();
		} else if (string.charAt(i)=='{') {
			bracketArray.push(string.charAt(i));
		} else if (string.charAt(i)=='}') {
			if (bracketArray[bracketArray.length-1]!='{') {
				return false;
			}
			bracketArray.pop();
		}
	}
	return true;
}