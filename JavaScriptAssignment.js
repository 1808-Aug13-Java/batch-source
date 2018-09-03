// 1.      Longest String
// Define function: maxLength(array)
// Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.
var my_array = ['This is the first string','This is a much longer string, made to be returned']; 

function maxLength(array){
    var length = 0;
    var longestString;
    for(var i = 0; i < array.length; i++){
        if(array[i].length > length){
        length = array[i].length;
        longestString = array[i];
        }
        //console.log(my_array.indexOf(longestString));
    }
    return longestString;
    // console.log(my_array.indexOf(longestString));
}
console.log(maxLength(my_array));


// 2.      Reverse Array
// Define function: reverseArray(array)
// Write a JavaScript function to reverse the elements of a given array.
var my_array2 = ['1','2','3','4','5'];

function reverseArray(array){
   let someArr;

   for (var i =0; i<array.length/2; i++){
       someArr = array[i];
       array[i] = array[array.length-1-i];
       array[array.length - 1 - i] = someArr;
   }
}
reverseArray(my_array2);
//console.log(reverseArray(my_array2));

// 3.     Count Vowels 
// 	Define function: vowelCount(string)
// 	 Write a JavaScript function to count the number of vowels in a given string.
 
function vowelCount(string){
    //string = window.prompt("Enter what you would like to check for vowels.");
    var vowel = string.match(/[aeiou]/gi);
    return vowel === null ? 0 : vowel.length;
    //vowel = window.prompt("Enter a string to check for vowels");
}

console.log(vowelCount('This sentence has 7 vowels'))

// 4.      Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.

function removeScript(string){
    string = window.prompt("Please provide a sentence to check :");
    const fixedString = string.replace(new RegExp("Script"),'');
    return fixedString;
}

//console.log(removeScript('This sentence has Script in the middle but you can not tell'));

// 5.      Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
 function isLeapYear(date){
  year = window.prompt("Please provide a year to check :");
  date = (year % 100 === 0) ? (year % 400 === 0) : (year % 4 === 0);  
  return date;
}

// 6.      Email Validation 
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.
 
function isValidEmail(string){
    //string = window.prompt("Please provide an email to check its format :")
    var validation = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return validation.test(string);
}
console.log(isValidEmail("ahuerta2@csustan.edu"))

// 7.     Remove Character
// 	Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
function removeChar(string, index){
    let testString ="";
    if (index >= 0 && index < string.length){
        testString = testString.concat(string.substring(0, index)).concat(string.substring(index + 1));
        return testString;
    } else{
        return undefined;
    }
}
console.log(removeChar("This string is missing the i from This", 2));

// 8.       Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.

var numArray3 = [50, 10, 1, 41, 1022, 123, 63, 98]; //TestArray

function bubbleSort(numArray){
    var sorted;
    do{
        sorted = false;
        for(var i=0; i < numArray.length - 1; i++){
            if(numArray[i] > numArray[i+1]){ //Organize from smallest to largest
                var x = numArray[i];
                numArray[i] = numArray[i + 1];
                numArray[i+1] = x;
                sorted = true;
            }
        }
    } while (sorted);
}
console.log(bubbleSort([50, 10, 1, 41, 1022, 123, 63, 98]));

// 9.    Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.
function isEven(someNum){
    someNum = window.prompt("Please provide a number to check if it is even: ");
    while(someNum > 1){
        someNum -=  2;
    }
    if(someNum === 1){
        return false;
    }
    return true;
}
 
// 10.   Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.
function isPalindrome(someStr){
    someStr = window.prompt("Please provide a phrase to check if it is a palindrome: ");
    const palinStr = someStr.split("").reverse().join("");
    return someStr === palinStr;

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
function printShape(shape, height, character){
    let someSymbol = 0;
    let someShape = "";

    switch(shape){
        case "Triangle":
        for(var i = 0; i < height; i++){
            someShape = 0;
            while(someShape){
                someSymbol += character;
                someShape ++;
            }
            someSymbol += "\n";
        }
        break;
        case "Diamond":
        for(var i = 0; i < height; i++){
            var center = Math.round(height/2);
            var space = 0;
            var symbolNum = height;

            while(space < Math.abs(center - i - 1)){
                someSymbol += " ";
                space ++;
            }
            symbolNum -= 2 * space;
            while(space > 0){
                someSymbol += character;
                symbolNum --;
            }
            someSymbol += "\n";
        }
        break;
        case "Square":
        for(var i = 0; i < height * height; i++){
            if(i % heigh ===0){
                someSymbol += character;
            }
        }
        break;
        default:
        console.log("Something went wrong");
    }
    console.log(someSymbol);
}

//printShape("Diamond", 5, "#");

// 12.   Rotate Left
// Define function: rotate(array, n)
// Given array, rotate left n times and return array
// Examples
// f([1,2,3,4,5], 1) = [2,3,4,5,1]
// f([1,2,3,4,5], 6) = [2,3,4,5,1]
// f([1,2,3,4,5], 3) = [4,5,1,2,3]
function rotate(array, n){
    for(var i = 0; i < n; i++){
        var a = array.splice(0, 1);
        array.push(a[0]);
    }
    return array;
}
console.log(rotate([1,2,3,4,5], 6));
// 13.   Balanced Brackets
//  	Define function: balanced(string)

// A bracket is any one of the following: (, ), {, }, [, or ]
 
// The following are balanced brackets:
// ()
// ()()
// (())
// ({[]})
 
// The following are NOT balanced brackets
// (
// )
// (()i
// ([)]
 
// Create a function which takes a string of brackets and returns true if balanced and false if not balanced
 
function balanced(string){
    const symbols = {
        "{" : "}",
        "(" : ")",
        "[" : "]",
        "}" : "{",
        ")" : "(",
        "]" : "["
    }
    symbolArr= string.split("");
    let checker = symbolArr.length - 1;
    for(i = 0; i < symbolArr.length; i++){
        if(symbolArr[i] == symbols[symbolArr[checker]]){
        --checker;
    }else{
        return false;
    }
}
    return true;
}
console.log(balanced("{}{}{}{}"));
console.log(balanced("{[]}"));
