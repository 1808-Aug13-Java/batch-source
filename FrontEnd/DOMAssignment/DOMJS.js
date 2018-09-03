

//1. link all of the links for part 1 using JS
document.getElementsByName("google")[0].href="https://www.google.com/";
document.getElementsByName("twitter")[0].href = "https://twitter.com/";
document.getElementsByName("slack")[0].href = "https://slack.com/ ";
document.getElementsByName("javadocs")[0].href = "https://docs.oracle.com/javase/8/docs/api/ ";


//2. remove the Pluto residency option 
var plutoOption= document.getElementById("planet");//retreives parent node 
var toBeRemoved=plutoOption.getElementsByTagName("option");//retrieves list of child nodes
plutoOption.removeChild(toBeRemoved[3]); // removes the fourth child, Pluto from the DOM

//3. Display hidden element function

document.getElementById("planet").setAttribute("onchange", "alienText()");
function alienText(){
    if (document.getElementById("planet").value != "Earth") {
    //display hidden element when anything other than earth is selected
    document.getElementsByTagName("p")[5].removeAttribute("hidden");
    }
    else{
        document.getElementsByTagName("p")[5].setAttribute("hidden","");   
    }
}


//4. When the submit button is pressed, 
//get the values from all of the input into a new row in the table below.
// Make sure no input is empty, check that first and last name are at least two letters each. 
// Validate for valid phone number and email structure. 
// This should continue to work for multiple entries and rows.
var formInputs= document.getElementsByTagName("form");
var inputsArray= formInputs[0].getElementsByTagName("input"); 
inputsArray[0].setAttribute("required","");
inputsArray[0].setAttribute("title", "This field cannot be blank");
inputsArray[1].setAttribute("required", "");
inputsArray[1].setAttribute("title", "This field cannot be blank");
inputsArray[2].setAttribute("required", "");
inputsArray[2].setAttribute("title", "This field cannot be blank");
inputsArray[3].setAttribute("required", "");
inputsArray[3].setAttribute("title", "This field cannot be blank");
inputsArray[4].setAttribute("required", "");
inputsArray[4].setAttribute("title", "This field cannot be blank");
inputsArray[5].setAttribute("checked", "");
inputsArray[9].setAttribute("checked", "");


//submit button requirements

var userRegex= /\S\S/;
var userMax= /\*/;

document.getElementsByTagName("button")[0].addEventListener("click",newRow);
var activities = [];
var checks = document.getElementsByClassName("activity");


function newRow(){
    var firstName =inputsArray[0].value;
    var email= inputsArray[2].value;
    var phone=inputsArray[3].value;
    var bday = new Date(inputsArray[4].value);
    var printBday=bday.toLocaleDateString(); //right date representation
    var favColor= inputsArray[8].value;
    var gender='';
    genderSelect();
    function genderSelect(){
        if (document.getElementsByName("gender")[0].checked){
            gender= document.getElementsByName("gender")[0].value;
        }
        if (document.getElementsByName("gender")[1].checked){
            gender = document.getElementsByName("gender")[1].value;
        }
        else{
            gender = document.getElementsByName("gender")[2].value;
        }
    }

    var parent=document.getElementsByTagName("tbody");
    var newChild=document.createElement("tr");
    newChild.setAttribute("scope","row");
    newChild.appendChild(document.createElement("td"));
    newChild.children[0].innerHTML=firstName; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[1].innerHTML = email; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[2].innerHTML = phone; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[3].innerHTML = printBday; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[4].innerHTML = favColor; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[5].innerHTML = gender; 
    newChild.appendChild(document.createElement("td"));
    newChild.children[6].appendChild(document.createElement("ul"));//creates empty element list
   //newChild.children[6].children[0] is the unordered list node
   //All list items (li) should be appended to this child
    
    for (var i=0; i<checks.length;i++) {
        if (checks[i].checked) {
         activities.push(checks[i].value);
            //increase the length of array for each checked box
        }
    }
    for (var j=0;j<activities.length;j++){
        newChild.children[6].children[0].appendChild(document.createElement("li"));
        newChild.children[6].children[0].children[j].innerHTML = activities[j];
    }
    activities=[];//reset the activites arrays
    parent[0].appendChild(newChild); //new child is appended to the table
    //new child will always be appended at the end of the list

}


