// Define function: maxLength(array)
// Find the longest string from an given array of strings and returns the string’s array index.
function maxLength(array){
    let index;
    let length = 0;
    for (i in array){
        if (length < array[i].length){
            length = array[i].length;
            index = i;
        }
    }
    return index;
}

// Define function: reverseArray(array)
// Write a JavaScript function to reverse the elements of a given array.
function reverseArray(array){
    let temp = array.slice();

    for (i = 0; i < array.length; i++){
        array[i] = temp[temp.length - i - 1];
        console.log(temp[temp.length - i - 1]);
    }
    return array;
}

// Define function: vowelCount(string)
// Write a JavaScript function to count the number of vowels in a given string.
function vowelCount(string){
    // regex expression
    var regex = /[aeiou]/i;
    let count = 0;
    for(i = 0; i < string.length; i++){
        if(regex.test(string.charAt(i))){
            count++;
        }
    }
    return count;
}

// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" is present in a given string. If "Script" is present in the string return the string without "Script" otherwise return the original one.
function removeScript(string){
    var Script = "Script";
    let temp = string.slice();
    let index = temp.indexOf(Script);
    let ret = "";
    if (index == -1){
        return string;
    }
    while(index != -1){
        ret += temp.substring(0, index) + temp.substring(index + Script.length);
        temp = ret.slice();
        index = temp.indexOf(Script);
    }
    return ret;
}

// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
function isLeapYear(date) {
    let year = date.getFullYear();
    if (year%4 == 0){
        if (year%100 == 0){
            if (year%400 == 0){
                return true;
            }
            return false;
        }
        return true;
    }
    return false;
}

// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.
// TODO
function isValidEmail(string) {
    var regex = /?=/;
}

// Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
function removeChar(string, index){
    let temp = string.substring(0, index);
    temp += string.substring(index+1);
    return temp;
}

// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.
function bubbleSort(numArray){
    let left;
    let temp;
    for(i = 0; i < numArray.length - 1; i++){
        for(j = 0; j < numArray.length - i - 1; j++){
            left = numArray[j];
            if (left > numArray[j+1]){
                temp = numArray[j+1];
                numArray[j+1] = left;
                numArray[j] = temp;
            }
        }
        
    }
    return numArray;
}

// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.
function isEven(someNum){
    let even = true;
    for(i = 0; i < someNum; i++){
        even = !even;
    }
    return even;
}

// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.
function isPalindrome(someStr){
    let strArr = someStr.split("")
    let rev = strArr.reverse()
    rev = rev.join("");
    if (rev == someStr){
        return true;
    }
    return false;
}

// Define function: printShape(shape, height, character)
// shape is a String and is either "Square", "Triangle", "Diamond".
// height is a Number and is the height of the shape. Assume the number is odd.
// character is a String that represents the contents of the shape.
// Assume this String contains just one character.
// Use a switch statement to determine which shape was passed in.
// Use the console.log function to print the desired shape.
String.prototype.replaceAt=function(index, replacement) {
    return this.substring(0, index) + replacement+ this.slice(index + 1);
}
function printShape(shape, height, character){
    let line = character;
    switch(shape){
        case "Square":
            for(i = 0; i < height; i++){
                line += character;
            }
            for(j = 0; j < height; j++){
                line += " ";
                console.log(line);
            }
            break;
        case "Triangle":
            for(i = 0; i < height; i++){
                console.log(line);
                line += character;
            }
            break;
        case "Diamond":
            line = " ";
            let mid = (height - 1)/2;
            for(i = 1; i < height; i++){
                if (i == mid){
                    line += character
                } else {
                    line += " ";
                }
            }
            console.log(line);
            for(j = 1; j < mid+1; j++){
                line = line.replaceAt(mid-j, character);
                line = line.replaceAt(mid+j, character);
                console.log(line);
            }

            for(k = 0; k < mid; k++){
                line = line.replaceAt(height-k-1, " ");
                line = line.replaceAt(k, " ");
                console.log(line);
            }
            break;
        default:
            break;
    }
}

// Define function: rotate(array, n)
// Given array, rotate left n times and return array
function rotate(array, n){
    let rot;
    for (i = 0; i < n; i++){
        rot = array.shift();
        array.push(rot);
    }
    return array;
}

// Define function: balanced(string)
// Balanced Brackets
function balanced(string){
    if ((string.length % 2) == 1){
        return false;
    }
    var stringArr = new Array;
    for(i = 0; i < string.length; i++){
        switch(string[i]){
            case "(":
            case "[":
            case "{":
                stringArr.push(string[i]);
                break;
            case ")":
                if (stringArr.pop() != "("){
                    return false;
                }
                break;
            case "]":
                if (stringArr.pop() != "["){
                    return false;
                }
                break;
            case "}":
                if (stringArr.pop() != "{"){
                    return false;
                }
                break;
            default:
                return false;
        }
    }
    return true;
}