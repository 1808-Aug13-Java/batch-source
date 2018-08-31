//Problem 1//
function maxLength(arr){
let maxLength = 0;
    for(i in arr){
        for(j in arr){
            if(arr[i] >= arr[j]){
                maxLength=arr[i].length;
            }
        }
    }
    return maxLength;
}

//Problem 2//
function reverseArray(arr){
    let m = 0;
    let n = arr.length-1;
    let size = arr.length/2 >> 0;

    for(i=0; i < size; i++){
        let a = arr[m];
        let b = arr[n];

        arr[m] = b;
        arr[n] = a;
        
        m++;
        n--;
    }

    return arr;
}

//Problem 3//
function vowelCount(str){
    let vowels = /[aeiou]/i;

    count = vowels.exec(str).length; 

    return count;
}

//Problem 4//
function removeScript(str){

    let vowels = /Script/;
    let match = vowels.exec(str);
    let result = str;

    if(match){
        result = str.replace("Script", "");
    }

    return result;
}

//Problem 5//
function isLeapYear(date){
let d = new Date(date);
let result = false;
year = d.getFullYear();

if(year%4 == 0 ){
    result = true;
    if(year%100 == 0 & year%400 !=0){
        result = false;
    }
}

return result;

}

//Problem 6//
function isValidEmail(str){
    let isValid = false;
    let check = /[A-z]*[0-9]*@[A-z]*.com/;
    let match = check.exec(str);
    if(match){
        isValid = true;
    }

    return isValid;
}

//Problem 7//
function removeCharacter(str, index){
    str = str.slice(0, index) + str.slice(index+1);

    return str;
}

//Problem 8//
function bubbleSort(numArray){
    n = numArray.length
    do{
        swapped = false;
        for(i = 0; i < n; i++)
            if(numArray[i-1] > numArray[i]){
                swap(numArray, numArray[i-1], numArray[i] )
                swapped = true;
            }
    }while(swapped)

    return numArray;

}

function swap(numArray, i, j) {
    var temp = numArray[i];
    numArray[i] = numArray[j];
    numArray[j] = temp;
  }



//Problem 9//
function isEven(num){
    let evenNums = /^\d*[02468]$/;
    let match = evenNums.exec(num);
    let isEven = false;

    if(match){
        isEven = true;
    }

    return isEven;
}


//Problem 10//
function isPalindrome(str){
    let isPalindrome = false;
    let reversed = str.split("").reverse().join("").toLowerCase();
    if(str.toLowerCase() == reversed){
        isPalindrome = true;
    }
    
    return isPalindrome;
}

//Problem 11//
function printShape(shape, height, character){

    let shapeArr = new Array();

    switch(shape){
        
        case "Square":

            for(i = 0; i < height; i++){
                shapeArr.push(height);
            }


            

        case "Triangle":
            for(i = 0; i < height; i++){
                shapeArr.push(height - i);
                
            }
 

        case "Diamond":
        for(i = 0; i < (height-1)/2; i++){
            shapeArr.push(1+i*2);
        }
        shapeArr.push(height);

        for(i = 1; i < (height-1)/2; i++){
            shapeArr.push(height-(i*2));
        }

        shapeArr.push(1);

    }

    for(slice of shapeArr){
        console.log(" ".repeat(height - slice)+character.repeat(slice));
    }
}




//Problem 12//
function rotate(array, n){
    let array1 = array;
    for ( i = 0; i < n; i++){
        array1 = rotateByOne(array1);
    }


    // a = array[0];
    // b = array[array.length - 1];
    // c = array[array.length - 2];

    // array[array.length - 1] = a;
    // array[array.length - 2] = b;

    // for(i = 0; i < n - 2; n++){

    // }

    // for(i = 0; i < n; i++){
    //     a = array[i];
    //     b = array[array.length - 1];
    //     c = array[array.length - 2];


    //     array[i] = b;
    //     array[array.length - (1+i)] = a;
    // }

    return array1;

}

function rotateByOne(array){
    // let x = array[array.length - 1], i;
    // for (i = array.length - 1; i > 0; i--){
    //     array[i] = array[i - 1]; 
    // }
    // array[0] = x;

    let x = array[1];
    let y = array[0];
    for (i = 0; i < array.length; i++){
        array[i] = array[i + 1]; 
    }
    array[0] = x;
    array[array.length - 1] = y;

    return array;
}




//Problem 13//
function balanced(str){
    let balanced = false;

    for(i = 0; i < str/2; i++){

    if(str[0] == '('){

        str = str.slice(1);
        str = str.slice(0, -1)
        

    }

    else if(str[0] == '{'){

        str = str.slice(1);
        str = str.slice(0, -1)

    }

    else if(str[0] == '['){

        str = str.slice(1);
        str = str.slice(0, -1)

    }

    }

    return str;

}