
function maxLength(array){
	if(array.length == 0 || array == []) {return null };
	
	var max = 0;
	var indexMax = 0;
	var indexCounter = 0;

	for(i of array)	{
		if (i.length > max){
			indexMax = indexCounter;
			max = i.length;
		}
		indexCounter++;
	}
	return indexMax;
}

function reverseArray(array){
	var number = 0;
	
	for(i in array)
	{
		number++;
	}
	
	for(var c = 0; c < (number / 2);c++){
		let temp = array[c];
		array[c] = array[number - ( c + 1)];
		array[number - (c + 1)] = temp;
	}
	return array;
	
}

function vowelCount(string){

	var ret = 0;
	for(var c = 0; c < string.length; c++){
		let ch = string.charAt(c);
		
		if(ch == 'a' || ch == 'A' || 
			ch == 'e' || ch == 'E' ||
			ch == 'i' || ch == 'I' ||
			ch == 'o' || ch == 'O' ||
			ch == 'u' || ch == 'U'){
			ret++;
		}
		
	}
	return ret;

}

function removeScript(string){

	var loc = string.indexOf("Script");
	if (loc == -1)
		return string;
	
	var ret = string.substring(0,loc) + string.substring(loc + 6, string.length);
	
	return ret;

}

function isLeapYear(date){
	return (date.getFullYear() % 4) == 0;
}

function isValidEmail(string){
	var atIndex = string.indexOf('@');
	var secAtIndex = string.lastIndexOf('@');
	var dotIndex = string.lastIndexOf('.');
	var comIndex = string.indexOf(',');


	
	return atIndex > 0 && atIndex == secAtIndex && dotIndex > (atIndex + 1) && (dotIndex != string.length - 1) && comIndex == -1;
}

function removeChar(string, index){

	var ret = string.substr(0, index);
	
	for(var c = index + 1; c < string.length; c++) {
		ret = ret + string.charAt(c);
	}
	return ret;
}

// Bubbl Sort
function bubbleSort(numArray){
	let done = false;
	while(!done){
		
		done = true;
		for(var rust = 0; rust < numArray.length - 1; rust++){
			if(numArray[rust] > numArray[rust + 1])
			{
				done = false;
				let temp = numArray[rust];
				numArray[rust] = numArray[rust + 1];
				numArray[rust + 1] = temp;
			}
		}
	}
	return numArray;
}

// Even Number
function isEven(someNum){
	
	let tens = someNum * 5;
	let sub = Math.floor(tens / 10);
	sub *= 10;
	return (tens - sub) == 0;
}

function isPalindrome(someStr){
	for(var c = 0; c < someStr.length / 2; c++){
		if (someStr.charAt(c) != someStr.charAt(someStr.length - (c+1)))
			return false;
	}
	return true;
}

function printShape(shape, height, character){
	if(shape.toLowerCase() == "square"){
		
		var line = "";
		for(var c = 0; c < height; c++){
			
			
			for(var rust = 0; rust < height; rust++){
				line += character;
			}
			line += "\n";
		}
		console.log(line);
	}else if(shape.toLowerCase() == "triangle"){
		
		var line = '';
		for(var c = 0; c < height; c++){
			
			
			
			for(var rust = 0; rust < c + 1; rust++){
				line += character;
			}
			line += "\n";
		}
		console.log(line);
	}else if(shape.toLowerCase() == "diamond"){
		
		let midElement = Math.floor(height / 2);
		var line = "";
		for(var c = 0; c < height; c++){
			
			
			for(var rust = 0; rust < height; rust++){
				
				
				
				if(c + rust < midElement ||
					(height - c) + rust < (midElement + 1) ||
					c + (height - rust) < (midElement + 1) ||
					(height - c) + (height - rust) < (midElement + 2)){
					line += ' ';
				}else{
					line += character;
				}
			}
			line += "\n";
		}
		console.log(line);
		
	}

}


function balanced(string){
	let roundCount = 0;
	let squareCount = 0;
	let curlyCount = 0;
	
	var tracker = [];
	
	for(var rust = 0; rust < string.length; rust++){
	
		switch(string[rust]){
			case '(':
				tracker.push('(');
				break;
			case '[':
				tracker.push('[');
				break;
			case '{':
				tracker.push('{');
				break;
			case ')':
				if(tracker.pop() != '(') return false;
				break;
			case ']':
				if(tracker.pop() != '[') return false;
				break;
			case '}':
				if(tracker.pop() != '{') return false;
				break;
			
		}
	
	}
	
	return tracker.length == 0;
}