//5. 
document.getElementsByTagName("summary")[0]. addEventListener("mouseover",openDetails);
document.getElementsByTagName("summary")[0].addEventListener("mouseout", closeDetails);

function openDetails(){
    // console.log("this is working");
    document.getElementsByTagName("details")[0].setAttribute("open","");
}
function closeDetails(){
    document.getElementsByTagName("details")[0].removeAttribute("open","");
}

//6.
// Create a function that concatenates the inner HTML of all of the span elements 
// and prints the results to the console.

function spanConcat(){
    var spanContent = document.getElementsByTagName("span");
    var spanPrint="";
    for (var i=0;i<spanContent.length; i++){
        spanPrint= spanPrint.concat(spanContent[i].innerHTML);    
    }
    console.log(spanPrint);
    // return spanPrint;
}


// Create a function that displays the current time on earth
// in the span with id “earth_time”. h4 header 
//Invoke this function when “Earth time” button is clicked. 

document.getElementById("earth_time_check").addEventListener("click",earthTime )
function earthTime(){
   var d= document.getElementById("earth_time");
   var earth = new Date;
   d.innerHTML=earth.toTimeString();
}

document.getElementById("mars_time_check").addEventListener("click",marsTime);
function marsTime(){
    var mars=document.getElementById("mars_time");
    var marsTime=new Date;
    var seconds = marsTime.getTime() / 1000;
    var minutes = seconds /= 60;
    var hours = minutes / 60;
    var days = hours / 24;
    var years = (days / 687)+1970;
    mars.innerHTML = `Mars time is : ${years} years`;

}



// Create two other functions which calculates and 
// displays the time passed on Mars and Alpha Centauri Bb if
//  the onset of January 1st, 1970 occured at the same time.
// Invoke the respective functions when their time buttons are clicked.
// An orbital period for Mars is 687 Earth days.Using an external api to get the orbital period for Alpha Centauri Bb.
//  (try http://www.astropical.space/astrodb/apiref.php) 
// Provide an implementation for getting this value using both AJAX and the fetch API.
let url = "http://www.astropical.space/astrodb/api-exo.php?which=name&limit=prox&format=json";

function sendAjaxGet(url, callback) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status == 200) {
            callback(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function alphaCentauriTime(xhr){
    let alphaTime=JSON.parse(xhr.responseText);
    var acb= document.getElementById("acb_time");
    var orbitalP = alphaTime.exoplanets[0].per;

    var timeCalc=new Date;
    var seconds = timeCalc.getTime() / 1000;
    var minutes=seconds/=60;
    var hours=minutes/60;
    var days=hours/24;
    var years=(days/orbitalP)+1970;
    acb.innerHTML = `Alpha Centauri B time is : ${years} years`;

}

function acbTime(){
    sendAjaxGet(url,alphaCentauriTime)
}
document.getElementById("acb_time_check").addEventListener("click",acbTime);

//9. Three seconds after a user clicks on the “Intergalactic Directory” heading, 
// the background color should change to a random color. 
// Make sure this color is never hbblack so we can still read our black text! 
// (there are other dark colors it could change to where we also couldn’t see the text but
//  it’s enough to just accomodate for a black background)

document.getElementsByTagName("span")[0].addEventListener("click",colorChange);
function colorChange(){
    // var oldColor= document.getElementsByTagName("script");
    
var randomColor=getcolor();
    var newColor= document.createElement("style");
    newColor.innerHTML = `body{ background-color: #${randomColor} ;
    }`;
    

    var backcolor=document.getElementsByTagName("head")[0];
    backcolor.appendChild(newColor);
}

function getcolor() {
    var color = "";
    var options = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    for (var i = 0; i < 6; i++)
        color += options.charAt(Math.floor(Math.random() * color.length));

    return color;
}

//calculator




