/*
1.      Longest String
Define function: maxLength(array)
Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.
*/

function maxLength(array) {

    let maxStr = '';
    for (i = 0; i < array.length; i++) {
        if (array[i].length > maxStr.length) {
            maxStr = array[i].length;
        }
    }
    
    return maxStr - 1;

}


/*
2.      Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
*/

function reverseArray(array) {
    let someArr = [];
    for (i = array.length; i > 0; i--) {
        someArr.push(i);
    }

    return someArr;
}

/*
3.     Count Vowels 
	Define function: vowelCount(string)
     Write a JavaScript function to count the number of vowels in a given string.
*/     

function vowelCount(string) {
    let check = 0;
    const vowels = ['a', 'e', 'i', 'o', 'u'];


    for (let char of word.toLowerCase()) {
        if (vowels.includes(char)) {
            check++;
        }
    }

    return check;
}


/*
4.      Remove Script
Define function: removeScript(string)
Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.
*/

function removeScript(string) {
    return string.split("Script").join('');
}

/*
5.      Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
*/

function isLeapYear(date) {
    let d = new Date();
    d = d.getFullYear();
    return ((date % 4 == 0) && (date % 100 !== 0)) || (date % 400 == 0);
} 

/*
6.      Email Validation 
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
*/

function isValidEmail(string) {
    let regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return regex.test(string);
}
 
/*
7.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
*/

function removeChar(string, index) {
    return string.substring(0, index-1) + string.substring(index, string.length);
}

/*
8.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array. You'll need to look this up!
Return the sorted array.
*/

function bubbleSort(numArray) {
    for (i = 0; i < numArray.length; i++) {
        for (j=0; j < numArray.length-1-i; j++) {
            if (numArray[j] > numArray[j+1]) {
                const smallerNum = numArray[j+1];
                numArray[j+1] = numArray[j];
                numArray[j] = smallerNum;
            }
        }
    }

    return numArray;
}

/*
9.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/

function isEven(someNum) {
    while (someNum > 0) {
        someNum -= 2;
    }

    return !someNum;
}
 
/*
10.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.
*/

function isPalindrome(someStr) {
    someStr = someStr.split(' ').join('');
    let reversed = '';

    for (let char of someStr) {
        reversed = char + reversed;
    }

    return reversed === someStr;
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

function printShape(shape, height, character) {
    let step = '';

    switch (shape) {
        case "Square":
            for (i = 0; i < height; i++) {
                for (j = 0; j < height; j++) {
                    step += character;
                }
                step += "\n";
            }
            break;

        case "Triangle":
            for (i = 0; i < height; i++) {
                for (j = 0; j <= i; j++) {
                    step += character;
                }
                step += "\n";
            }
            break;

        case "Diamond":
            for (i = 0; i < height / 2; i++) {
                for (j = 1; j < Math.abs(i - height / 2); j++) {
                    step += " ";
                }
                for (k = 0; k < height - 2 * Math.abs(i - (height / 2)) + 1; k++) {
                    step += character;
                }
                step += "\n";
            }

            for (i = height / 2 + 1; i < height; i++) {
                for (j = 0; j < Math.abs(i - height / 2); j++) {
                    step += " ";
                }
                for (k = 1; k < height - 2 * Math.abs(i - (height / 2)) + 1; k++) {
                    step += character;
                }
                step += "\n";
            }

            break;
    }

    console.log(step);
}


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
    let pos = n % array.length;

    if (array.length < 2) {
        return array;
    }

    // what if the rotation amount is 0?
    if (pos === 0) {
        return array;
    }

    if (pos < 0) {
        return array.slice(pos).concat(array.slice(0, array.length + pos));
    } else {
        return array.slice(pos).concat(array.slice(0, pos));
    }
}

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
 
// use a stack to solve
function balanced(string) {
    let stack = [];
    let openBrackets = {
        '{':'}',
        '[':']',
        '(':')'
    };
    
    let closedBrackets = {
        '}': true,
        ']': true,
        ')': true
    };


    for (i=0; i < string.length; i++) {
        let char = string[i];

        // push open brackets to the top of the stack if they match the chars in openBrackets
        if (openBrackets[char]) {
            stack.push(char);
        // check if char matches an item in closedBrackets
        } else if (closedBrackets[char]) {
            // check if the last element popped off the stack is !== to current char
            // return false if so, since this isn't balanced
            if (openBrackets[stack.pop()] !== char) return false;
        }

    }

    return stack.length === 0;
}
