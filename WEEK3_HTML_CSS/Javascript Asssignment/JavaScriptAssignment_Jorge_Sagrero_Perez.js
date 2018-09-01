//Jorge Sagrero-Perez
//JavaScript Assignment

//console.log('hello from our javascript file')
/*
window.onload = function() {
    console
}
console.log("5 "+ isNaN(5));
console.log("'5': "+ isNaN('5'));
console.log("false: " + isNaN(false));
console.log("null: "+ isNaN(null));
console.log("undefined: " + isNaN(undefined)); */

/*1 Define function: maxLength(array)
Write a JavaScript to find the longest string from an given array of strings 
and returns the string’s array index. */

array = ["Bananas", "Orange", "Apple", "Mangosbaby"];

function maxLength(array) {
    var length = array[0].length;
    var int = 0;
    var high = 0;

    for( a of array) {
        if(length < a.length){
            length = a.length;
            high = int;
        }
        int +=1;
    }
    return high;
}

let b = maxLength(array)
 console.log(b)
 console.log(array[b])

/*
 2 Reverse Array
 Define function: reverseArray(array)
 Write a JavaScript function to reverse the elements of a given array
 */

 function reverseArray(array) {

    var maxlength = (array.length - 1)
    var back = maxlength
    var holder;
    for(a = 0; a < maxlength; a++) { 
        holder = array[a];
        array[a] = array[back];
        array[back] = holder;
        back--; 
    }
    return array;
 }

 let y = reverseArray(array);
/*

 for(d = 0; d<4; d++)
 {
     console.log(y[d]);
 }

 // the of for loop iterates through the elements of the array y
 for(a of y) {
    console.log(a);
 } */

 /*
 3     Count Vowels 
	Define function: vowelCount(string)
	 Write a JavaScript function to count the number of vowels in a given string.

 */

 function vowelCount(string) {
    var count = 0;
    var max = string.length;
    var a;
    for(x = 0; x < max; x++) {
        a = string.charAt(x);
        
        if((a == 'a')||(a == 'e')||(a == 'i')||(a == 'o')||(a == 'u')||(a == 'A')||(a == 'E')||(a == 'I')||(a == 'O')||(a == 'U')) {
            count +=1;
        }
    }
    return count;
 }
 var count = vowelCount(array[0]);
 console.log("the number of vowels in " + array[0] + " is: " + count);

 /*
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.

 */

 function removeScript(string) {

    var it = "Script";
    var search = string.search(it);
    if(search == -1) {
        return string;
    }
    var max = string.length;
    string = string.replace("Script","");
    return string;
    
 }
 var scripty = "I hate Script and Script things";
 console.log(scripty)
 scripty = removeScript(scripty);
 console.log(scripty)
 // the replace function does not replace all instances of the old token, you have to use a for loop if you want to remove all instances

 /*
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
The year can be evenly divided by 4;
If the year can be evenly divided by 100, it is NOT a leap year, unless;
The year is also evenly divisible by 400. Then it is a leap year.
 */
// list of leap years 1904, 1908, 1912, 1916, 1920, 1924, 1928, 1932, 1936, 1940, 1944, 1948, 1952, 1956, 1960, 1964, 1968, 1972, 1976, 1980, 
//1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020

 function isLeapYear(date) {
     var date= date.getFullYear();
     if((date % 4) == 0) {
         if((date % 100) == 0) {
            if((date % 400) == 0) {
                return true;
            }
            else {
                return false;
            }
         }
         else {
             return true;
         }
     }
     else {
        if((date % 400) == 0) {
            return true;
         }
         else {
             return false;
         }
     }
 }
 /*var leapYear = [1904, 1908, 1912, 1916, 1920, 1924, 1928, 1932, 1936, 1940, 1944, 1948, 1952, 1956, 1960, 1964, 1968, 1972, 1976, 1980, 1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020];
 for(a of leapYear) {
    var isleap = isLeapYear(a);
    console.log(a +" is a leap year? : " +isleap);
   
 }*/


 
 /*
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
 */
//solution using regex
function isValidEmail(string) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

var email = "jsagreroperez@gmail.com"
console.log(isValidEmail(email));

/*
7.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
*/
function removChar(string, index) {
    if(index == 0) {
        string = string.substr(1)
        return string

    }
    else if(index == (string.length-1)) {
        string = string.substr(0,(string.length-1));
        return string;
    }
    string = string.substr(0,index) + string.substr((index+1));
    return string;
}
var randoms = "pass"
var index = 0;
var randoms2 = "pass"
var index2  = 3;
var randoms3 = "pass"
var index3 = 1;

