
//  < !--1.      Longest String Define function: maxLength(array)
// Write a JavaScript to find the longest string from an given array
// of strings and returns the string’s array index. -- >


function maxLength(array) {
    var longest=0;
    var index;
    for (var i = 0; i < array.length; i++) {
        if (array[i].length > longest) {
            longest = array[i].length;
            index=i;
        }
    } 
    // console.log(index)
    return index;
    
} 

// Reverse Array
// Define function: reverseArray(array)
// Write a JavaScript function to reverse the elements of a given array.

function reverseArray(array){
    var rev=[];
    
    for (var i=array.length-1;i>=0;i--){
        // rev=rev+array.charAt(i);
        rev.push(array[i]);
    }

    return rev;
}

// 3.     Count Vowels
// Define function: vowelCount(string)
// Write a JavaScript function to count the number of vowels in a given string.

function vowelCount(string){
    var num=0;
    var vowels=["a","e","i","o","u"];
    for ( a in vowels){
        if(string.toLowerCase().includes(vowels[a])){
            num++;
        }
    }
    return num;
}

//     Remove Script
// Define function: removeScript(string)
// Write a JavaScript function to check if a string "Script" is present in a given string.
//If "Script" is present in the string return the string without "Script" otherwise return the original one.

function removeScript(string){
     var regex=/script/gi;
     var gone=string.replace(regex,'');
    return gone;
}

//  Find Leap Year
// Define function: isLeapYear(date)
// Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
function isLeapYear(date){
    var leap=date.getFullYear();
    if((leap %4 ==0 && leap%100 !=0)|| leap%400==0){
        return true;
    } 
    else{
        return false;
    }
}

//      Email Validation
// Define function: isValidEmail(string)
// Create a function that checks for a valid email format.
function isValidEmail(string){
    // var regex = /.@.$/
    var may="*@*.*"

    if(string.localeCompare(may)){
        return true;
    }
    else{
        return false;
    }
}

// Remove Character
// Define function: removeChar(string, index)
// Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
function removeChar(string,index){
    var bye=string.charAt(index);
    var remove=string.replace(bye,""); 
    return remove;
}

// Bubble Sort
// Define function: bubbleSort(numArray)
// Use the bubble sort algorithm to sort the array.You'll need to look this up!
// Return the sorted array.

function bubbleSort(numArray){
    var swap=true;
    var change=numArray;

    while (swap){
        swap=false;
        for(var i=0;i<numArray.length-1;i++){ 
            
            if(change[i] > change[i+1] ){
                var swtch=change[i+1];
                change[i+1]=change[i];
                // console.log(change);
                change[i]=swtch;
                // console.log(change);
                swap=true;

            }
        }
    }
    return change;
}

// Even Number
// Define function: isEven(someNum)
// Return true if even, false if odd.
// Do not use % operator.
function isEven(someNum){
    var sum=someNum
   
    if(sum==0){
        return false;
    }
    while(sum>2){
        sum-=2;
    }
    if(sum==2 || sum==0){
        return true;
    }
    else{
        return false;
    }
}


// Palindrome
// Define function: isPalindrome(someStr)
// Return true if someStr is a palindrome, otherwise return false.
function isPalindrome(someStr){
   
    var revr=reverseString(someStr);
    if(revr===someStr){
        return true;
    }
    else{
        return false;
    }


}
//helper function for palindrome
function reverseString(array) {
    var rev = "";

    for (var i = array.length - 1; i >= 0; i--) {
        rev=rev+array.charAt(i);
        // rev.push(array[i]);
    }

    return rev;
}

//   Shapes(use a for loop)
//     Define function: printShape(shape, height, character)
// shape is a String and is either "Square", "Triangle", "Diamond".
// height is a Number and is the height of the shape.Assume the number is odd.
// character is a String that represents the contents of the shape.
// Assume this String contains just one character.


function printShape(shape, height, character){
    var rep="";
    switch(shape){
        case "Square":{
            for(var a=1;a<=height;a++){
                rep+=character.repeat(height)+"\n"

            }
            console.log(rep);
            break;
        }
        case "Triangle":{
            for (var a = 1; a <= height; a++) {
                rep += character.repeat(a) + "\n"

            }
            console.log(rep);
            break;
        }
        case"Diamond":{
            var times=1;
            var pass=true;
            for (var a = 1; a <= height; a++) {
                if(times<=height && pass){
                    rep += character.repeat(times) + "\n";
                    if(times==height){
                        pass=false;
                        times-=2;
                    }
                    else{
                        times+=2
                    }
                    
                }
                else{
                    rep += character.repeat(times) + "\n";
                    times-=2;
                }
            }
            console.log(rep);
            break;
        }


    }
}


// Rotate Left
// Define function: rotate(array, n)
// Given array, rotate left n times and return array
// Examples
// f([1, 2, 3, 4, 5], 1) = [2, 3, 4, 5, 1]
// f([1, 2, 3, 4, 5], 6) = [2, 3, 4, 5, 1]
// f([1, 2, 3, 4, 5], 3) = [4, 5, 1, 2, 3]
function rotate(array,n){

   var mover=array;
   var pop;
   for (var i=0;i<n;i++){
       pop=mover.shift();
       mover.push(pop);
   }
   return mover;
}

// Balanced Brackets
// A bracket is any one of the following: (, ), {, }, [, or]

function balanced(string){
  
 var open=['(','{','['];
//  var close=[')','}',']'];
 var flag=false;
 var index=0;
 var index2=0;
 var index3=0;
 var current=[];
    var current2 = [];
    var current3 = [];
 
 var first=true;

 for (a in open){
     if (string.charAt(0) ==open[a]){
        first=false;
     }
     
 }
 if(first){
     return false;
 }




 for(var i=0;i<string.length;i++){
    switch(string.charAt(i)){
        case '(':{
                current.push('(')
                index++;
            break;
        }
        case '{': {
            current2.push('{')
            index2++;
            break;
        }
        case '[': {
            current3.push('[')
            index3++;
            break;
        }
        case ')': {
          
            if(index2%2 != 0 || index3%2 !=0){
                return false;
            }
                current.push(')');
            index++;
                
            break;
        }
        case '}': {
           
            if (index % 2 != 0 || index3%2 !=0) {
                return false;
            }
            index2++;
            current2.push('}');
            break;
        }
        case ']': {
            
            if (index%2!= 0 || index2%2!=0) {
                return false;
            }
            index3++;
            current3.push(']');
            break;
        }
        default:
            return false;


    }//switch
 }//for

 if(current.length%2 !=0){
     return false;
 }
if (current2.length % 2 != 0) {
    return false;
}
if (current3.length % 2 != 0) {
    return false;
}
else{
    return true;
}

}
