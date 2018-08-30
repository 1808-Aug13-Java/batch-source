

function adder() {
	let counter = 0;
	function add(){
		counter += 1;
		console.log(counter);
	}
	return add;
}

function adder2() {
	let counter = 0;
	return function () {
		return ++counter;
	}
}


let my2Adder = adder();
my2Adder();



// Callback Functions: Passing a function as a parameter. 
function apply2(func) {
	return func(2);
}

// Function that adds 5. 
//function add5(param) {
//	return param + 5;
//}
// This does the same, but is better for visualizing our arrow notation
let add5 = (param) => param + 5;
// Or 
add5 = (param) => {
	return param + 5;
}


// Pass the add5 function to apply2 and print the result. 
console.log(apply2(add5));


