//1.      Longest String
//Define function: maxLength(array)
//Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.


 var arr =['nan','nbn','ncn','ndn','nen','nfn','ngn'];
function maxlength (arr){
var maxL = 0
var longx = 0
for(var ray = 0; arr.length; ray++ {
    if(array[ray].length > maxL) {
      maxL = arr[ray].length;
      longx = ray;
	}
   }
return longx;
}




 //  2.      Reverse Array
//  Define function: reverseArray(array)
//  Write a JavaScript function to reverse the elements of a given array. 


var arrA = ['nan','nbn','ncn','ndn','nen'];
var arrz = [];
function reverseArray(array){
	for (var u = array.length-1; u>= 0; u--){
		arrZ.push(arrA[u]);
	}
	console.log(arrZ)
    }




 // 3.     Count Vowels 
// 	Define function: vowelCount(string)
// 	 Write a JavaScript function to count the number of vowels in a given string.




function countVowels(str){
    return (str.match(/[aeiou]/gi) == null) ? 0 : str.match(/[aeiou]/gi).length;        
    }





 // 4.      Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.

// 5.      Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.



var every4 = new Date();
var currentyear = every4.getFullYear;
function isLeapYear(date){
    if (year %4 ===0 ) {
    if (year% 100 ===0 ) {
    if (year % 400 ===0){
        return true;
    } else {
        return false;
    }
}




 // 6.      Email Validation 
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.function validateEmail(email) 


{
    var myemail = /\S+@\S+\.\S+/;
    return myemail.test(email);


}
 // 7.     Remove Character
// 	Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the specified position of a given string and return the new string.


function removeChar(string, index,) {
  return string.substring(0, index)
}


 // 8.       Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.
var ord = [4, 3, 13, 46, 20, 84, 18, 64, 9];
 
function bubbleSort(ord)
{
    var swapped;
    do {
        swapped = false;
        for (var u=0; u < ord.length-1; u++) {
            if (ord[u] > ord[u+1]) {
                var $1e = ord[u];
                ord[u] = ord[u+1];
                ord[u+1] = $1e;
                swapped = true;
            }
        }
    } while (swapped);
}
 
bubbleSort(ord);
console.log(ord);



// 9.    Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.



  var $withOutOp = function(someNum) {
    if(someNum & 1) {
      return false;
    } 
    return true;
  }



// 10.   Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.



function ispalindrome(str){
str.str.toLowerCase();
for(var u= 0 ; < str.length, u ++){
	str=str.replace('','');
	str=str.replace(','.'');
	str=str.replace('.','');
	str=str.replace('/','');
	str=str.replace('_','');
}
var pal= str.split('').reverse().join('');
if(pal==str){
	return true;
}
	else{
	return false
}
return str;




 // 11.   Shapes

function printSquare(d) {
  var $quare;
  for (var u = 0; u < d; u++) {
      var arr = [];
    for (var p = 0; p < d; p ++) {
      arr.push("#")
    }
      $quare = arr.join(' ');
      console.log($quare + '\n'); 
  }
}
function printDiamond(a) {
  var _diamond;
  var arr = [];
  for (var u = 0; u < a; u++) {
      arr.push("$")
      _diamond = arr.join(' ');
      console.log(_diamond + '\n'); 
  }
}
