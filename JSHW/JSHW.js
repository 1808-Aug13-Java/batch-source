
/*
1.      Longest String
Define function: maxLength(array)
Write a JavaScript to find the longest string from an given 
array of strings and returns the string’s array index.
*/
let array1 = ["here's one", "and another", "look at this string"];
let array2 = ['a','b','c'];
let array3 = [0,1,2,3,4,5,6,7,8,9];
let string1 = "Hippopotamous";
let string2 = "Scriptures";
let string3 = "prescriptions";

console.log("1.      Longest String");

function maxLength(arr) {
    let max = "";
    let maxIndex = "";
    for(i in arr) {
        if(max.length < arr[i].toString().length) {
            max = arr[i];
            maxIndex = i;
        } else if (max.length == arr[i].length) {
            maxIndex += ", " + i;
        }
	}
	return maxIndex;
    
}
console.log("Input: " + array1);
console.log("Input: " + array2);
console.log("Output:");
console.log(maxLength(array1));
console.log(maxLength(array2));
/*
2.      Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
*/

console.log("2.      Reverse Array");

function reverseArray(arr) {
	return arr.reverse();
}
console.log("Input: " + array1);
console.log("Input: " + array2);
console.log("Output:");

console.log(reverseArray(array1));
console.log(reverseArray(array2));

/*
3.     Count Vowels 
Define function: vowelCount(string)
 Write a JavaScript function to count the number of vowels in a given string.
 */
console.log("3.     Count Vowels ");
function vowelCount(string){
	
	string.toString()
	let x = string.match(/[aeiou]/gi);
	if(x === null){
		return 0;
	} else return x.length;
}
console.log("Input: " + string1);
console.log("Output:");

console.log(vowelCount(string1));

 /*
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" 
is present in a given string. If "Script" is present in the string 
return the string without "Script" otherwise return the original one.
*/
console.log("4.      Remove Script");

function removeScript(string) {

	 if(string.search(/Script/g) == -1) {
		 return string;
	 } else {
		let replace = string.replace("Script","");
		return replace
	 }

}
console.log("Input: " + string2);
console.log("Input: " + string3);
console.log("Output:");

console.log(removeScript(string2));
console.log(removeScript(string3));
/*
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true 
if the year is a leap year in the Gregorian calendar.
*/
console.log("5.      Find Leap Year");

function isLeapYear(date) {
	if(!((date % 400) && (date % 100) && (date % 4))) {
		return true;
	} else return false;
}

console.log("Input: 2016");
console.log("Input: 1804");
console.log("Input: 2017");
console.log("Output:");


console.log(isLeapYear(2016));
console.log(isLeapYear(1804));
console.log(isLeapYear(2017));

/*
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
*/

console.log("6.      Email Validation");

function isValidEmail(string) {
	let emailTest = /\S+@\S+\.\S+/
	return emailTest.test(string);
}
console.log("Input: julie@gmail.com");
console.log("Output:");

console.log(isValidEmail("julie@gmail.com"));

/* 
7.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position 
of a given string and return the new string.
*/
console.log("7.     Remove Character");

function removeChar(string, index) {
	let front = string.substr(0, index);
	let back = string.substr(index + 1);
	return front + back;	
}
console.log("Input: " + string1 + ", 3");
console.log("Input: " + string3 + ", 2");
console.log("Output:");

console.log(removeChar(string1, 3));
console.log(removeChar(string3, 2));

/*
8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.
*/
let numAry = [4, 3, 5, 2, 6, 7, 4000];

console.log("8.       Bubble Sort");
function bubbleSort(numArray) {
	let temp = 0;
	for(i = 0; i < numArray.length; i++) {
		for(j = 0; j < (numArray.length - i - 1); j++) {
			if(numArray[j] > numArray[j + 1] ) {
				temp = numArray[j];
				numArray[j] = numArray[j + 1];
				numArray[j + 1] = temp;
			}
		}
	}
	return numArray;

}
console.log("Input: " + numAry);
console.log("Output:");

console.log(bubbleSort(numAry));

