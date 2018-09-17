function doSomething() {
    for (let i = 0; i < 5; i++) {
        console.log(i);
    }
}

// console.log("finally: " + i); not allowed in TypeScript but allowed in JavaScript

// let counter = 5;
// counter = "A"; also not allowed in TypeScript although allowed in JavaScript

// works because we didn't declare a type and assign it right off the bat
let x;
x = 9;
x = false;
x = "blue";

// declaring a variable, can be complex datatypes
// format is variable name colon datatype
let myNum: number;
myNum = 9;
// Doesn't work
// myNum = false;
// myNum = "blue";

let boo: boolean;
let str: string;
let obj: object;

// we also have the type void,
function myVoidFunction() { // can be explicit and return void
    console.log("This function prints but doesn't return anything");
}

function myNumberFunction(): number {
    console.log("This function prints and has a return type");
    return 9;
}

// also have a type of never
// difference between number and Number: like casting to an object, casts implicitly
function myDeclaredVoidFunction(): void {
    console.log("Prints but doesn't return stuff");
}

let neverTrue = function(value: any) {
    if (typeof value === "string" && typeof value === "number") {
        return value;
    }
}

let rickRoll = function() {
    while(true) {
        console.log("never gonna give you up");
        console.log("never gonna let you down");
    }
}

// declaring arrays
let u: number[];
let v: string[];
let w: any[];

// function hoisting and variable hoisting is still a thing
// closures still exist

// anything can be passed in, like String
let drawPoint = (x,y) => {
    // implementation
}

let drawPoint2 = (point) => {
    // implementation
    // access point.x and point.y
}
drawPoint2({
    x: 3,
    y: 5
});

drawPoint2({
    name: "bob",
    age: 45,
    email: "bob@gmail.com"
});

// the good one:
let drawPoint3 = (point: {x: number, y: number}) => {
    // implementation
    // access point.x and point.y
}

drawPoint3({
    x: 3,
    y: 5
});

/* No longer works, which is good:
drawPoint3({
    name: "bob",
    age: 45,
    email: "bob@gmail.com"
});*/

interface Point {
    x: number,
    y: number
}

let drawPoint4 = (point: Point) => {
    // implementation
    // access point.x and point.y
}

class AlsoPoint {
    x: number;
    y: number;
    constructor(_x?: number, _y?: number) {
        this.x = _x;
        this.y = _y
    }

    drawPoint = () => {
        console.log("x is " + this.x + ", y is " + this.y);
    }
}
// can't overload constructors but can work around it by making parameters option using something like a question mark
// an optional parameter can't be followed by a required parameter

let myPoint = new AlsoPoint();
myPoint.drawPoint();

drawPoint4(myPoint);