/**
 * 
 */

console.log('hello from our javascript file')
console.log("5: " + isNaN(5));
console.log("'5' :" + isNaN('5')); // also get false because of type coercion
console.log("false: " + isNaN(false));
console.log(Number(false)); // explicit casting
console.log("null: " + isNaN(null));
console.log("undefined: " + isNaN(undefined));
console.log(Number(undefined)); // only exception because it can't be type coerced
console.log("new date: : " + isNaN(new Date()));
console.log(Number(new Date()));



// <script src="JSDemo.js"> </script> should be added to body of html
// keep scoping in mind

function hoistTest1() {
	let x;
	x = 6;
	console.log(x);
}

function hoistTest2() {
	console.log(x);
	let x;
}

hoistTest2()

function hoistTest3() {
	var x;
	x = 6;
	console.log(x);
}

hoistTest3()

function hoistTest4() {
	console.log(x);
	var x;
}

hoistTest4()

function hoistTest5() {
	console.log(x);
	var x = 5;
}

hoistTest4()