/*
9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/
console.log("9.    Even Number");
function isEven(someNum) {
	return (someNum & 1) ? false : true;
}

console.log("Input: 42");
console.log("Input: 31");
console.log("Output:");

console.log(isEven(42));
console.log(isEven(31));

/* 
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.
*/

console.log('10.   Palindrome');

function isPalindrome(someStr) {
	let splitStr = someStr.split("");
	let reverseStr = splitStr.reverse();
	let testStr = reverseStr.join("");
	if(someStr === testStr) {
		return true;
	} else return false;
}
console.log("Input: able was i ere i saw elba" );
console.log("Input: Fuschia");
console.log("Output:");

console.log(isPalindrome("able was i ere i saw elba"));
console.log(isPalindrome("Fuschia")); 
/*
11.   Shapes
Define function: printShape(shape, height, character)
shape is a String and is either "Square", "Triangle", "Diamond".
height is a Number and is the height of the shape. Assume the number is odd.
character is a String that represents the contents of the shape.
Assume this String contains just one character.
Use a switch statement to determine which shape was passed in.
Use the console.log function to print the desired shape.
Example for printShape("Square", 3, "%");
%%%
%%%
%%%
Example for printShape("Triangle", 3, "$");
$
$$
$$$

Example for printShape("Diamond", 5, "*");
  *
 ***
*****
 ***
  *	
*/

console.log("11.	Shapes");

function printShape(shape, height, character) {
	let makeShape = "";
    switch(shape) {
        case "Square":
            for(i = 1; i <= height; i++) {
                for(j = 1; j <= height; j++) {
                    makeShape += character;
                }
            makeShape += "\n";
            }
            break;
        case "Triangle":
            for(i = 1; i <= height; i++) {
                for(j = 1; j <= i; j++) {
                    makeShape += character;
                }
            makeShape += "\n";
            }
            break;
        case "Diamond":
            for(i = 1; i <= height; i += 2) {
           		for(k = 1; k <= ((height - i) / 2); k++) {
                	makeShape += " ";
            	}
            	for(j = 1; j <= i; j++) {
                	makeShape += character;
            	}
            makeShape += "\n";
            }
			
			for(i = height - 2; i >= 1; i-= 2) {
           		for(k = 1; k <= ((height - i) / 2); k++) {
                	makeShape += " "
            	}
            		for(j = 1; j <= i; j++) {
                		makeShape += character;
            		}
            	makeShape += "\n";
            }
        break;
    }
    console.log(makeShape);
}

console.log("Input: Diamond, 7, #");
console.log("Input: Triangle, 5, ^");
console.log("Input: Square, 9, `");
printShape('Diamond', 7, '#');
printShape('Triangle', 5, '^');
printShape('Square', 9, '`')

/*  
12.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]
*/
console.log("12.   Rotate Left");
function rotate(array, n) {
	
	for(i = 1; i <= n; i++) {
		array.push(array.shift());	
	}

	return array;
}
console.log("Input: " + array2);
console.log("Input: " + array3);
console.log("Output:" );
console.log(rotate(array2, 1));
console.log(rotate(array3, 4));

/*
13.   Balanced Brackets
 	Define function: balanced(string)

A bracket is any one of the following: (, ), {, }, [, or ]
 
The following are balanced brackets:
()
()()
(())
({[]})
 
The following are NOT balanced brackets
(
)
(()i
([)]
 
Create a function which takes a string of brackets and returns true if balanced and false if not balanced
 */
function balanced(string) {
		let stack = {};
		let size = 0;
		let opener = ['(', '[', '{'];
		let closer = [')', ']', '}'];
		for(let i = 0; i < string.length; i++){
		  if( opener.indexOf(string[i]) !== -1 ){
			stack[size] = string[i];
			size++;
		  } else if(closer.indexOf(string[i]) !== -1 ){
			if(size === 0) { 
				return false; 
			}
			let index = closer.indexOf(string[i]);
			if(stack[size - 1] === opener[index] ){
			  size--;
			} else {
			  return false;
			}
		  }
		}
	  
		return size === 0;
};
console.log("Input: ()");
console.log("Input: ([{}])");
console.log("Input: ([)");
console.log("Output: ");


console.log(balanced("()"));
console.log(balanced("([{}])"));
console.log(balanced("([)"));

