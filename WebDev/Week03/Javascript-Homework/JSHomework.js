
// Returns the longest string in an array. 
// Returns undefined for a zero length array.
function maxLength(array) {
	if (array.length == 0) {
		return;
	}
	
	// Set the current max to the first one. 
	let maxLength = array[0].length;
	let maxVal = array[0];
	
	let tempLength;
	
	// Loop through each index. 
	for (obj of array) {
		tempLength = obj.length;
		
		// If the length of the current string is greater than the current max 
		// replace the current max. 
		if (tempLength > maxLength) {
			maxLength = tempLength;
			maxVal = obj;
		}
	}
	
	return maxVal;
}

// Reverses the elements of an array
function reverseArray(array) {
	let temp;
	// For each element, swap it with the element the same distance
	// from the end. 
	for (var i=0; i<array.length/2; i++) {
		temp = array[i];
		array[i] = array[array.length-1-i];
		array[array.length-1-i] = temp;
	}
	console.log(array);
}

reverseArray([1, 2, 3, 4, 5]);

