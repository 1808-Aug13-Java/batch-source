/*----------------------------------------------------------------------------------------------------------------------
1.      Longest String
Define function: maxLength(array)
Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.
*/

var array = ["Saab", "Volvo", "BMW"];

function maxLength(array){
    var stringLengthsArray = [];
    var i;
    for (i = 0; i < array.length; i++) { 
        stringLengthsArray.push(array[i].length);
    }
    //console.log("String lengths array:  ".concat(stringLengthsArray));

    var maxOfArray = Math.max.apply(Math, stringLengthsArray);

    for (i = 0; i < stringLengthsArray.length; i++) { 
        if(stringLengthsArray[i]===maxOfArray){
            console.log("index is " + i);
            return i;
        }
    }
}

maxLength(array);

/*----------------------------------------------------------------------------------------------------------------------
2.      Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
*/

function reverseArray(array){
    console.log(array.reverse());
    return array.reverse();
}

reverseArray(array);

/*----------------------------------------------------------------------------------------------------------------------
3.     Count Vowels 
	Define function: vowelCount(string)
	 Write a JavaScript function to count the number of vowels in a given string.
*/

var thing = "the brown dog";

function vowelCount(thing){
    var count = 0;

    var i;
    for (i = 0; i < thing.length; i++) { 
        if (thing.substring(i,i+1)==="a" || thing.substring(i,i+1)==="e"
        || thing.substring(i,i+1)==="i" || thing.substring(i,i+1)==="o"
        || thing.substring(i,i+1)==="u") {
            count++;
        }
    }
    console.log("The count is  "+ count);
    return count;
}

vowelCount(thing);

/*----------------------------------------------------------------------------------------------------------------------
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" is present in a given string.
If "Script" is present in the string return the string without "Script" otherwise return the original one.
*/
var thing2 = "manuscript"; // should return manu
var thing3 = "prescrip"; // should return prescrip

// in this function, I will be assuming that this is case sensitive
function removeScript(thing2){

    thing2 = thing2.trim();
    var newString = "";

    if (thing2.length < 6) { // base case
        console.log("here");
        console.log("The same string is:  " + thing2);
        return thing2;
    }

    else if (thing2.indexOf("Script") !== -1){
        //get index
        var indexOfScript = thing2.indexOf("Script");
        newString += thing2.substring(0, indexOfScript) + thing2.substring((indexOfScript+6), thing2.length);
        console.log("The new string is:  " + newString);
        return newString;
    }
    else {
    console.log("The same string is:  " + thing2);
    return thing2;
    }
}

removeScript(thing2);

/*----------------------------------------------------------------------------------------------------------------------
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
To determine whether a year is a leap year, follow these steps: 
1 If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
2 If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
3 If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
4 The year is a leap year (it has 366 days).
5 The year is not a leap year (it has 365 days).
*/

function isLeapYear(date){
    var theYear = date.getFullYear();
    var isLeap = false;

    console.log(date);
    console.log(theYear);

    if (theYear%4 !== 0){
        return false;
    }
    if (theYear%400 !== 0){
        console.log(false)
        return false;
    }
    if (theYear%100 === 0 && theYear%400 === 0){
        isLeap = true;
    }
    console.log(isLeap)
    return isLeap;
}

var d = new Date("July 21, 2500 01:15:00");
isLeapYear(d);

/*----------------------------------------------------------------------------------------------------------------------
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
according to wiki, an email address such as John.Smith@example.com is made up of a local-part, an @ symbol, then a 
case-insensitive domain.

The local part
The local-part of the email address may use any of these ASCII characters:
uppercase and lowercase Latin letters A to Z and a to z;
digits 0 to 9;
special characters !#$%&'*+-/=?^_`{|}~;
dot ., provided that it is not the first or last character unless quoted, and provided also that it does not appear 
consecutively unless quoted (e.g. John..Doe@example.com is not allowed but "John..Doe"@example.com is allowed);

(words) (0-1 dot) (words)

the local part may be up to 64 characters long and the domain may have a maximum of 255 characters
*/

function isValidEmail(str){
    //the string can be at most 64 + 1 + 255 characters long
    if (str.length > 320) {
        return false;
    }

    var patt = new RegExp("[0-9a-zA-Z!#\$\.%&'*\+\/=\?\^_`{\|}~-]*[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+@[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+\.[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+");
    console.log("is valid email??:  " + patt.test(str));
    return patt.test(str);
}

isValidEmail("nozuko.kho.go@gmail.com");
isValidEmail("nozuko..com");

/*----------------------------------------------------------------------------------------------------------------------
7.     Remove Character
Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
*/

function removeChar(str, index){
    var newStr = "";

    newStr += str.substring(0, index) + str.substring((index+1), str.length);

    console.log("The new string removeChar:  " + newStr);
    return newStr;
}

removeChar("tyrannosaurus Rex", 6);

/*----------------------------------------------------------------------------------------------------------------------
8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.
*/

// swapping helper function
// function reverz1(arr, i, j){ // i is starting index, j is last index
// 	while(i < j){
// 		var temp = arr[i];
// 		arr[i] = arr[j];
// 		arr[j] = temp;
// 		i++; // advance pointers
// 		j--;
// 	}
// } // end swapping function


