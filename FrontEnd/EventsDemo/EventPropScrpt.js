let capturing=true;
let bubbling=false;



document.getElementById("inner").addEventListener("click",function(){
    alert("INNER-bubbin") //since this is physically above inner capturing, it will occur before capturing and they are on the same object 
}, bubbling
);


document.getElementById("middle").addEventListener("click", function () {
    alert("MIDDLE-bubbin")
},bubbling
)

document.getElementById("outer").addEventListener("click", function () {
    alert("OUTER-bubbin")
},bubbling
)



document.getElementById("inner").addEventListener("click", function () {
    alert("INNER-capturing")
}, capturing
);


document.getElementById("middle").addEventListener("click", function (e) {
    
    alert("MIDDLE-capturing")
    // e.stopPropagation;
    e.stopImmediatePropagation;
}, capturing
)

document.getElementById("outer").addEventListener("click", function () {
    alert("OUTER-capturing")
}, capturing
)

