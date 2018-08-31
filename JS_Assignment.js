//#1
var arr = ['ah', 'ahhhhhhhhhhhhhhhhhhhh', 
  'ahhhhh'];
function maxlength(arr){
  var lgth = 0;
  var longest;
  for(var i=0; i < arr.length; i++){
    if(arr[i].length > lgth){
        lgth = arr[i].length;
        longest = arr[i];
    }
      
        
            
} 
return arr.indexOf(longest);
}

var b = maxlength(arr);
console.log(b);

//#2
function reverseArray(array) {
  var ret = new Array;
  for(var i = array.length-1; i >= 0; i--) {
      ret.push(array[i]);
  }
  return ret;
}

var a = [3,5,7,8]
var b = reverseArray(a);
console.log(b);

//#3
function vowelCount(string) {
  var m = string.match(/[aeiou]/gi);
  return m === null ? 0 : m.length;
}
a="moo";
b=vowelCount(a);
console.log(b);

//#4
function removeScript(string){
  if (string.includes("script")===true){
  var ret = string.replace('script','');
  return ret;
  }
  else{
    return string;
  }
}

a="mooscript";
b= removeScript(a);
console.log(b);

//#5
function isLeapYear(someDate){
    return ((someDate % 4 == 0) && (someDate % 100 != 0)) || (someDate % 400 == 0);
  }

//#6
function isValidEmail(email) {
var ret = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
if(!ret.test(email) || email == ''){
  return false;
}
else{
  return true;
}
}
a="a.com";
b = isValidEmail(a);
console.log(b);
//#7
function removeChar(str, index) 
 {
  part1 = str.substring(0, index);
  part2 = str.substring(index + 1, str.length);
  return (part1 + part2);
 }

console.log(removeChar("Mooses",0));
console.log(removeChar("Mooses",3));
console.log(removeChar("Mooses",5));

//#8
var numArray = [3, 10, 30, 1, 2, 4];

function bubbleSort(numArray)
{
    var swapped;
    do {
        swapped = false;
        for (var i=0; i < numArray.length-1; i++) {
            if (numArray[i] > numArray[i+1]) {
                var temp = numArray[i];
                numArray[i] = numArray[i+1];
                numArray[i+1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
}

bubbleSort(numArray);
console.log(numArray);

//#9
function isEven(someNum) {
  if (someNum == 0)
    return true;
  else if (someNum == 1)
    return false;
  else if (someNum < 0)
    return isEven(-someNum);
  else
    return isEven(someNum - 2);
}

//#10
function isPalindrome(str) {
  var re = /[^A-Za-z0-9]/g;
  str = str.toLowerCase().replace(re, '');
  var len = str.length;
  for (var i = 0; i < len/2; i++) {
    if (str[i] !== str[len - 1 - i]) {
        return false;
    }
  }
  return true;
 }
 isPalindrome("A man, a plan, a canal. Panama");
 //isPalindrome("dog");

 //#11
 function printShape(shape, height, character){
if(shape==="triangle"){

  var character;
  var array = [];
  for (var i = 0; i < height; i++) {
      array.push('#')
      character = array.join(' ');
      console.log(character + '\n'); 
  }
}

if(shape==="square") {
  var character;
  for (var i = 0; i < height; i++) {
      var array = [];
    for (var j = 0; j < n; j ++) {
      array.push('#')
    }
      character = array.join(' ');
      console.log(character + '\n'); 
  }
}


//#12
function rotate( array , n ){
  array = array.slice();
  while( n-- ){
    var temp = array.shift();
    array.push( temp )
  }
  return array;
}

var numbers = [1,2,3,4];
console.log( rotate( numbers ,2 ) );

//#13
function balanced(string) {

  if (string.length <= 1)
    return false

  let matchingOpeningBracket, ch
  let stack = []

  let openingBrackets = ['[', '{', '(']
  let closingBrackets = [']', '}', ')']

  for (let i = 0; i < string.length; i++) {
    ch = string[i]

    if (closingBrackets.indexOf(ch) > -1) {
      matchingOpeningBracket = openingBrackets[closingBrackets.indexOf(ch)]
      if (stack.length == 0 || (stack.pop() != matchingOpeningBracket)) {
        return false
      }
    } else {
      stack.push(ch)
    }
  }

  return (stack.length == 0)

 }
console.log(balanced("([)]"));
console.log(balanced("()"));
console.log(balanced("{}[]()"));