function bubbleSort(numArray) {
    var numLength = numArray.length;
    for (var i = 0; i < numLength-1; i++) {
        for(var p = 0; p < (numLength-i-1); p++) {
            if (numArray[p] > numArray[p+1]) {
            //numArray = reverz1(numArray, numArray[p], numArray[p+1]);
            var tmp = numArray[p];  //Temporary variable to hold the current number
            numArray[p] = numArray[p+1]; //Replace current number with adjacent number
            numArray[p+1] = tmp; //Replace adjacent number with current number
            }
        }
    }
    console.log("The sorted array: " + numArray);
    return numArray;
}

bubbleSort([5,4,1,2,9]);

/*----------------------------------------------------------------------------------------------------------------------
9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/

var someNum = 89;

function isEven(someNum){

    if (Math.trunc(someNum/2) * 2 === someNum) { // truncate 1st!
        console.log("true");
    }
    else {
        console.log("false");
    }
}

isEven(someNum);


/*----------------------------------------------------------------------------------------------------------------------
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.
*/

function isPalindrome(someStr){
if (someStr.length===0 || someStr.length===1){
    console.log("is Palindrome true");
    return true;
}

var isPal = false; // boolean

inputArray = someStr.toLowerCase().split('') // array
console.log(inputArray);

var start = 0; // int
var end = someStr.length-1;
while (start < end){
    if (inputArray[start] === inputArray[end]){
        isPal = true;
    }
    else{
        console.log("is Palindrome false");
        return false;
    }
    start++;
    end--;
}//end while

console.log("isPalindrome (final) " + isPal);
return isPal;
}

isPalindrome("racecar");

/*----------------------------------------------------------------------------------------------------------------------
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

function printShape(shape, height, character) {
    switch(shape) {
        case "Square":
            var i;
            for (i = 0; i < height; i++) {
                console.log(character.repeat(height));
            }
            break;
        case "Triangle":
            for (i = 0; i < height; i++) {
                console.log(character.repeat(i));
            }
            break;
        case "Diamond":
            var i, j;
            var countSpaces = height-1;
            countSpaces = height-1;
            for (j=1; j<=height/2; j++) {
                for (i=1; i<countSpaces; i++) {
                    process.stdout.write(" ");
                }
                countSpaces--;
                process.stdout.write(character.repeat(j*2));
                // for(i=1; i<=(2*height-1); i++) {
                //     process.stdout.write(character);
                // }
           
               console.log();
            }
            countSpaces = 1;
            for (j=1; j<=(height-1); j++) {
                for (i=1; i<=countSpaces; i++) {
                    process.stdout.write(" ");
                }
                countSpaces++;
                for (i=1; i<=(2*(height-j)-2); i++) {
                    process.stdout.write(character);    
                }
                console.log();
            }
            break;
        default:
            console.log(shape);
    }
    return shape;
}

printShape("Diamond", 5, "$");

/*----------------------------------------------------------------------------------------------------------------------
12.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]
*/

// swapping helper function
function reverz(arr, i, j){ // i is starting index, j is last index
	while(i < j){
		var temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		i++; // advance pointers
		j--;
	}
} // end swapping function

//reverse(['a', 'b','c','d','e','f','g'])

// rotating method
function rotate(arrayRot, n) {

	if(array == null || arrayRot.length < 2){
	    return arrayRot;
    }

	n = n % arrayRot.length;
	reverz(arrayRot, 0, arrayRot.length - 1); // first reverse entire array
	reverz(arrayRot, arrayRot.length - n, arrayRot.length - 1); // then reverse last half
	reverz(arrayRot, 0, arrayRot.length - n - 1); // then reverse 1st half

    console.log(arrayRot);
	return arrayRot;
} // end rotate method

rotate([1,2,3,4,5], 6); //[2,3,4,5,1]

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

//helper method to check if pairs
function isPaired(openBracket, closeBracket) {
    return  (openBracket === '(' && closeBracket === ')') ||
            (openBracket === '[' && closeBracket === ']') ||
            (openBracket === '{' && closeBracket === '}');
}

function balanced(string) { //note: javascript shift() = java pop()

console.log("input:  " + string);
var charCount = string.length;
var isBalanced = true;
var index = 0;
var nextChar = '';
var myStack = new Array();
    while (isBalanced && (index < charCount)) {
        nextChar = string.charAt(index);
        switch (nextChar) {
            case '(': case '[': case '{':
                myStack.push(nextChar);
                break;
            case ')': case ']': case '}':
                if (myStack.length == 0) {
                    //console.log("false");
                    isBalanced = false;
                }
                else {
                    var openDelimeter = myStack.pop();
                    //console.log(openDelimeter);
                    isBalanced = isPaired(openDelimeter, nextChar);
                }
                break;
            default:
                break;
        } // end switch
        index++;
    } // end while
    if (!myStack.length == 0) {
        //console.log("false");
        isBalanced = false;
    }
    
    console.log("is Balanced??:  " + isBalanced);
    return isBalanced;
    }

balanced("[[()]]");
