// 1. Longest String
function longestString(arr) {
    let longestIndex = 0;
    let longestStr = arr[0];
    for (let i = 1; i < arr.length; i++) {
        if (arr[i].length > longestStr.length) {
            longestStr = arr[i];
            longestIndex = i;
        }
    }
    return longestIndex;
}

console.log(longestString(["cat", "horse", "elephant", "gorilla", "chihuahua"]));

// 2. Reverse Array
function reverseArray(arr) {
    let reverseArr = [];
    for (let i = 0; i < arr.length; i++) {
        reverseArr.unshift(arr[i]);
    }
    return reverseArr;
}
console.log(reverseArray([1, 2, 3, 4, 5]));

//3. Count Vowels
function vowelCount(str) {
    const count = str.match(/[aeiou]/gi);
    return count == null ? 0 : count.length;
}
console.log(vowelCount("astigmatism"));

//4. Remove Script
function removeScript(str) {
    const newString = str.replace(new RegExp("Script", 'g'), "");
    return newString;
}
console.log(removeScript("This is Scriptmy testScript string."));

//5. Find Leap Year
function findLeapYear(date) {
    let year = date.getYear();
    year += 1900;
    // console.log(year);
    if (year % 4 === 0 && year % 100 !== 0) {
        return true;
    } else if (year % 400 === 0) {
        return true;
    }
    return false;
}
console.log(findLeapYear(new Date('January 8, 1973 01:00:00')));
console.log(findLeapYear(new Date('January 8, 1600 01:00:00')));
console.log(findLeapYear(new Date('January 8, 1900 01:00:00')));
console.log(findLeapYear(new Date('January 8, 1904 01:00:00')));

//6. Email Validation
function isValidEmail(str) {
    const isEmail = str.match(new RegExp(/\w+@\w+.\w+/));
    return isEmail ? true : false;
}
console.log(isValidEmail("jan.a.balangue@gmail.com"));
console.log(isValidEmail("armadillo"));
console.log(isValidEmail("x@i."));

//7. Remove Character
function removeCharacter(str, index) {
    let newString = "";
    if (index >= 0 && index < str.length) {
        newString = newString.concat(str.substring(0, index)).concat(str.substring(index + 1));
        return newString;
    } else {
        return undefined;
    }
}
console.log(removeCharacter("processing", 7));
console.log(removeCharacter("force fed", -1))
console.log(removeCharacter("dog", 7));

//8. Bubble Sort
function bubbleSort(arr) {
    for (let i = 0; i < arr.length; i++) {
        for (let j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                let temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    return arr;
}
console.log(bubbleSort([11, 1, 22, 4, 5, 33, 6]));

//9. Even Number
function isEven(someNum) {
    while (someNum > 1) {
        someNum -= 2;
    }
    if (someNum === 1) {
        return false;
    }
    return true;
}
console.log(isEven(59));
console.log(isEven(4));

//10. Palindrome
function isPalindrome(str) {
    const reverseStr = str.split("").reverse().join("");
    return str === reverseStr;
}
console.log(isPalindrome("racecar"));
console.log(isPalindrome("confetti"));

//11. Print Shape
function printShape(shape, height, character) {
    let result = "";
    switch (shape) {
        case "Square":
            for (let i = 0; i < height; i++) {
                for (let j = 0; j < height; j++) {
                    result += character;
                }
                result += "\n";
            }
            break;
        case "Triangle":
            for (let i = 0; i < height; i++) {
                for (let j = 0; j <= i; j++) {
                    result += character;
                }
                result += "\n";
            }
            break;
        case "Diamond":
            for (let i = 0; i < height / 2; i++) {
                for (let j = 1; j < Math.abs(i - height / 2); j++) {
                    result += " ";
                }
                for (let k = 0; k < height - 2 * Math.abs(i - (height / 2)) + 1; k++) {
                    result += character;
                }
                result += "\n";
            }
            for (let i = height / 2 + 1; i < height; i++) {
                for (let j = 0; j < Math.abs(i - height / 2); j++) {
                    result += " ";
                }
                for (let k = 1; k < height - 2 * Math.abs(i - (height / 2)) + 1; k++) {
                    result += character;
                }
                result += "\n";
            }
            break;
    }
    console.log(result);
}
printShape("Square", 5, "$");
printShape("Triangle", 7, "#");
printShape("Diamond", 9, "*");

//12. Rotate Left
function rotateLeft(array, n) {
    while (n > array.length) {
        n -= array.length;
    }
    let firstArr = [];
    let secondArr = [];
    for (let i = 0; i < n; i++) {
        firstArr.push(array[i]);
    }
    for (let i = n; i < array.length; i++) {
        secondArr.push(array[i]);
    }
    secondArr.push(...firstArr);
    return secondArr;
}
console.log(rotateLeft([1, 2, 3, 4, 5], 6));
console.log(rotateLeft([1, 2, 3, 4, 5], 3));
console.log(rotateLeft([1, 2, 3, 4, 5], 1));

//13. Balanced Brackets
function balanced(str) {
    const brackets = {
        "(": ")",
        "[": "]",
        "{": "}",
        ")": "(",
        "]": "[",
        "}": "{"
    }
    strArr = str.split("");
    let endIndex = strArr.length - 1;
    for (let i = 0; i < strArr.length; i++) {
        if (strArr[i] == brackets[strArr[endIndex]]) {
            --endIndex;
        } else {
            return false;
        }
    }
    return true;
}
console.log(balanced("()()()"));
console.log(balanced("[[{}]]"));
console.log(balanced("[[[["));
console.log(balanced("{[}]}}"));