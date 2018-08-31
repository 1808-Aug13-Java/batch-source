// 1. find longest string from an array and return 
// that string's array index
const maxLength = function(arr) {
  let longestStr = {index: -1,
     str: ""};
  
  for(i in arr) {
    if(arr[i].length > longestStr.str.length) {
      longestStr.index = i;
      longestStr.str = arr[i];
    }
  }

  return longestStr.index;

}

// 2. Reverse Array
// While you can use arr.reverse()
// figured I'd show how that might be done

const reverseArray = function(arr) {
  let reversedArray = [];

  for(let i = arr.length - 1; i > -1; i--) {
    reversedArray.push(arr[i]);
  }
  return reversedArray;
}

// 3. Count Vowels
// return count of vowels in string
const vowelCount = function(string) {
  let vowels = "aeiou";
  let vowelCount = 0;
  for(let character of string) {
    // checking against our string of vowels
    if(vowels.includes(character)) {
      vowelCount++;
    }
  }

  return vowelCount;
}

// 4. remove any occurence of 'Script'
const removeScript = function(string) {
  let copyOfString = string;
  if(string.includes("Script")) {
    copyOfString = string.replace(/Script/g, "");
  }

  return copyOfString;
}

// 5. find leap year
// return true if the year is a leap year 
// in the gregorian calender
const isLeapYear = function(date) {
  let yr = date.getFullYear();
  let leapYear = false;
  if(yr % 4 == 0 && (yr % 100 != 0 || yr % 400 == 0)) {
    leapYear = true;
  }

  return leapYear;
}

// 6. Email Validation
const isValidEmail = function(string) {
  // FROM OWASP REGEX LIBRARY
  let regex = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/gi;
  let found = string.match(regex);
  // .match returns null if not found
  // else returns the matched string
  if( found == null ) {
    return false;
  }

  return true;
}

// 7. Remove character
// removeChar(string, index)
// remove a char at specified index
const removeChar = function(string, index) {
  let copyStr = string.split("");
  copyStr.splice(index, 1);

  return copyStr.join("");
}

// 8. Bubble Sort
// bubble sort algorithm to sort arr
// return sorted arr
const bubbleSort = function(numArray) {
  let sorted = false;
  let copyOfNumArray = numArray;
  while(!sorted) {
    let counter = 0;
    for(let i = 1; i < numArray.length; i++) {
      
      let firstNum = copyOfNumArray[i -1];
      let secondNum = copyOfNumArray[i];

      // swap the elements if the earlier on is larger
      if(firstNum > secondNum) {
        copyOfNumArray[i] = firstNum;
        copyOfNumArray[i -1] = secondNum;
        counter++;
      }
    }

    if(counter == 0) {
      sorted = true;
    }
  }

  return copyOfNumArray;
}

// 9.  isEven()
// return true if even
// bitwise operator
const isEven = function(someNum) {
  return (1&someNum) ? false : true;
}

// 10. Palindrome
// return true if someStr is a palindrome
const isPalindrome = function(someStr) {
  if(
    someStr
    .replace(/[^A-Za-z0-9_]/g,"").toLowerCase() 
      == someStr
          .replace(/[^A-Za-z0-9_]/g,"")
          .split("")
          .reverse()
          .join("")
          .toLowerCase()
    ) {
    return true;
  }
  return false;
}

// 11. Shapes
// print square, triangle, diamond
// with specified height and character
// printShap(shape, height, character)
const printShape = function(shape, height, character) {
  switch(shape.toLowerCase()) {
    case "square":
      printSquare(height, character);
      break;
    case "triangle":
      printTriangle(height, character);
      break;
    case "diamond":
      printDiamond(height, character);
      break;
    default:
      break;
  }
}

const printSquare = function(height, character) {
  // the demo on MDN of repeat is hilarious
  let string = character.repeat(height);
  for(let i = 0; i < height; i++) { 
    console.log(string + "\n");
  }
}

const printTriangle = function(height, character) {
  for(let i = 0; i < height; i++) {
    console.log(character.repeat(i + 1));
  }
}
//  * 2 sp 1 c
// *** 1 sp 3 c
//***** 0 5 c
// *** 1 sp 3c
//  * 2 sp 1c

//   * 3sp 1c index 0
//  *** 2sp 3c index 1
// ***** 1 sp 5c index 2
//******* 0sp 7c index 3 
// ***** 1sp 5c index 4
//  *** 2sp 2c index 5
//   * 3sp 1c index 6
// middle index - current index = spaces
// amt of chars => 1 + 2*3 at index 3
// amt of chars => 1 + 2*0 at index 0 and 6
// amt of chars => 1+2*1 at index 1 and 5
// amt of chars => 1+2*2 at index 2 and 4
// 3 - abs(3-index)
const printDiamond = function(height, character) {
  let midIndex = Math.floor(height/2);
  for(let i = 0; i < height; i++) {
    let line = " ".repeat(Math.abs(midIndex - i));
    let amtToRepeat = 1 + 2 * (midIndex - Math.abs(3 - i));
    line += character.repeat(amtToRepeat);
    console.log(line)
  }
}

// 12. Rotate Left
// [1,2,3,4] => rotate 1 => [2,3,4,1]
// rotate 3 => [4,1,2,3]
const rotate = function(arr, n) {
  if(arr.length == 0 || arr.lenght == 1) {
    return arr;
  } else if (n == 0) {
    return arr;
  }
  let len = arr.length;
  let copyArr = arr;
  let rotations = n % len;
  if(n >= len) {
    rotations = (n % len) + 1;
  }
  console.log('rotations: ' + rotations);
  
  // n % arr.length number of shift() 
  // [1,2,3,4]
  // 1 [2,3,4] then adding to end
  let appendArr = [];
  for(let i = 0; i < rotations; i++) {
    appendArr[i] = copyArr.shift();
  }
  // orginal arr is modified somehow? 
  return arr.concat(appendArr);
}

// 13. Balanced Brackets
// a bracket is one of the following (){}[];

const balanced = function(string) {
  let stack = [];
  let openingBrackets = "({[";
  let closingBrackets = "]})";
  for(character of string) {
    if(openingBrackets.includes(character)) {
      stack.push(character);
    } else if (closingBrackets.includes(character)) {
      let top = stack[stack.length - 1];
      switch(character) {
        case ")":
          if(top == "("){
            stack.pop();
          } else {
            return false;
          }
          break;
        case "}":
          if(top =="{") {
            stack.pop();
          } else {
            return false;
          }
          break;
        case "]":
          if(top == "[") {
            stack.pop();
          } else {
            return false;
          }
          break;
      }
    }
  }

  return stack.length > 0 ? false : true;
} 

