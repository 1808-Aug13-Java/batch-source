
let capturing = true;
let bubbling = false;

// Note, Capturing takes priority. Therefore, capturing events propogate before
// propogating bubbling evens. 


document.getElementById("inner").addEventListener("click", function() {
	console.log("INNER - Bubbling");
}, bubbling);

document.getElementById("middle").addEventListener("click", function() {
	console.log("MIDDLE - Bubbling");
}, bubbling);

document.getElementById("outer").addEventListener("click", function() {
	console.log("OUTER - Bubbling");
}, bubbling);


document.getElementById("inner").addEventListener("click", function() {
	console.log("INNER - Capturing");
}, capturing);

document.getElementById("middle").addEventListener("click", function(eventObj) {
	console.log("MIDDLE - Capturing");
	// We can access the event object that is passed in 
	// This stops the event from propogating through the hierarchy. 
	eventObj.stopPropagation();
	
	// This stops the propogation entierly, even on same precedence level. 
	eventObj.stopImmediatePropagation();
}, capturing);

document.getElementById("outer").addEventListener("click", function() {
	console.log("OUTER - Capturing");
}, capturing);

