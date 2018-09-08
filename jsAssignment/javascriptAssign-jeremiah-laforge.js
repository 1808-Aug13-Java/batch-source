/* 1.      Longest String
  Define function: maxLength(array)
  Write a JavaScript to find the longest string from an given 
  array of strings and returns the string’s array index.
 */
function callMaxLength() {
    let strArr = ["hey", "", "hello", "konnichiwa", "Bonjour",
        "merhaba", "aloha", "hola", "annyong", "gene parmasen!!!!"];
    return maxLength(strArr);
}
function maxLength(array) {
    let max = 0;
    let longestWrd = "";
    let c = 0;
    let index = 0;

    array.forEach(function (element) {
        if (max < element.length) {
            console.log("current longest word: " + element);
            max = element.length;
            longestWrd = element;
            index = c;
        }
        c++;
    });
    return index;
}

// 2.      Reverse Array
// Define function: reverseArray(array)
// Write a JavaScript function to reverse the elements of a given array.

function callReverseArray() {
    let arr = [1, 2, 3, 4, 5, 6];//for easily changing what is passed
    return reverseArray(arr);
}

function reverseArray(array) {
    return array.reverse();
}

// 3.     Count Vowels 
// 	Define function: vowelCount(string)
// 	 Write a JavaScript function to count the number of vowels in a given string.
/*
 I use a regular expression to grab all vowels (inluding y), and
 then return the length of newly created string.
*/
function callCountVowels() {
    let arr = "Wu Tang clAn apprOves thIIs message.";//for easily changing what is passed
    return countVowels(arr);
}

function countVowels(string) {
    let regexp = /[a|e|i|o|u|y|A|E|I|O|U|Y]/g;
    let nuStr = string.match(regexp);
    console.log(nuStr);
    return nuStr.length;
}

// 4.      Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if 
//a string "Script" is present in a given string. 
//If "Script" is present in the string return the string without 
//"Script" otherwise return the original one.
/*
 I use a regular expression to grab the string "Script" and only
 that string exactly as typed if it appeas anywhere in a string
*/
function callRemoveScript() {
    let arr = "JavaScript function to check if a string Script is present Script in a giScriptven string.";//for easily changing what is passed
    return removeScript(arr);
}

function removeScript(param) {
    let regx = /(?:^|)(Script)(?=|$)/g;
    return param.replace(regx, "");
}

// 5.      Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and 
//returns true if the year is a leap year in the Gregorian calendar.

//known leap years: 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044
//console.log("the year 2000 is a leap year? " + isLeapYr(2000));
function callIsLeapYr() {
    console.log(isLeapYr(new Date('December 17, 2014 03:24:00')));
}
function isLeapYr(param) {

    let yr = param.getFullYear();
    console.log(yr);
    let isIt = (yr % 100 === 0) ? (yr % 400 === 0) : (yr % 4 === 0);
    return isIt;
}

// 6.      Email Validation 
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.
/*I am assuming to return true if valid
  regular expression have a .test function in JavaScript that returns true if the 
  string provided matches the reg expression the function is called on
*/
function validEmail(param) {
    let regx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regx.test(String(param).toLowerCase()); //to lower case as email names are not case sensitive
}

// 7.     Remove Character
// 	Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the 
//specified position of a given string and return the new string.

function removeCharacter(str, num) {
    return str.slice(0, num - 1) + str.slice(num);
}

// 8.       Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.
function callBbblSort() {
    let notSorted = [22, 200, 2, 26, 202, 23, 92, 264, 72, 42];
    let sorted = bbblSort(notSorted);
    console.log("not sorted: " + notSorted);
    console.log("sorted: " + sorted);
}

function bbblSort(arr) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                let temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
    return arr;
}


// 9.    Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.
function isEven(num) {
    if (num % 2 === 0) {
        return true;
    } else {
        return false;
    }
}

// 10.   Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.
function callIsPalindrome() {
    let palindrome = "Able was I ere I saw Elba";
    let notPalin = "not a palindrome";
    console.log(isPalindrome(palindrome));
}

function isPalindrome(param) {
    param = param.toLowerCase();
    let regexp = /[\s]/g;
    let processedStr = param.replace(regexp, "");
    let reverse = processedStr;
    let revArr = reverse.split("");
    revArr = revArr.reverse();
    reverse = revArr.join("");
    if (processedStr === reverse) {
        return true;
    } else {
        return false;
    }
}

// 11.   Shapes
// Define function: printShape(shape, height, character)
// shape is a String and is either "Square", "Triangle", "Diamond".
// height is a Number and is the height of the shape. Assume the number is odd.
// character is a String that represents the contents of the shape.
// Assume this String contains just one character.
// Use a switch statement to determine which shape was passed in.
// Use the console.log function to print the desired shape.
// Example for printShape("Square", 3, "%");
// %%%
// %%%
// %%%
// Example for printShape("Triangle", 3, "$");
// $
// $$
// $$$

// Example for printShape("Diamond", 5, "*");
//   *
//  ***
// *****
//  ***
//   *		
function printShape(shape, height, char) {

}

// 12.   Rotate Left
// Define function: rotate(array, n)
// Given array, rotate left n times and return array
// Examples
// f([1,2,3,4,5], 1) = [2,3,4,5,1]
// f([1,2,3,4,5], 6) = [2,3,4,5,1]
// f([1,2,3,4,5], 3) = [4,5,1,2,3]

function rotateLeft(array, n) {
    let copyArr = array.slice();
    for (i = 0; i < array.length; i++) {
        array[i] = copyArr[(i + n) % array.length];
    }
    return array;
}

// 13.   Balanced Brackets
//  	Define function: balanced(string)
// Create a function which takes a string of brackets and 
//returns true if balanced and false if not balanced
/*
    This function using a stack to check if the paranthesis are matched.
    I broke it up into multiple function calls to keep the logic
    easier to follow. callBalanced function jus serves to make this 
    easier to call from the console. The match() call on the array
    returns an array of only the parantheticals in their original order
    with all other characters removed.
*/
function callBalanced() {
    let balanced = "(a{s[(d)]g}gg)";
    let notBalanced = "(((){}";
    // let boo = balancedBrackets(balanced);
    let boo = balancedBrackets(balanced);
    console.log("Those paranthesis are matched status: " + boo);
}

function balancedBrackets(arr) {
    let regexp = /[\(|\)|\[|\]|\{|\}]/g;
    let matches_array = arr.match(regexp);
    let stack = [];
    let top;
    console.log(matches_array);
    if (matches_array.length % 2 != 0) {
        return false;
    } else {
        matches_array.forEach(element => {
            if (isOpenParan(element)) {
                stack.push(element);
            } else if (isCloseParan(element)) {
                if (stack === undefined) {
                    return false;
                }
                top = stack.pop();
                if (!matches(top, element)) {
                    return false;
                }
            }
        });
    }
    if (stack === undefined || stack.length == 0) {
        return true;
    } else {
        return false;
    }
}
function isOpenParan(param) {
    if (param === '(' || param === '[' || param === '{') {
        return true;
    }
}

function isCloseParan(param) {
    if (param === ')' || param === '}' || param === ']') {
        return true;
    }
}

function matches(tpParan, arrg) {
    if (tpParan === '(' && arrg === ')') {
        return true;
    } else if (tpParan === '[' && arrg === ']') {
        return true;
    } else if (tpParan === '{' && arrg === '}') {
        return true;
    } else {
        return false;
    }
}