function alienText(){ 

    var b = document.createAttribute("hidden");

    if(document.getElementById("planet").value !="Earth"){
        document.getElementsByTagName("p")[5].removeAttribute("hidden");
    } else if(document.getElementById("planet").value =="Earth"){
        document.getElementsByTagName("p")[5].setAttributeNode(b);
    }
}

document.getElementById("planet").addEventListener("click", alienText);

function addRow(firstname, lastname, email, phone, bday, gender, color, activity){
    let row = document.createElement("tr");

    let cell1 = document.createElement("td");
    let cell2 = document.createElement("td");
    let cell3 = document.createElement("td");
    let cell4 = document.createElement("td");
    let cell5 = document.createElement("td");
    let cell6 = document.createElement("td");
    let cell7 = document.createElement("td");
    cell7.appendChild(document.createElement("ul"));

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);

    for(a of activity){
        if(a.checked == true){
            let cell = document.createElement("li");
            cell.innerHTML = a.defaultValue;
            cell7.appendChild(cell);
        }
    }

    // if(id===undefined){
    //     id = count++;
    // }
    cell1.innerHTML = firstname +" " + lastname;
    //cell2.innerHTML = lastname;
    cell2.innerHTML = email
    cell3.innerHTML = phone;
    cell4.innerHTML = bday;
    cell5.innerHTML = color;
    cell6.innerHTML = gender;
    //cell7.innerHTML = activity;


    console.log(row);
    console.log(document.getElementById("rows"));
    document.getElementsByTagName("tbody")[0].appendChild(row);
    
}

function addNew(){
    let gender = "";
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    for (a of document.getElementsByName("gender")){
        if(a.checked == true){
            gender = a.defaultValue;
            break;
        }
    }
    //let gender = document.getElementsByName("gender");
    let activity = document.getElementsByClassName("activity");
    let color = document.getElementById("color").value;

    console.log(activity);


    var regex1 = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
    var regex2 = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    // console.log(`name is ${name} and major is ${major}`);

    // if((name && major) != ""){
    //     addRow(name, major);
    // }else{
    //     document.createElement("p");
    // }

    if((firstname && lastname && email && phone && bday && gender && color && activity && regex1.test(phone) && regex2.test(email))){
        if(errorDisplayed){
            removeError();
            errorDisplayed = false;
        }
        addRow(firstname, lastname, email, phone, bday, gender, color, activity);
    } else {
        if(!errorDisplayed){
            displayError();
            errorDisplayed = true;
        }
    }

}

function displayError(){
    let errorNode = document.createElement("p");
    errorNode.innerHTML = "Invalid Input"
    errorNode.setAttribute("id", "error");
    errorNode.style = "color : red; display : inline; margin-left: 20px;";
    document.getElementsByTagName("div")[3].appendChild(errorNode);
}

function removeError(){
    let error = document.getElementById("error")
    if(error){
        error.remove();
    }
}

var a = document.createAttribute("open");

function openDetails(){

    document.getElementsByTagName("details")[0].setAttributeNode(a);

}

function closeDetails(){

    document.getElementsByTagName("details")[0].removeAttributeNode(a);

}

let errorDisplayed = false;

//document.getElementsByTagName("p")[3].addAttribute("hidden")

document.getElementById("form-sub").addEventListener("click", addNew);

document.getElementsByTagName("summary")[0].addEventListener("mouseenter", openDetails);
document.getElementsByTagName("summary")[0].addEventListener("mouseout", closeDetails);

function concat(){
    spanElements = document.getElementsByTagName("span");
    text = ""

    for(elements of spanElements){

        text = text + elements.innerHTML;

    }

    console.log(text);

}

function checkTime(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }
  
  function earthTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('earth_time').innerHTML = h + ":" + m + ":" + s;
    t = setTimeout(function() {
      earthTime()
    }, 500);
  }

  document.getElementById("earth_time_check").addEventListener("click", earthTime);



function marsTime(){

    var d = new Date();
    document.getElementById("mars_time").innerHTML = (d.getTime())/(1.027491252*1000*60*60*24);
    var t = setTimeout(function() {
        marsTime()
      }, 500); 

}

function random_bg_color() {

    event.stopPropagation();   

    var x = Math.floor(Math.random() * 256 + .01);
    var y = Math.floor(Math.random() * 256 + .01);
    var z = Math.floor(Math.random() * 256 + .01);
    var bgColor = "rgb(" + x + "," + y + "," + z + ")";
  
    setTimeout(function(){document.body.style.background = bgColor; }, 3000);
    
    
    
    }

document.getElementsByTagName("h1")[0].addEventListener("click", 
    function(event){random_bg_color();}
);



document.getElementById("mars_time_check").addEventListener("click", marsTime);

function calculator(){
    var operation = document.getElementById("operation").value;
    var result;
    n1 = parseFloat(document.getElementById("n1").value);
    n2 = parseFloat(document.getElementById("n2").value);

    switch(operation){
        case "Add":
            result = n1+n2;
            break;
        case "Multiply":
            result = n1*n2;
            break;
        case "Divide":
            result = n1/n2;
            break;
        case "Subtract":
            result = n1-n2;
            break;
    }

    document.getElementById("result").innerHTML = result;

}

document.getElementById("calc-sub").addEventListener("click", calculator);

function walkTheDom(node, func) {
    var children = node.childNodes;
    for (var i = 0; i < children.length; i++)  // Children are siblings to each other
        walkTheDom(children[i], func);
    func(node);
 }

function func(x){
    console.log(x);
}

//walkTheDom(document, func);
