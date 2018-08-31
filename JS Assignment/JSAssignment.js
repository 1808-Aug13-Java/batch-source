
console.log("Max Length");
function maxLength(arr) {
    let longest = "";
    let longestIndex = "";
    for(i in arr) {
        if(longest.length < arr[i].toString().length) {
            longest = arr[i];
            longestIndex = i;
        } else if (longest.length == arr[i].length) {
            longestIndex += ", " + i;
        }
    }
    console.log(longestIndex);
}
maxLength(["red", "blue", "purple", "tortoise", "seven", "Argentina"]);
maxLength([1,2,3,4,5]);

console.log("Reverse Array");
function reverseArray(arr) {
    var newArray = [];
    var i;
    for(i = arr.length - 1; i >= 0; i--) {
        newArray.push(arr[i]);
    }
    console.log(newArray);
}
reverseArray([1,2,3,4,5]);

console.log("Vowel Count");
function vowelCount(word) {
    var vowels = 0;
    for(i of word.toString().toLowerCase()) {
        switch(i) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                vowels++;
            break;
        }
    }
    console.log(vowels);
}
vowelCount("Peacock");
vowelCount(1,2,3,4,5);

console.log("Remove Script");
function removeScript(phrase) {
    phrase = phrase.replace(/Script/g, "");
    console.log(phrase);
}
removeScript("The director burst in and tore up the Script");
removeScript("Oh no! I lost the Script! The Script was the most important part of the movie! Script!")

function isLeapYear(d) {
    //console.log(d);
    var year = d.getFullYear();
    var yearString = year.toString();
    if(yearString.substring(yearString.length - 2) == "00") {
        if(year % 400 == 0) {
            console.log(true);
        } else {
            console.log(false);
        }
    } else if(year % 4 == 0) {
        console.log(true);
    } else {
        console.log(false);
    }
}
isLeapYear(new Date(2016, 2, 23)); //In this format, months start at 0
isLeapYear(new Date(2020, 6, 17)); //This for example is actually July
isLeapYear(new Date(1900, 4, 5)); //CINCO DE MAYO!
console.log("Valid Email");
function isValidEmail(email) {
    function isValidEmail(string) {
        return string.search(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)!=-1;
    }
}
isValidEmail("maxwellmooney13@gmail.com");
isValidEmail("Franklin");
isValidEmail("TypeCast@");
isValidEmail(".com");

console.log("Remove Character");
function removeChar(phrase, place) {
    if(phrase.length == 0) {
        console.log("");
    } else {
        if(place > phrase.length - 1) {
            console.log("Your selected index is out of the array's bounds.");
        } else {
        var begin = phrase.substring(0, place);
        var ending = phrase.substring(place + 1);
        console.log(begin + ending);
        }
    }
}
removeChar("Sally sells oh so many seashells", 10);

console.log("Bubble Sort");
function bubbleSort(numArray) {
    let temp = 0;
    for(i = 0; i <= numArray.length - 1; i++) {
        for(j = 0; j <= numArray.length - 1 - i; j++) {
            if(numArray[j] > numArray[j + 1]) {
                temp = numArray[j];
                numArray[j] = numArray[j + 1];
                numArray[j+1] = temp;
            }
        }

    }
    console.log(numArray);
}
bubbleSort([4, 8, 2, 1, 9, 5]);

console.log("Even Number");
function isEven(num) {
    if(Number.isInteger(num / 2)) {
        console.log(true);
    } else {
        console.log(false);
    }

}
isEven(36);
isEven(27);

console.log("Palindrome");
function isPalindrome(string) {
    var string = string.toString().toLowerCase();
    var newString = "";
    var count = 0;
    while (newString.length < string.length) {
        newString += string.charAt(string.length - count);
        count++;
    }
    if(newString == string) {
        console.log(true);
    } else {
        console.log(false);
    }
}

isPalindrome("Pineapple");
isPalindrome("Racecar");
isPalindrome("tacocat");
isPalindrome(12321)
console.log("Shapes");
function shapes(shape, height, character) {
    var newShape = ""
    switch(shape.toLowerCase()) {
        case "square":
            for(i = 1; i <= height; i++) {
                for(j = 1; j <= height; j++) {
                    newShape += character;
                }
            newShape += "\n";
            }
            break;
        case "triangle":
            for(i = 1; i <= height; i++) {
                for(j = 1; j <= i; j++) {
                    newShape += character;
                }
            newShape += "\n";
            }
            break;
        case "diamond":
            for(i = 1; i <= height; i += 2) {
            for(k = 1; k <= ((height - i) / 2); k++) {
                newShape += " ";
            }
            for(j = 1; j <= i; j++) {
                newShape += character;
            }
            newShape += "\n";
            }

            for(i = height - 2; i >= 1; i-=2) {
            for(k = 1; k <= ((height - i) / 2); k++) {
                newShape += " "
            }
            for(j = 1; j <= i; j++) {
                newShape += character;
            }
            newShape += "\n";
            }
        break;
    }
    console.log(newShape);
}
shapes("SQUARE", 3, "%");
shapes("triangle", 3, "$");
shapes("diamond", 5, "*");

console.log("Rotate Left");
function rotate(arr, displacement) {
    //var moveToBack;
    for(i = 1; i <= displacement; i++) {
        arr.push(arr.shift());
    }
    console.log(arr);
}

rotate([1,2,3,4,5], 1);
rotate([1,2,3,4,5], 6);
rotate([1,2,3,4,5], 3);

console.log("Balanced Brackets");
function balanced(string) {
    let openBrackets = [];
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
    if((openBrackets.length == 0) && (nothingImportant.length == 0)) {
        console.log("Perfectly balanced... As all things should be.");
    } else {
        console.log(false);
    }
}

balanced("()");
balanced("()()");
balanced("(())");
balanced("({[]})");
balanced("(");
balanced(")");
balanced("(()i");
balanced("([)]");
