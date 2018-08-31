//1.      Longest String
//Define function: maxLength(array)
//Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.

function maxLength(array) {
  var maxLength = 0;
  var maxLengthIdx = 0;
  for(i in array) {
    if(array[i].length > maxLength) {
      maxLength = array[i].length;
      maxLengthIdx = i;
    } 
  }
  return maxLengthIdx;
}

//2.      Reverse Array
//Define function: reverseArray(array)
//Write a JavaScript function to reverse the elements of a given array.
var reverseArray = function(array) {
  var heyo = [];
  array.forEach(function(value, i) {
    heyo.unshift(value);
  });
  return heyo;
}

//3.     Count Vowels 
//    Define function: vowelCount(string)
//     Write a JavaScript function to count the number of vowels in a given string.
var vowelCount = function(string) {
  var re = /[aeiou]/;
  cnt=0;
  for(c of string) {
    if(re.test(c.toLowerCase())) 
      cnt++;
  } 
  return cnt;
}

//4.      Remove Script
//Define function: removeScript(string)
//Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.
var removeScript = function(string) {
  var re = /Script/g;
  if(re.test(string)) {
    string = string.replace(re, "");
  } 
  return string;
}



//5.      Find Leap Year
//Define function: isLeapYear(date)
//Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
/*
To determine whether a year is a leap year, follow these steps:
1 If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
2 If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
3 If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
4 The year is a leap year (it has 366 days).
5 The year is not a leap year (it has 365 days).
*/
var isLeapYear = function(date) {
 var year = date.getFullYear(); 
  if((year % 4) === 0) {
    if((year % 100) === 0) {
      if((year % 400) === 0) {
        return true;
      } 
    }
    else {
      return true;
    }
  }
  else {
    return false;
  }
}
//6.      Email Validation 
//Define function: isValidEmail(string)
//Create a function that checks for a valid email format.

var isValidEmail = function(string) {
  // from http://emailregex.com/
   var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/ 
  //var re = /^\w*@.*\w/ 
  console.log(re.test(string));
}

//7.     Remove Character
//    Define function: removeChar(string, index)
//Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
var removeChar = function(string, index) {
  var re = new RegExp(string.charAt(index));
  string = string.replace(re, "");
  return string;
}
//8.       Bubble Sort
//Define function: bubbleSort(numArray)
//Use the bubble sort algorithm to sort the array. You'll need to look this up!
//Return the sorted array.
var bubbleSort = function(numArray) {
  function swap(arr, x, y) {
    var temp = arr[x];
    arr[x] = arr[y];
    arr[y] = temp; 
    return arr;
  }
  for(var i=0; i<numArray.length; i++) {
    for(var j=0; j<numArray.length; j++) {
      if(numArray[j] > numArray[j+1]) {
        numArray = swap(numArray, j, j+1);
      }
    }
  }
  return numArray;
}

console.log(bubbleSort([5,4,3,2,1]));

//9.    Even Number
//Define function: isEven(someNum)
//Return true if even, false if odd.
//Do not use % operator.
  var isEven = function(someNum) {
    if(someNum & 1) {
      return false;
    } 
    return true;
  }
  //console.log(isEven(124));

//10.   Palindrome
//Define function: isPalindrome(someStr)
//Return true if someStr is a palindrome, otherwise return false.
var isPalindrome = function(someStr, nospaces=false) {
  if(nospaces) {
    var re = /\s/g;
    someStr = someStr.replace(re, "");
  }
  var a=0; 
  var z=someStr.length-1;
  while(z > a) {
    if(someStr[a++] !== someStr[z--]) return false; 
  }
  return true;
}

