let capture = true;
let bubbling = false;

document.getElementById("inner").addEventListener("click", function() {
  alert("inner - bubbling")
}, bubbling);

document.getElementById("middle").addEventListener("click", function() {
  alert("middle - bubbling")
}, bubbling);

document.getElementById("outer").addEventListener("click", function() {
  alert("outer - bubbling")
}, bubbling);

document.getElementById("inner").addEventListener("click", function() {
  alert("inner - capturing")
}, capture);

document.getElementById("middle").addEventListener("click", function(e) {
  alert("middle - capturing");
  e.stopPropagation();
}, capture);

document.getElementById("outer").addEventListener("click", function() {
  alert("outer - capturing")
}, capture);