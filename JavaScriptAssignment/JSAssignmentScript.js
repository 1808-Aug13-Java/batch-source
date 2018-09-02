/////1
function maxLenght(arr){
    let longest = 0;
    let indexLongest = 0
}



/////2
function reverseArray(arr){
    let newArr = [];
    for(let i = arr.length-1; i>=0; i--){
        newArr[arr.length-i-1] = arr[i];
}
return newArr;
}

let myArray = [1,2,3,4,5]
console.log(reverseArray(myArray));


/////3
function vowelCount(string){
    let count = 0;
    for (letter of string)
    switch(letter){
        case"a":
        case"e":
        case"i":
        case"o":
        case"u":
        case"A":
        case"E":
        case"I":
        case"O":
        case"U":
        count++;
    }
    return count;
}

console.log(vowelCount("Matrix Trilogy"));


/////4
function removeScript(string){
    return string.replace(/Script/g,"");
}
console.log(removeScript("Remove this Script."));


/////5
function leapYear(year){
    var result; 
    if (year%400){
      result = true;
    }
    else if(year%100){
      result = false;
    }
    else if(year%4){
      result= true;
    }
    else{
      result= false;
    }
    return result;
 }

 console.log(leapYear(2016));
 console.log(leapYear(2018));