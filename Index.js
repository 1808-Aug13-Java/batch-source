//#1
var ele = document.getElementById("google");

ele.onclick = function() {
    ele.href = "https://www.google.com"; 
    ele.setAttribute('target', '_blank')
    window.open(ele);
    return false; };

var ele1 = document.getElementById("twitter");

ele1.onclick = function() {
        ele1.href = "https://www.twitter.com"; 
        ele1.setAttribute('target', '_blank')
        window.open(ele1);
        return false; };

var ele2 = document.getElementById("slack");

ele2.onclick = function() {
            ele2.href = "https://slack.com/get-started"; 
            ele2.setAttribute('target', '_blank')
            window.open(ele2);
            return false; };

var ele3 = document.getElementById("javadocs");

ele3.onclick = function() {
                ele3.href = "https://www.javadocs.com"; 
                ele3.setAttribute('target', '_blank')
                window.open(ele3);
                return false; };

//#2
document.getElementById("planet").options[3].disabled = true;

//#3


let selection = document.getElementById("planet");

selection.onchange = function optionchange(){
let p = document.getElementsByTagName('p');

	if (selection.value != "Earth") {
		for (i = 0; i < p.length; i++) {
			if (p[i].innerHTML == "Beep boop") {
			 p[i].removeAttribute("hidden");
			 p[i].style.visibility = "";
			}
		}
	}
	
	else if (selection.value == "Earth") {
		for (i = 0; i < p.length; i++) {
			if (p[i].innerHTML == "Beep boop") {
				p[i].style.visibility = "hidden";
			}
			
		}
		
	}

}








// // //#4
// // let count = 1000;

// // function addRow(name, email, phone, , , , ){
// //     let row = document.createElement("tr");

// //     let cell1 = document.createElement("td");
// //     let cell2 = document.createElement("td");
// //     let cell3 = document.createElement("td");
// //     let cell4 = document.createElement("td");
// //     let cell5 = document.createElement("td");
// //     let cell6 = document.createElement("td");
// //     let cell7 = document.createElement("td");

// //     row.appendChild(cell1);
// //     row.appendChild(cell2);
// //     row.appendChild(cell3);

// //     if(id===undefined){
// //         id = count++;
// //     }
// //     cell1.innerHTML = name;
// //     cell2.innerHTML = email;
// //     cell3.innerHTML = phone;
// //     cell1.innerHTML = name;
// //     cell2.innerHTML = email;
// //     cell3.innerHTML = phone;
// //     cell3.innerHTML = phone;

// //     document.getElementById("students").appendChild(row);
// // }

// // document.getElementById("studentButton").addEventListener("click", addNew);

// // let errorDisplayed = false;

// // function addNew(){
// //     let name = document.getElementById("name").value;
// //     let major = document.getElementById("major").value;

// //     console.log(`name is ${name} and major is ${major}`);

// //     if((name && major)){
// //         if(errorDisplayed){
// //             removeError();
// //             errorDisplayed = false;
// //         }
// //         addRow(name, major);
// //     } else {
// //         if(!errorDisplayed){
// //             displayError();
// //             errorDisplayed = true;
// //         }
// //     }
// // }

// // function displayError(){
// //     let error = document.getElementById("error")
// //     let errorNode = document.createElement("p");
// //     errorNode.innerHTML = "Invalid Input"
// //     errorNode.setAttribute("id", "error");
// //     errorNode.style = "color : red; display : inline; margin-left: 20px;";
// //     document.getElementsByTagName("div")[0].appendChild(errorNode);
// // }

// // function removeError(){
// //     let error = document.getElementById("error")
// //     if(error){
// //         error.remove();
// //     }
// // }

//#5 
var elem = document.getElementsByTagName("details");

elem[0].onmouseover = function showContent() {
    elem[0].setAttribute('open', 'true'); }

    function hideContent(){
    
        this.open = false;}
        
    elem[0].addEventListener("mouseleave", hideContent);
 
// // //#6

var elem1 = document.getElementsByTagName("span");
var x="";
for (e of elem1){
    
    x+=e.innerHTML;
    
}
console.log(x);

//#7
var elem2 = document.getElementById("earth_time_check");

function onClick1(){
    var elem3 = document.getElementById("earth_time")
    elem3.innerHTML = Date();
    
}
elem2.addEventListener("click", onClick1);


//#9
var elem4 = document.getElementsByTagName("span");

function colorChange(){
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
          document.body.style.backgroundColor ="#"+Math.floor(Math.random() * 16000000);
        }
        return color;
      }
    
    
      
elem4[0].addEventListener("click", function(){
    setTimeout(colorChange, 3000)});

//#10

var inputN1 = document.getElementById("n1")
 var   inputN2 = document.getElementById("n2")
  var  output = document.getElementById("result");

function operation1(){
    operation = document.getElementById("operation").value;

    if(operation == 'Add'){
        document.getElementById("result").innerHTML = Number(n1.value)+Number(n2.value);
    }else if(operation == 'Subtract'){
        document.getElementById("result").innerHTML = Number(n1.value)-Number(n2.value);
    }else if(operation == 'Divide'){
        document.getElementById("result").innerHTML = Number(n1.value)/Number(n2.value);
    }else if(operation == 'Multiply'){
        document.getElementById("result").innerHTML = Number(n1.value)*Number(n2.value);
    }
}


document.getElementById("n1").addEventListener("change", operation1);
document.getElementById("n2").addEventListener("change", operation1);
document.getElementById("operation").addEventListener("change", operation1);


function operate1() {
    console.log("hi");
    if (operation.value == 'Add') {
        output.innerHTML = inputN1.value + inputN2.value;
        console.log("hi add");
    } else if (operation.value == 'Subtract') {
        output.innerHTML = inputN1.value - inputN2.value;
    } else if (operation.value == 'Divide') {
        output.innerHTML = inputN1.value / inputN2.value;
    } else if (operation.value == "Multiply") {
        output.innerHTML = inputN1.value * inputN2.value;
    }

    // function XYZ(){
    
    //     var x = getInputs(inputN1),
    //             y = getInputs(inputN2);
        
    //         if (isNaN(x) || isNaN(y)) {
    //             alert("Please input an integer value");
    //             return;
    // }}


inputN1.addEventListener("change", operate1);
inputN2.addEventListener("change", operate1);
output.addEventListener("change", operate1);
   
       

//#11
var walkTheDOM = function (node,func) {
    func(node);  //this will invoke the functionToInvoke from arg
    node = node.firstChild;
    while(node) {
        walkTheDOM(node,func);
        node = node.nextSibling;
    }
};    

var functionToInvoke = function(node) {
console.log(node.tagName);
};

walkTheDOM(document.body, functionToInvoke);}
