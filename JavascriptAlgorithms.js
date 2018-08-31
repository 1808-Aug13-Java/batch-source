//Max Length Function and Index 
var a = ["max", "cirey",  "the longest string", "John", "Jameson", "Element"];

function maxLength(array)
{
    let max = 0;
    let index = -1;
    for(i of array)
    {
        if(i.length > max)
        {
            max = i.length;
            index++;
        }
    }
    return index;
}

b = maxLength(a);
console.log(b);

/*  Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
 */

function reverseArray(arr)
{
    count = 0;
    newArray = new Array(arr.length)
    for(i of arr)
    {   
        count++;
        newArray[newArray.length - count] = i;
    }
    return newArray;
}

let c = reverseArray(a);
console.log(c);

 /*
3.     Count Vowels 
	Define function: vowelCount(string)
	 Write a JavaScript function to count the number of vowels in a given string.
 */

 function vowelCount(word)
 {
     count = 0;
     for(i of word)
     {
         if(i === 'a' || i === 'e' || i === 'i' || i === 'o' || i === 'u')
         {
             count++;
         }
     }
     return count;
 }

 /*
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.
 */

function removeScript(word)
{
    let newString = word;
    while(newString.includes("Script"))
    {
        newString = newString.replace("Script", "");
    }
    return newString;
}

let scriptString = "Script.ScriptJava";
console.log("Original String: " + scriptString + " New String: " + removeScript(scriptString));

 /*
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
*/

var testDate = new Date(2100, 1);
function isLeapYear(date)
{
    if(date.getFullYear() % 4 === 0)
    {
        if(date.getFullYear() % 100 === 0)
        {
            if(date.getFullYear() % 400 === 0)
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }
    return false;
}

console.log("isLeapYear = "+isLeapYear(testDate));

/*
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format. 
*/

function isValidEmail(email)
{
    if(!(email.includes("@") || email.includes(".")))
    {
        return false;
    }
    else
    {
        t_email = email.substring(email.indexOf('@'), email.length)
        if(t_email.length < 3)
        {
            return false;
        }
    }
    return true;
}

let validEmail = "cirey@gmail.com";
let inValidEmail = "cireygmail";
let inValidEmail2 = "cireygmail@.";

console.log("Valid Email: " + isValidEmail(validEmail));
console.log("Invalid Email 1: " + isValidEmail(inValidEmail));
console.log("Invalid Email 2: " + isValidEmail(inValidEmail2));

/*
7.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
*/

function removeChar(string, index)
{
    string = string.replace(string.charAt(index), "");
    return string;
}

let charString = "Mustang!";

console.log("Original String: " + charString + "New String with 7th character removed: " + removeChar(charString, 7));

/*
8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.
*/

var arrToSort = [10, 9, 6, 7, 8, 3, 4, 1, 9];

function bubbleSort(numArray)
{
    var index = 0;
    var hasSwapped = false;
    for(i of numArray)
    {
        if(index < numArray.length - 1)
        {
            var currentNumber = numArray[index];
            var nextNumber =  numArray[index + 1];
            if(numArray[index] > numArray[index+1])
            {
                numArray[index] = nextNumber;
                numArray[index + 1] = currentNumber; 
                hasSwapped = true;
            }
        }
        index++;
    }
        if(hasSwapped === true)
        {
            bubbleSort(numArray);
        }
        return numArray;
}

console.log(bubbleSort(arrToSort));

/*
9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/

function isEven(someNum)
{
    if(Number.isInteger(someNum/2))
    {
        return true;
    }
    return false;
}

var evenNumber = 10;
var evenNumber2 = 8;
var oddNumber = 111111111111;
var oddNumber2 = 5;

console.log(evenNumber + " is even: " + isEven(evenNumber));
console.log(evenNumber2 + " is even: " + isEven(evenNumber2));
console.log(oddNumber + " is even: " + isEven(oddNumber));
console.log(oddNumber2 + " is even: " + isEven(oddNumber2));

/*
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.
*/

function isPalindrome(someStr)
{
    let newString = "";
    someStr = someStr.toLocaleLowerCase();
   for(var i = someStr.length - 1; i >= 0; i--)
   {
        newString = newString + someStr[i];
   }
   if(newString === someStr)
   {
        return true;
   }
   else
   {
       return false;
   }
}

var palindrome1 = "racecar";
var palindrome2 = "madamimadam";

console.log("Racecar is a palindrome: " + isPalindrome(palindrome1));
console.log("Madam Im Adam is a palindrome: " + isPalindrome(palindrome2));
console.log("Notapalindrome is a palindrome: " + isPalindrome("Notapalindrome"));


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

  function printShape(shape, size, character)
  {
      let newShape = "";
      let numChars = 0;
      switch (shape){
          case "Square":
          for(var i = 0; i < size*size; i++)
          {
              if(i % size === 0) {newShape += ("\n")}
              newShape += character;
          }
          break;
          case "Triangle":
          for(var i = 0; i < size; i++)
          {
              numChars = 0;
              while(numChars <= i)
              {
                newShape += character;
                numChars ++;
              }
              newShape += "\n";
          }
          break;
          case "Diamond":
          for(var i = 0; i < size; i++)
          {
              var middle = Math.round(size/2); 
              var numSpaces = 0;
              var charNum = size;
              
              //Put spaces
              while(numSpaces < Math.abs(middle - i - 1))
              {
                newShape += " ";
                numSpaces ++;
              }

              charNum -= 2*numSpaces;
              //Start Printing
              while(charNum > 0)
              {
                newShape += character;
                charNum --;
              }
              newShape += "\n";
          }
          break;
          default:
          console.log("Invalid Shape!")
      }

      console.log(newShape);
      
  }

  printShape("Square", 5, "*");
  printShape("Triangle", 4, "#");
  printShape("Diamond", 7, "@");
 
  /*
12.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]
*/

function rotate(array, n)
{
    for(i = 0; i < n; i++)
    {
        array.push(array.shift());
    }
    return array;
}

var testCase = [1,2,3,4,5];
console.log(rotate(testCase, 3));

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
    let openBrackets = [];
    let other = [];
    for(i of string) {
        switch(i) {
            case "(" :
            case "[" :
            case "{" :
                openBrackets.push(i);
                break;
            case ")" :
                if(openBrackets[openBrackets.length - 1] == "(") {
                    openBrackets.pop();
                    break;
                } else {
                    other.push(i);
                    break;
                }
            case "]":
                if(openBrackets[openBrackets.length - 1] == "[") {
                    openBrackets.pop();
                    break;
                } else {
                    other.push(i);
                    break;
                }
            case "}":
                if(openBrackets[openBrackets.length - 1] == "{") {
                    openBrackets.pop();
                    break;
                } else {
                    other.push(i);
                    break;
                }
            default:
                other.push(i);
                break;
        }
    }
    if(openBrackets.length == 0 && other.length == 0)
    {
        return true;
    }
    else{
        return false;
    }
}

console.log(balanced("({[]})"));
console.log(balanced("([)]"));
console.log(balanced("(}"));