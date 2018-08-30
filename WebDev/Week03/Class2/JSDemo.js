

window.onload = function() {
	console.log("Window Loaded");
}

// Note this runs first, likely similar to how python runs. 
let x = 12;
console.log("Print X: " + x);





function myFunc () {
	console.log("Print Global X From a Function: " + x);
	// This causes the first line to throw an error!
//	let x = 5;
	
//	console.log("Print Local X From a Function: " + x);
}

function myFunc2() {
	let x = 5;
	console.log("Print Local X From a Function: " + x);
}

myFunc();
myFunc2();

console.log("Print X after function: " + x);

// Constants can be reassiged, but their properties can be, just like in java. 
const person1 = {name:'joe', age:28};
const person2 = {name:'sam', age:36};


// Demonstration of var-scope verses let-scope
var pass = false;
if (70 > 60) {
	var pass = true;
}
console.log("\n'var' Pass: " + pass);

let pass2 = false;
if (70 > 60) {
	let pass2 = true;
}
console.log("\n'let' Pass: " + pass2);

console.log("\n");
// Can use an enhanced for loop over a set. 
let person3 = {name:'paul', age:40};
for (i in person3) {
	console.log(i + " is: " + person3[i]);
}

// Can use regular/enhanced for loop for array
let myArray = ['green', true, 42];
for (i in myArray) {
	console.log(i + "th element: " + myArray[i]);
}

// Now you don't even have to deal with indexes with the 'of' keyword
for (obj of myArray) {
	console.log("Is: " + obj);
}



function printAll(a, b, c, d) {
	console.log(a);
	console.log(b);
	console.log(c);
	console.log(d);
}
printAll('a');


function printAllAgain() {
	for (obj of arguments) {
		console.log(obj);
	}
}
printAllAgain('a', 'b');

// Can see that aray has a __proto__ which is where it looks for methods 
// that myArray doesn't have itself. 
console.log(myArray);





function adder() {
	let counter = 0;
	function add(){
		counter += 1;
		console.log(counter);
	}
	return add;
}


let myAdder = adder();

myAdder();







