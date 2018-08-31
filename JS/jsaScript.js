// 1.      Longest String
// Define function: maxLength(array)
// Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.

var arr = ["a","ab","abcd","abc","ab","a"];

function maxArray(array){
    max = 0;
    position = 0;
    for (var i=0; i<array.length; i++){
        if(max < array[i].length){
            max = array[i].length;
            position = i;
        }
    }
    return position;    
}
 console.log(maxArray(arr));

//  2.      Reverse Array
//  Define function: reverseArray(array)
//  Write a JavaScript function to reverse the elements of a given array. 

var arrNorm = ["a","b","c","d","e"];
var arrRev = [];
function reverseArray(array){
    for (var i=(array.length-1); i>=0; i--){
        arrRev.push(arrNorm[i]);
    }
    console.log(arrRev);
}
console.log(reverseArray(arrNorm));

// 3.     Count Vowels 
// 	Define function: vowelCount(string)
// 	 Write a JavaScript function to count the number of vowels in a given string.

var vowelString = "The quick brown fox jumps over a lazy dog.";

function vowelCount(string){
    var vowelConvert = string.match(/[aeiou]/g);
    console.log(vowelConvert.length);
}
console.log(vowelCount(vowelString));

// 4.      Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.

var stringToRemove = "The quick brScriptown fox juScriptmps over a lazy dog.";

function removeScript(string){
    var reducedString = string.replace(/Script/g,"");
    console.log(reducedString);
}
console.log(removeScript(stringToRemove));

// 5.      Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.

var leapYearDate = new Date();
var yearcheck = leapYearDate.getFullYear;
function isLeapYear(date){
    if (date%4 == 0 && date%100 !==0 || date%400==0){
        return true;
    } else {
        return false;
    }
}
console.log(isLeapYear(yearcheck));

// 6.      Email Validation 
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.

var testEmail = "banana@gmail.com";

function isValidEmail(email) {
    var reg = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    return reg.test(email);
}
console.log(isValidEmail(testEmail));

// 7.     Remove Character
// 	Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the specified position of a given string and return the new string.

var stringCharRemove = "abcdefghij" ;
var afterCharRemove = "";
function removeChar(string, index){
    for (var i=0; i<string.length; i++){
        if (i != index){
            afterCharRemove += string.charAt(i);
        }
    }
    console.log(afterCharRemove);
}
console.log(removeChar(stringCharRemove,5));

// 8.       Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.

var nArray = [3,2,6,4,1];
function bubbleSort(numArray){
    for (var i=0; i<numArray.length; i++){
        for (var j=0; j<numArray.length; j++){
            if (numArray[j]>numArray[j+1]){
                var hold = numArray[j];
                numArray[j] = numArray[j+1];
                numArray[j+1] = hold;
            }
        }
    }
    console.log("The result after the bubble sort: " + numArray);
}
console.log(bubbleSort(nArray));

// 9.    Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.

var oddNum = 351;
function isEven(someNum){
    if((someNum&1) === 0){
        return true;
    } else {
        return false;
    }
}
console.log("The number is even: " + isEven(oddNum));

// 10.   Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.

var word = "bananab";
var palindromeCheck = "";

function isPalindrome(someStr){
    for (var i=someStr.length-1; i>=0; i--){
        palindromeCheck += someStr.charAt(i);
    }
    if (word == palindromeCheck){
        return true;
    } else {
        return false;
    }
}
console.log("The word is a palindrome: " + isPalindrome(word));

// 11.   Shapes
// Define function: printShape(shape, height, character)

function printShape(shape, height, character){
    switch(shape){
        case "Square":
        for (var i=0; i<height; i++){
            var shapeMaker = "";
            for(var j=0; j<height;j ++){
                shapeMaker += character;
            }
        console.log(shapeMaker);
        }
        break;
        case "Triangle":
        for (var i=0; i<height; i++){
            var shapeMaker = "";
            for (var j=0; j<=i; j++){
                shapeMaker += character;
            }
            console.log(shapeMaker);
        }
        break;
        case "Diamond":
        for (var i=1; i<=(height-1)/2; i++){
            var shapeMaker = "";
            for (var j=1; j<=((height-(i*2-1))/2); j++){
                shapeMaker += " ";
            }
            for (var k=0; k<(2*i-1);k++){
                shapeMaker += character;
            }
            console.log(shapeMaker);
            shapeMaker = "";
        }
            for(var l =0; l<height; l++){
                shapeMaker += character;
            }
            console.log(shapeMaker);
            shapeMaker = "";

            for (var m = ((height+1)/2); m<height; m++){
                for (var n=-1; n<((height-(((height+1)-m)*2-1))/2); n++){
                    shapeMaker += " ";
                }
                for (var p=1; p<(2*((height+1)-m)-2); p++){
                    shapeMaker += character;
                }
                console.log(shapeMaker);
                shapeMaker = "";
            }
    }
}

console.log(printShape("Diamond", 9, "@"));

// 12.   Rotate Left
// Define function: rotate(array, n)
// Given array, rotate left n times and return array

var numsArray = [1,2,3,4,5];
var firstPosition = 0;
var positionHold = 0;

function rotate(array, n){
    for(var i=0; i<n; i++){
        firstPosition = array[0];
        for (var j=0; j<array.length-1; j++){
            array[j] = array[j+1]
        }
        array[array.length-1] = firstPosition;
    }
    console.log(array);
}

console.log(rotate(numsArray,3));

// 13.   Balanced Brackets
//  	Define function: balanced(string)

var balanceString = "({[]}())";
var openArray = [];
var closeArray = [];
function balanced(string){
    //assuming no other characters other than brackets it must be string length even, if not auto false
    // if ((string.length)%2 == 1){
    //     return false;
    // } else {
    for (var i=0; i< string.length; i++){
        if (string.charAt(i) === "(" || string.charAt(i) === "{" || string.charAt(i) === "["){
            openArray.push(string.charAt(i));
            console.log(openArray);
        } else if(string.charAt(i) === ")" ){
            if (openArray[openArray.length-1] === "("){
                openArray.pop();
                console.log(openArray);
            } else {
                return false;
            }
        } else if(string.charAt(i) === "}"){
            if (openArray[openArray.length-1] === "{"){
                openArray.pop();
                console.log(openArray);
            } else {
                return false;

            }
        } else if(string.charAt(i) === "]" && string.charAt(i-1 == "[")){
            if (openArray[openArray.length-1] === "["){
                openArray.pop();
                console.log(openArray);
            } else {
                return false;
            }
            console.log(openArray);
        }
            
            //  else if (string.charAt(i) === ")" || string.charAt(i) === "}" || string.charAt(i) === "]"){
            //     openArray.push(string.charAt(i));
            // }
        // }
    }
    if (openArray.length === 0){
        return true;
    } else {
        return false;
    }
    // console.log(openArray);

}

console.log(balanced(balanceString));
