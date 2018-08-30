

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
