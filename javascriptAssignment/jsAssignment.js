
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

// 1.      Longest String
// Define function: maxLength(array)
// Write a JavaScript to find the longest string from an given array of strings and returns the string’s array index.
function length(array)
{
    sum = 0;
    for(i in array)
        sum++;
    return sum;
}
function maxLength(array)
{
    // Testing and learning. 
    // for(i in array)
    // ***i refers to index of the array
    // ***console.log(i) gives 0,1,2,3,etc
    // ***how to get value is array[i]
    // {
    //     console.log(array[i]);
    // }
    let maxLength = length(array[0]);
    let indexOfLongest = 0;
//FIXME problems? :( i goes to 2 and then 4....    
    for(i = 0; i < array.length; i++)
    {
        if (length(array[i]) > maxLength)
        {
            maxLength = length(array[i]);
            indexOfLongest = i;
        }
        console.log("array[]: "+array[i]+
                ", "+indexOfLongest+", "+maxLength);
    }
}

console.log(maxLength(["cat","greyhound","dog","mouse"]));


// 2.      Reverse Array
// Define function: reverseArray(array)
// Write a JavaScript function to reverse the elements of a given array.
function reverseArray(array)
{
    for(i=0; i < array.length / 2 - 1; i++){
        // swap(array[i], array[array.length - 1 - i]);
        c = array[i];
        array[i] = array[array.length - 1 - i];
        array[array.length - 1 - i] = c;
    }
    
} 
arr1 = [1,2,"three",4,"five",6,7];
console.log(arr1);
reverseArray(arr1);
console.log(arr1);


// 3.     Count Vowels 
// 	Define function: vowelCount(string)
// 	 Write a JavaScript function to count the number of vowels
//  in a given string.
function vowelCount(string)
{
    vowelCount = 0;
    vowels = ['a','e','i','o','u'];
    for(i in string)
        for(j in vowels)
            if(vowels[j] === string[i])
            {
                vowelCount++;
                break;
            }
    return vowelCount;
}
console.log("# vowels in 'starry skies': "
            +vowelCount("starry skies"));

// 4.      Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" 
// is present in a given string. If "Script" is present in 
// the string return the string without "Script" otherwise 
// return the original one.
function removeScript(string)
{   //  01234567
    // "aaScript"   [2,8] for substring. can do 2+6
    //JS lets you do "hey"==="hey" :DDDD for string compare
    // __.substring(i,j) includes index i -> j-1
    //JS can append to empty string. str = "" + "a"
    changedCopy = "";
    i = 0;
    for(; i < string.length - 5; i++)
        if(string.substring(i,i+6) === "Script")
            i += 5; //really is 6, but i think does i++  
        else
            changedCopy += string[i]; 
    for(j = i; j < string.length; j++) //copy rest
        changedCopy += string[j];
    return changedCopy;
}
console.log(removeScript("hahaScriptha"));

// 5.      Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter
// and returns true if the year is a leap year in the 
// Gregorian calendar.
 
function isLeapYear(date)
{
// If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
// If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
// If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
// The year is a leap year (it has 366 days).
// The year is not a leap year (it has 365 days).
    if(date % 4 === 0)
        if(date % 100 === 0)
            if(date % 400 === 0)
                return true;
            else
                return false;
        else 
            return true;
    else
        return false;
}
console.log(isLeapYear(1200));


// 6.      Email Validation 
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.
function isValidEmail(string)
{
    indexAt = 0;
    indexLastDot = 0;
    existsAt = 0;
    existsDot = false;
    for(i in string)
    {
        //check @ sign
        if(string[i] === "@")
        {
            indexAt = i;
            existsAt++;
        }
        //check index of . > index of @
        if(string[i] === ".")
        {
            indexLastDot = i;
            existsDot = true;
        }
    }

    if(existsAt !== 1)
        return false;
    if(existsDot === false)
        return false;
    if(indexLastDot > indexAt)  //FIXME...DOES THIS EVEN MAKE SENSE??
        return false;
    //check . isnt at the end 
    if(indexLastDot === string.length - 1)
        return false;
    return true;
}
console.log("is valid email: "+isValidEmail("cindy@gmail.com"));


