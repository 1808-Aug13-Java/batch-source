// JavaScript Assignment Interacting with the DOM
// > Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder
let google = document.getElementsByName("google");
google[0].setAttribute("href", "https://www.google.com/");
let twitter = document.getElementsByName("twitter");
twitter[0].setAttribute("href", "https://twitter.com/");
let slack = document.getElementsByName("slack");
slack[0].setAttribute("href", "https://slack.com/");
let javadocs = document.getElementsByName("javadocs");
javadocs[0].setAttribute("href", "https://docs.oracle.com/javase/8/docs/api/");

// Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)

document.getElementById("planet").options[3].disabled = true;

// Define a function alienText() which shows the hidden element displaying an alien message.
//When any planet other than Earth is selected, execute this function.
planet.addEventListener("click", function(){
    if(this.value !== "Earth"){
        alienText();
    }
});
function alienText(){
    var message = document.getElementsByTagName("p");
    for (psst of message){
        if(psst.attributes.hidden){
            alert(psst.textContent);
        }
    }
}
// When the submit button is pressed, get the values from all of the input into a new row in the table below.  
//Make sure no input is empty, check that first and last name are at least two letters each. Validate for valid phone number
// and email structure. This should continue to work for multiple entries and rows.

document.getElementById("form-sub").addEventListener("click", newSubmit);
function newSubmit() {
    let firstName = document.getElementById("firstname").value;         //Grabbing all the elements
    let lastName = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    let gender = document.querySelector('input[name = "gender"]:checked').value;
    let favcolor = document.getElementById("color").value;
    let activity = document.querySelector('input[type = "checkbox"]:checked').value;
    name = firstName + " " + lastName;                                  //merging together first and last name for a single column

    if (nameCheck(firstName, lastName) && emailCheck(email) && phoneCheck(phone) && bdayCheck(bday) && genderCheck(gender) &&favcolorTest(favcolor) && activityCheck(activity)){ 
        addRow(name, email, phone, bday, favcolor, gender, activity);
    } else {
        alert("Something went wrong, please check your input");                                         //displaying alert if the input fails and check
    }
}

function addRow(nameRow, emailRow, phoneRow, bdayRow, favcolorRow, genderRow, activitiesRow){
    let row = document.createElement("tr");
    let name = document.createElement("td");
    let email = document.createElement("td");
    let phone = document.createElement("td");
    let birthday = document.createElement("td");
    let favcolor = document.createElement("td");
    let gender  = document.createElement("td");
    let activities  = document.createElement("td");

    name.innerHTML = nameRow;
    email.innerHTML = emailRow;
    phone.innerHTML = phoneRow;
    birthday.innerHTML = bdayRow;
    favcolor.innerHTML = favcolorRow;
    gender.innerHTML = genderRow;
    activities.innerHTML = activitiesRow;

    row.appendChild(name);
    row.appendChild(email);
    row.appendChild(phone);
    row.appendChild(birthday);
    row.appendChild(favcolor);
    row.appendChild(gender);
    row.appendChild(activities);
    
    document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);     
}
function nameCheck(firstName, lastName) {                   //Everything below this is just checking for both any input
    if(firstName.length >= 2 && lastName.length >= 2) {       //and correct input for email and phone numbers
        return true;
    } else {
        return false;
    }
}
function emailCheck(email) {
    let emailCheck = new RegExp(/^.+@.+\..+$/);
    return emailCheck.test(email);
}
function phoneCheck(phone) {
    let phoneCheck = new RegExp(/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/);
        return phoneCheck.test(phone);

}
function bdayCheck(bday) {
    if(bday != null) {
        return true;
    } else {
        return false;
    }
}  
function favcolorTest(favcolor){
    if(favcolor != null){
        return true;
    } else {
        return false;
    }
} 
function genderCheck(gender) {
    if(gender != null) {
        return true;
    } else {
        return false;
    }
} 
function activityCheck(activity){
    if(activity != null){
        return true;
    } else {
        return false;
    }
} 
// Create a function openDetails() which opens the details element. 
//Invoke this function when the details’ summary is moused over. 
//The details should be hidden when the mouse is removed from the summary.
var summ= document.getElementsByTagName("summary");
summ[0].addEventListener("mouseover", invokeDetails);       //mouseover/out are great TIL
summ[0].addEventListener("mouseout", removeDetails);