console.log(randoms +" is now " + removChar(randoms,index));
console.log(randoms2 +" is now " + removChar(randoms2,index2));
console.log(randoms3 +" is now " + removChar(randoms3,index3));

/*
8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.

procedure bubbleSort( A : list of sortable items )
    n = length(A)
    repeat
        swapped = false
        for i = 1 to n-1 inclusive do
            // if this pair is out of order 
            if A[i-1] > A[i] then
                // swap them and remember something changed 
                swap( A[i-1], A[i] )
                swapped = true
            end if
        end for
    until not swapped
end procedure */

function swap(num1, num2, numArray) {
    var hold = numArray[num2];
    var hold2 = numArray[num1]
    numArray[num2] = numArray[num1];
    numArray[num1] = hold;
    //console.log("index2: " + num2 + " index1 " + num1);

    //console.log("swapping " + hold2 + " with " + hold);

    return numArray;

}

function bubbleSort(numArray) {

    var sorted = false;
    var length = (numArray.length );
    do{
        sorted = false;
        for( i = 1; i<length; i++){
            if(numArray[i-1] > numArray[i]) {
               // console.log("index i = " + i + " numArray[i] = " + numArray[i] + " numArray[i-1] = " + numArray[i-1]);
                numArray = swap((i-1), i, numArray);
                sorted = true;
            }
        }

    }while(sorted === true);
    return numArray;
}

var numArray = [5,1,9,4,2,0];
console.log("before sort numArray = ");
for(x of numArray) {
console.log(x)
}
console.log("after bubble sort numArray = ");
numArray = bubbleSort(numArray);
for(x of numArray) {
    console.log(x)
    }

    /*
9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.

    */


function isEven(someNum) {
    return ((someNum & 1) ? false: true);
}
    /*
    var hold = (someNum/2)
    var hold2 = hold.toString();
    if(hold2.search("/\./") == -1) {
        return true;
    }
    else {
        return false;
    }
}
*/
console.info(isEven(6));


/*
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.

*/

function isPalindrome(someStr) {
    return  (someStr.replace(/ /g, "", someStr).split("").reverseArray().join(""));
}

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


function printSquare(character,num) {
    var holder = character;
    for(i = 1;i<num;i++) {
        holder = holder + character;

    }
    console.info(holder);
}
function printTriangle(character,num) {
    var holder = character;
    for(i = 0;i<num;i++) {
        holder = holder + character;

    }
    console.info(holder);
}
function printDiamond(character,num,height) {
    var holder = character;
    for(i = 1;i<num;i++) {
        holder = holder + character;

    }
    holder = " ".repeat(((height-num)/2)) + holder + " ".repeat(((height-num)/2))
    console.info(holder);
}


function printShape(shape, height, character) {
     //found out about .repeat(x) to late :(
    switch(shape) {
        case("Square"): {
            for(z=0;z <height;z+=1) {
                //for the for loop here, don't use the same variables as the printSquare function because the variables withing the forloop
                // are considered the global scope of the printSquare function
                printSquare(character,height);
                
            }
            break;
        }
        case("Triangle"): {
            for(c=0;c <height;c++) {
                printTriangle(character,c,height);
            }
            break;
            
        }

        case("Diamond"): {

            for(c=1;c <height;c+=2) {
                printDiamond(character,c, height);
            }
            for(c = height; c >0; c-=2) {
                printDiamond(character,c,height);
            }
            break;
        }
    }
 }

 printShape("Square", 5, "*");
 printShape("Triangle", 5, "%");
 printShape("Diamond", 5, "$");
 


 /*														
12.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]
*/


function rotate(array, n) {
    var dummy = array;
    for( a =0; a < n; a++) {
        dummy.push(arr.shift());
    }
    return array;
}

var arr = [1,2,3,4,5]
console.info(arr) 
console.info(rotate(arr,4))


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

 var veg = "(()](";
 console.info((veg.split("u").length - 1));

 function balanced(string) {

    if(((string.split("(").length - 1) !== ((string.split(")").length - 1)) ||((string.split("[").length - 1) !== ((string.split("]").length - 1))||((string.split("{").length - 1) !== ((string.split("}").length - 1)))))) {
        return false
    }
    else return true;

 }
 console.info(veg + " is balanced? : "+ balanced(veg))
 veg = "()[]{}";
 console.info(veg + " is balanced? : "+ balanced(veg))



