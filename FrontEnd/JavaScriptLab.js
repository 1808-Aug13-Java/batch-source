//1
function maxLength(arr){
    let longest = 0;
    let indexOfLongest = 0;
    for(elem in arr){ //just the element ==> elem of arr
        if (arr[elem].length > longest){
            longest = arr[elem].length;
            indexOfLongest = elem;
        }
    }
    return indexOfLongest;
}

console.log(maxLength(["banny","manny","this one should be the one that does it","heh",'boop']))
//2
function reverseArray(arr){
    let newArr = [];
    for (let i = arr.length-1; i >= 0; i--){
        newArr[arr.length-i-1] = arr[i];
    }
    return newArr;
}

let theArray = [1,2,3,4,5,6,7,8,9];
console.log(reverseArray(theArray));

//3
function vowelCount(string){
    let count = 0;
    for (letter of string)
        switch(letter){
            case"a":case"e":case"i":case"o":case"u":case"A":case"E":case"I":case"O":case"U":
            count++;
        }
    return count;
}

console.log(vowelCount("Hello World!"));

//4
function removeScript(string){
    return string.replace(/Script/gi,"");
}

console.log(removeScript("Checking to see if this ScriptScriptScriptScriptScriptScript works..."))

//5
function isLeapYear(year){
    return year.getFullYear() % 400 === 0 && year.getFullYear() % 100 != 0 || year.getFullYear() % 4 === 0;
}

console.log(isLeapYear(new Date(2020,2)))

//6
function isValidEmail(email){
return -1 < email.search(/[\w|\d]*@[\w]*\.[\w]*/g);
}

console.log(isValidEmail("11ccrandall@gmail.com"));

//7
function removeCharacter(string, index){
    return string.substring(0,index) + string.substring(index+1);
}

console.log(removeCharacter("Remove that character from a man.", 6));

//8
function bubbleSort(arr){
    for (gointhroughthelistagain of arr)
        for (let it = 0; it < arr.length; it++)
            if (arr[it] > arr[it+1]){
                tmp = arr[it];
                arr[it] = arr[it+1];
                arr[it+1] = tmp;
            }
    return arr;
}

console.log(bubbleSort([2,30,3,1,6,8,5,5,7,3]));

//9
function isEven(num){
    return Number.isInteger(num/2)
}

console.log("isEven(498734) => " + isEven(498734));

//10
function isPalindrome(string){
    for (let int = 0; int <= string.length/2; int++)
        if(string[int].toLowerCase() != string[string.length-int-1].toLowerCase())
            return false;
    return true;
}

console.log(isPalindrome("RacEcar"));

//11
function printShape(shape, symbol, size){
    let out = "";
    switch(shape.toUpperCase()){
        case"SQUARE":
            for(let row = 0; row < size; row++){
                for(let col = 0; col < size; col++)
                    out += symbol;
                out += "\n";
            }
            break;
        case"TRIANGLE":
            for(let row = 0; row < size; row++){
                for(let col = -1; col < row; col++)
                    out += symbol;
                out += "\n";
            }
            break;
        case"DIAMOND":
            for(let row = 0; row < size/2; row++){
                out += ' '.repeat(size/2 - row);
                out += symbol.repeat(row*2 + 1) + "\n";
            }
            for(let row = size/2 - 1; row > 0; row--){
                out += ' '.repeat(size/2 - row);
                out += symbol.repeat(row*2) + "\n";
            }
            break;
        default:
            console.log("Wrong type of shape.");
    }
    console.log(out);
}

printShape("square","@",3);
printShape("triangle","#",5);
printShape("diamond","*",21);

//12
function rotate(arr, n){
    for(let nnnnnn = 0; nnnnnn < n; nnnnnn++)
        arr.push(arr.shift());
    return arr;
}

console.log(rotate([1,2,3,4,5,6,7,8],3))

//13
function balanced(){  let openBrackets = [];
    let nothingImportant = [];
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
                    nothingImportant.push(i);
                    break;
                }
            case "]":
                if(openBrackets[openBrackets.length - 1] == "[") {
                    openBrackets.pop();
                    break;
                } else {
                    nothingImportant.push(i);
                    break;
                }
            case "}":
                if(openBrackets[openBrackets.length - 1] == "{") {
                    openBrackets.pop();
                    break;
                } else {
                    nothingImportant.push(i);
                    break;
                }
            default:
                nothingImportant.push(i);
                break;
        }
    }
    return (openBrackets.length == 0) && (nothingImportant.length == 0);
}

console.log("(alkwe)eeeeeeeeeeeeeeee)");
/*1.      Longest String
Define function: maxLength(array)
Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.
 
2.      Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
 
3.     Count Vowels 
	Define function: vowelCount(string)
	 Write a JavaScript function to count the number of vowels in a given string.
 
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.
 
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
 
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
 
7.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.

8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.

9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
 
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false
11.   Shapes
Define function: printShape(shape, height, characteNaN;".
height is a Number and is the height of the shape.( Assuarr)
character is a String that represents the arrntentsarrf (the shape.)
Assume this String contains just one charaarrnt to determine which shape was passed 
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
12.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]

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