function invokeDetails() {
    var deets = document.getElementsByTagName("details");
    deets[0].open = true;
}

function removeDetails() {
    var deets = document.getElementsByTagName("details");
    deets[0].open = false;
}

// Create a function that concatenates the inner HTML of all of the span elements and prints the results to the console.
function concat(){
    var allSpan = [];
    var element = document.querySelectorAll("span");
    element.forEach(span => allSpan.push(span.innerHTML));  //Brining them together
    console.log(allSpan.join(""));
}


// Create a function that displays the current time on earth in the span with id “earth_time”. 
//Invoke this function when “Earth time” button is clicked. 

var timeCheck = document.getElementById("earth_time_check");
var earthTime = document.getElementById("earth_time");

timeCheck.addEventListener("click", function(){
    earthTime.innerHTML = new Date().toLocaleTimeString();      //straight forward pulling locale time and displaying as earth time
});

// Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb 
//if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when their time buttons are clicked. 
//An orbital period for Mars is 687 Earth days. Using an external api to get the orbital period for Alpha Centauri Bb.
//(try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting this value using both AJAX 
//and the fetch API.

//no clue what to do


// Three seconds after a user clicks on the “Intergalactic Directory” heading, the background color should change to a random color. 
//Make sure this color is never black so we can still read our black text!
//(there are other dark colors it could change to where we also couldn’t see the text but it’s enough to just accomodate for
// a black background)
function rgbColors(){                      //Generate random colors using rgb values
    const redLight = randomizer(60, 255); //Keeping the lowest value capped at 60 avoids dark colors that could potentially show up
    const greenLight = randomizer(60, 255);
    const blueLight = randomizer(60, 255);
  
    document.body.style.backgroundColor = "rgb("+redLight+","+greenLight+","+blueLight+")";
  }
function randomizer(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
function timer() {
    setTimeout(rgbColors, 3000);
  }
  const headingNode = document.getElementsByTagName("h1")[0];
  headingNode.addEventListener("mouseover", timer);
  
// When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select.
//Display the result in the element with id result.
document.getElementById("operation").addEventListener("click", performCalc);
document.getElementById("n1").addEventListener("click", performCalc);      //establishing id
document.getElementById("n2").addEventListener("click", performCalc);

function performCalc(){
    let n1 = Number(document.getElementById("n1").value);
    let n2 = Number(document.getElementById("n2").value);
    if(isNaN(n1) && isNaN(n2))                  //making sure we're dealing with numbers
        return;
    else{
        let result;  
        let math = document.getElementById("operation").options, op;            
        op = math[math.selectedIndex].innerHTML;
        switch(op){                               //Handling the operations
            case "Add":
                result =  n1 + n2;
                break;
            case "Subtract":
                result = n1 - n2;
                break;
            case "Divide":
                result = n1 / n2;
                break;
            case "Multiply":
                result = n1 * n2;
        }
        
        document.getElementById("result").innerHTML = result;
    }
}
// Define function walkTheDom(node, func)
// 	This function should traverse every node in the DOM. 
// 	Use recursion. On each node, called func(node).
let walkTheDom = function(node,fun){
    func(node);
}

let func = function(node){
    if((node) && (node.hasChildNodes())){
        console.log(node);
        func(node.firstElementChild);
    }
    if((node) && (node.nextSibling)){
        console.log(node);
        func(node.nextSibling);
    }
    return;
}
walkTheDom(document.getElementsByTagName("body")[0], func);