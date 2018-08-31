let person3 = {name: "Paul", age:40};
for (i in person3) {
	console.log(i + " is: " + person[i]);
}

let arr = ['green', true, 42];
for (i in arr) {
	console.log("#" + i + " is " + arr[i]); // i would give 0, 1, and 2 
}

for (i of arr) {
	console.log(i); // gives the values green, true, and 42
}

// can't use of in something we can't iterate over, like object, but arrays are fine

function printAll(a, b, c, d) { // can use printAll without enough parameters, sets the extra to undefined parameters
	console.log(a);
	console.log(b);
	console.log(c);
	console.log(d);
}

// can use extra parameters for this
function printAllAgain() { // can access parameters using array for additional arguments
	for (i of arguments) {
		console.log(i);
	}
}

////////////////// closure
function add() {
	let counter = 0;
	counter +=1;
	console.log(counter);
}

add();
add();
add();
// why closures are important!

function add() {
	let counter = 0;
	// closer is basically from here
	return function() {
		return ++counter;
	} // to here
}
// closures are basically the chaining of scopes


// callback function: function that has another function as a parameter
function apply2(fun) {
	return fun(2); // return add5(2)
}

function add5(param) {
	return param+5; // return 2 + 5
}

console.log(apply2(add5));

// also in JavaScript: lambdas
let add5Again = function() {
	
} // this function is the same as console.log(apply2(add5))
// this structure is more conducive to realizing arrow notation

let add5 = (param) => param + 5;

let add5 = (param) => {
	return param + 5; // same as above but must use return keyword!!
}

// uses in Java: one method in functional interfaces, can provide iteration using foreach method in collections 

let p = {name: "John", age: 28}; // {} is an object literal, one way to create an object
// can also create an object by using a function that returns one for you

function makePerson(name, age) {
	let person = {"name": name, "age": age};
	return person;
}

let p2 = makePerson("Sally", 30);

// can also create it with a constructor
function Person(name, age) {
	this.name = name;
	this.age = age;
}

let p3 = new Person("Molly", 45);
let person3 = new Person("Molly", 45);
