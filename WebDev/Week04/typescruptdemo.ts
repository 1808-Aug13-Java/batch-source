
function doSomething() {
	for (let i=0; i<5; i++) {
		// Do something
	}
}



let counter = 5; // This implicitly makes this a number type. 
//counter = "a"; // Cant to this. There is type safety.

let x; // Can reassing the following as we don't declare it with a type. 
x = 5;
x = true;
x = "asdf";


let num: number; // This explicitly makes this a number type. 
num = 9;
num = "fish"; // Can't do this as we declared 'num' as a Number. 

// Some other examples. 
let bool: boolean;
let str: string;
let obj: object;


// Can make return type explicit. 
function myVoidFunction(): void {
	//return 9;// Can't return a number on void. 
	return;
}
function myNumFunction(): number {// Has error if not returning something
	return 4;
	// return "asdf";// Can't return a non-number. 
}

let neverTrue = function(value:any) { // Nothing will be a string and a number. 
	if (typeof value === "string" && typeof value === "number") {
		return value;
	}
}


let u: number[]; // Declare a number array
u = [4, 5, 6, 4];
let v: string[]; // Declare a string array
v = ["asfd", "qwer"];// Can only add strings
let w: any[]; // Declare an array of anything.
w = ["four", 5, 6.0];

// Enforce passing a number to this lambda
let drawPoint = (x: number, y: number) => {
}
drawPoint(4, 6); // Can only give numbers to this lambda. 

// This has no enforcement of any type. 
let drawPoint2 = (point) => {
}
drawPoint2({"asfd":"qwer", 5:6});

// Enforcing a structure for the point
let drawPoint3 = (point: {x: number, y: number}) => {
}
// drawPoint3({name;"bob", age:5});// Can't do this
drawPoint3({x:4, y:7});


// We can define a common interface for a point. Good for enforcing a particular structure. 
interface Point {
	x: number;
	y: number;
}
let drawPoint4 = (point: Point) => {
}
drawPoint4({y:2, x:3});


// Can also make a class to define types
class AlsoPoint {
	x: number;
	y: number;


	// Can define a constructor for this object. 
	// Can also define an OPTIONAL PARAMETER by use of the '?' after the name of the variable
	constructor(_x: number, _y?:number) {
		this.x = _x;
		this.y = _y;
	}

	drawPoint = () => {
		console.log("X:" + this.x + "  Y:" + this.y);
	}


} // end of class AlsoPoint


// Can provide a variable amount of arguments with optional parameters
let myPoint: AlsoPoint = new AlsoPoint(1);
myPoint.drawPoint;

// Can provide myPoint as it has an x & y that are numbers. 
drawPoint4(myPoint);








