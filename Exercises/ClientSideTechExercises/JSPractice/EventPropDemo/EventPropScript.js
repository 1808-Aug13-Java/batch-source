/**
 * 
 */

let capture = true;
let bubbling = false;

document.getElementById("inner").addEventListener("click", function() {
	alert("INNER - bubbling");
}, bubbling);

document.getElementById("middle").addEventListener("click", function() {
	alert("MIDDLE - bubbling");
}, bubbling);

document.getElementById("outer").addEventListener("click", function() {
	alert("OUTER - bubbling");
}, bubbling);

document.getElementById("inner").addEventListener("click", function() {
	alert("INNER - capturing");
}, capturing);

document.getElementById("middle").addEventListener("click", function() {
	alert("MIDDLE - capturing");
}, capturing);

document.getElementById("outer").addEventListener("click", function() {
	alert("OUTER - capturing");
}, capturing);

// inner returns bubbling before capturing simply because we wrote bubbling first and it is on the same object
// can do e.stopPropagation(); // the difference is anything on the same element will continue everything on that element and then complete
// also have stopImmediatePropagation();