// 7.     Remove Character
// 	Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the 
// specified position of a given string and return the new 
// string.
function removeChar(string, index)
{
    copy = string.substring(0, index) + 
                string.substring(index + 1);
    return copy;
}
console.log(removeChar("stabbiness", 1));

// 8.       Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array. You'll need to look this up!
// Return the sorted array.
function bubbleSort(numArray)
{
    for(i=0; i < numArray.length - 1; i++) //i controls how
    //many sort rounds there will be
        for(j=0; j < numArray.length - 1 - i; j++)
            if(numArray[j] > numArray[j + 1])
            {
                c = numArray[j];
                numArray[j] = numArray[j + 1];
                numArray[j + 1] = c;
            }   
    return numArray; 
}
console.log(bubbleSort([3,2,2,1,8,4,0,9,2]));


// 9.    Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.
function isEven(someNum)
{   
    if((someNum - parseInt(someNum/2)*2) === 0)
        return true;
    else
        return false;

}
console.log(isEven(45));


// 10.   Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return 
// false.
function isPalindrome(someStr)
{
    //remove spaces
    splitted = someStr.split(" ");
    for(i=0; i < splitted.length/2; i++)
        if(splitted[i] !== splitted[splitted.length - 1 - i])
            return false;
    return true;
}
 console.log(isPalindrome("racecar"));


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
  *						*/
function printShape(shape, height, character)
{
    buffer="";
    switch(shape)
    {
        case "Square":
            for(i=0; i < height; i++)
            {
                for(j=0; j<height; j++)
                    buffer += character;
                if(i < height - 1)
                    buffer += "\n";
            }
            break;
        case "Triangle":
            for(i=0; i < height; i++)
            {
                for(j=0; j <= i; j++)
                    buffer += character;
                buffer += "\n";
            }
            break;
        case "Diamond":
            mid = -1;
            if(height % 2 == 0) //even height
                mid = height / 2;
            else
                mid = (height + 1) / 2;
            i = 0;
            for(; i < mid; i++) //each row
                
            break;
    }

    console.log(buffer);
}
printShape("Triangle", 5, "%");
//  12.   Rotate Left
// Define function: rotate(array, n)
// Given array, rotate left n times and return array
// Examples
// f([1,2,3,4,5], 1) = [2,3,4,5,1]
// f([1,2,3,4,5], 6) = [2,3,4,5,1]
// f([1,2,3,4,5], 3) = [4,5,1,2,3]
function rotate(array, n)
{
    size = array.length;
    displacement = n % size;
    if(displacement === 0)
        return array;
    newArray = "";
    for(i=0; i < size; i++)
    {
        if(i + displacement >= size )
            newArray += array[(i + displacement) - size];
        else
            newArray += array[i + displacement];
    }

    return newArray;
}
console.log(rotate([1,2,3,4,5], 3));


// 13.   Balanced Brackets
//  	Define function: balanced(string)

// A bracket is any one of the following: ( ) { } [ ]
 
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
 
// Create a function which takes a string of brackets and 
// returns true if balanced and false if not balanced

 
function balanced(string)
{
    //*Teaching Example about arrays in JS
    // var t = [];              //how to make an array
    // t.length === 0;
    // t[10000] = 'value';
    // t.length === 10001;
    // you can set the value of any index in the array at any 
    // time, and the array will expand to that size!

    topI = -1;   //-1 means empty. 0+ starts to point
    stack = [];
    for(i in string)
    {
        switch(string[i])
        {
            case "(":
                stack[++topI] = string[i];
                break;
            case "{":
                stack[++topI] = string[i];
                break;
            case "[":
                stack[++topI] = string[i];
                break;
            default:
                switch(string[i])
                {
                    case ")":
                        if(stack[topI] === "(")
                            topI--;
                        break;
                    case "}":
                        if(stack[topI] === "{")
                            topI--;
                        break;
                    case "]":
                        if(stack[topI] === "[")
                            topI--;
                        break;
                    default:
                }

        }
    }

    if(topI !== -1) //not empty
        return false;
    return true;
}
// ()
// ()()
// (())
// ({[]})
// (
// )
// (()i
// ([)]
console.log(balanced("([)]"));




 










