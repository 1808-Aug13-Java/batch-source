let capturing = true;
let bubbling = false;

document.getElementById("inner").addEventListener("click",function(){
    alert("INNER - capturing");
},capturing);

document.getElementById("inner").addEventListener("click",function(){
    alert("INNER- bubbling");
},bubbling);

document.getElementById("middle").addEventListener("click",function(){
    alert("MIDDLE - bubbling");
},bubbling);

document.getElementById("outer").addEventListener("click",function(){
    alert("OUTER - bubbling");
},bubbling);

document.getElementById("middle").addEventListener("click",function(){
    alert("MIDDLE - capturing");
},capturing);

document.getElementById("outer").addEventListener("click",function(){
    alert("OUTER - capturing");
},capturing);