//11.   Shapes
//Define function: printShape(shape, height, character)
//shape is a String and is either "Square", "Triangle", "Diamond".
//height is a Number and is the height of the shape. Assume the number is odd.
//character is a String that represents the contents of the shape.
//Assume this String contains just one character.
//Use a switch statement to determine which shape was passed in.
//Use the console.log function to print the desired shape.
//Example for printShape("Square", 3, "%");
var printShape = function(shape, height, character) {
  
  var printSquare = function(){
    var printdatthang = "";
    for(var i=0; i<height; i++) {
      for(var j=0; j<height; j++) {
        printdatthang += character;
      }
      printdatthang += '\n';
    }
    console.log(printdatthang);
    return printdatthang;
  }
  
  var printTriangle = function() {
    var printdatthang = "";
    line = "";
    for(var i=0; i<height; i++) {
      line += character;
      printdatthang += line + '\n';
    } 
    console.log(printdatthang.replace(/\n$/, ""));
  }
  
  var printDiamond = function() {
    function getPrintIdx(arr) {
      arr = arr.sort(); 
      var first = arr[0];
      var last = arr[arr.length-1];
      var new1 = first-1;
      var new2 = last+1; 
      arr.push(new1);
      arr.push(new2);
      arr = arr.sort();
      console.log(arr);
      return arr;
    }
    var printdatthang = "";
    var mid = parseInt(height/2);
    var printIdx = [mid];
    var printArr = [];
    for(var i=0; i<=parseInt(height/2); i++) {
      for(var j=0; j<height; j++) {
        if(printIdx.includes(j))
          printArr.push(character); 
        else {
          printArr.push(" ");
        }
      }
      printArr.push('\n');
      printIdx = getPrintIdx(printIdx);
    }
    printIdx = printIdx.slice(1,printIdx.length-1);
    for(var i=0; i<parseInt(height/2); i++) {
      for(var j=0; j<height; j++) {
        if(printIdx.slice(i+1, printIdx.length-(i+1)).includes(j)) {
          printArr.push(character);
        }
        else {
          printArr.push(" ");
        }
      }
      printArr.push('\n');
    }
    for(var i=0; i<printArr.length; i++) {
      printdatthang += printArr[i];
    }
    console.log(printdatthang);
  }
  switch (shape.charAt(0).toUpperCase()) {
    case 'S': printSquare(height, character); break;
    case 'T': printTriangle(shape); break;
    case 'D': printDiamond(shape); break;
  }
}
printShape("Diamond", 3, '*');
//%%%
//%%%
//%%%
//Example for printShape("Triangle", 3, "$");
//$
//$$
//$$$
//Example for printShape("Diamond", 5, "*");
//  *
// ***
//*****
// ***
//  *                                                                
//12.   Rotate Left
//Define function: rotate(array, n)
//Given array, rotate left n times and return array
//Examples
//f([1,2,3,4,5], 1) = [2,3,4,5,1]
//f([1,2,3,4,5], 6) = [2,3,4,5,1]
//f([1,2,3,4,5], 3) = [4,5,1,2,3]
var rotate = function(array, n) {
  for(var i=0; i<n; i++) {
    var e = array.splice(0,1);
    array.push(e[0]);  
  }
  return array;
}
console.log(rotate([1,2,3,4,5], 3));

//13.   Balanced Brackets
//     Define function: balanced(string)
//
//A bracket is any one of the following: (, ), {, }, [, or ]
// 
//The following are balanced brackets:
//()
//()()
//(())
//({[]})
// 
//The following are NOT balanced brackets
//(
//)
//(()i
//([)]
// 
//Create a function which takes a string of brackets and returns true if balanced and false if not balanced
var balanced = function(string) {
  var pop = function(last=true) {
    var c = string.charAt(0);
    if(last) {
      c = string.charAt(string.length-1);
      string = string.slice(0, string.length-1); 
    }
    else {
      string = string.slice(1); 
    }
    return c; 
  }
  var map = { '(': ')',
              '[': ']',
              '{': '}'}
  var token = string.charAt(0);
  var last = string.charAt(string.length-1);
  while(token = pop(false)) { //pop removes first char from string and returns if false; modifies original string
    var token2 = pop(); //else removes last
    if(token2 !== map[token]) //check if first and last token are the same
      return false;
  }
  return true;
}
