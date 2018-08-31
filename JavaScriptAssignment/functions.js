function maxString() {
    var letn=0;
    var index=0;
    for (i in arguments) {
        console.log(i + " length is: " + arguments[i].length);
        if(arguments[i].length>letn){
            letn = arguments[i].length;
            index=i;
        }
    }
    console.log(arguments[index]+" "+index);
}

function reverseArray() {
	let arrLen = arguments.length-1;
	let revArr= [].slice.call(arguments);
	for(i=0;i<=arrLen;i++){
	revArr[i]=arguments[arrLen-i];
	}
	return revArr;
}

function vowelCount(str) {
	str = str.split("");
	console.log(str);
	let count = 0;
	for(i of str){
		if((i=='a')||(i=='e')||(i=='i')||(i=='o')||(i=='u')){
	count++;
	}
	}
	return count;
}

function removeScript(string){
	if(string.search("Script")>-1){
		string = string.slice(0,string.search("Script"))+string.slice(string.search("Script")+6,string.length);
	}
	return string;
}

function isLeapYear(year){
	if(year%4==0){return true;}else{return false;}
}

function isValidEmail(string){
	if((string.indexOf("@")!=string.lastIndexOf("@"))||(string.lastIndexOf("@")>string.indexOf(".com"))){
		return false;
	}else{
		return true;
	}
}

function removeChar(string, index){
	string = string.replace(string.charAt(index),"");
	return string;
}


function bubbleSort(numArray){
	do{
	var swapped = false;
	var lastUnsortedElement = numArray.length;
	var temp=0;
	for(var i = 0; i<lastUnsortedElement;i++){
		if(numArray[i]>numArray[i+1]){
		temp = numArray[i];
		numArray[i] = numArray[i+1];
		numArray[i+1]=temp;
		swapped=true;
		}
	}
	
	}while(swapped);
	return numArray;
}

function isEven(someNum){
	if(someNum&1){
	return false;
	}
	return true;
}

function isPalindrome(someStr){
	for(i=0, j=someStr.length-1;i<j; i++, j--){
		if(someStr.charAt(i)!=someStr.charAt(j)){
		return false;
		}
	}
	return true;
}

function printShape(shape, height, character){
	var str="";
	if(shape=='Square'){
		for(i=0;i<height;i++){
			for(j=0;j<height;j++){
				str = str.concat(character);
			}
			console.log(str);
			str="";
		}
	}
	if(shape=='Triangle'){
		for(i=0;i<height;i++){
			for(j=0;j<=i;j++){
				str = str.concat(character);
			}
			console.log(str);
			str="";
		}
	}
	if(shape=='Diamond'){
		var empty = (height-height%2)/2;
		for(i=0;i<height;i=i+2){
			for(j=0;j<empty;j++){
				str = str.concat(" ");
			}
			for(j=0;j<=i;j++){
				str = str.concat(character);
			}
			empty--;
			console.log(str);
			str="";
		}
		empty++;
		for(i=height-3;i>=0;i=i-2){
			for(j=0;j<=empty;j++){
				str = str.concat(" ");
			}
			for(j=0;j<=i;j++){
				str = str.concat(character);
			}
			empty++;
			console.log(str);
			str="";
		}
	}
}

function rotate(array, n){
	for(i=0;i<n;i++){
	array.push(array.shift());}
	return array;
}

function balanced(string){
	var s = [];
	var strArr = string.split("");
	for(i in strArr){
		if(isMatchingPair(s[s.length-1],strArr[i])){
			s.pop();
		}else{
			s.push(strArr[i]);
		}
	}
	if(s.length>0){
	return false;}
	return true;
}

function isMatchingPair(first, second){
	if(first=='['&&second==']'){
		return true;
	}else if(first=='{'&&second=='}'){
		return true;
	}else if(first=='('&&second==')'){
		return true;
	}
	return false;
}