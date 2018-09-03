window.onload = function (){
    links();
    disablePluto();
    alienText();
    innerCat();
   
}
/*
JavaScript Assignment Interacting with the DOM
> Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder

Make each link direct the user to its respective website (see id)
*/
function links(){
    document.getElementsByName("google")[0].innerHTML = "Google";
    document.getElementsByName("google")[0].href = "https://www.google.com";
    document.getElementsByName("twitter")[0].innerHTML = "Twitter";
    document.getElementsByName("twitter")[0].href = "https://twitter.com";
    document.getElementsByName("slack")[0].innerHTML = "Slack";
    document.getElementsByName("slack")[0].href = "https://slack.com";
    document.getElementsByName("javadocs")[0].innerHTML = "Javadocs";
    document.getElementsByName("javadocs")[0].href = "https://javadocs.com";
}

/*
Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
*/
function disablePluto(){
    document.getElementById("planet").options[3].disabled = true;
}

/*
Define a function alienText() which shows the hidden element displaying an alien message. 
When any planet other than Earth is selected, execute this function.
*/
function alienText(){
   document.getElementById("planet").addEventListener("click", function()
   {
        let planet = document.getElementById("planet");
        let selected = (planet.options[planet.selectedIndex].text);
        if(selected != "Earth") {
            document.getElementsByTagName("p")[5].removeAttribute("hidden");
        }
   }
)}

/*
When the submit button is pressed, get the values from all of the input into a new row in the table below. 
Make sure no input is empty, check that first and last name are at least two letters each. 
Validate for valid phone number and email structure. This should continue to work for multiple entries and rows.
*/

document.getElementById("form-sub").addEventListener("click", addNew);
let name = "";

function addNew() {
    let fname = document.getElementById("firstname").value;
    let lname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    let gender = document.querySelector('input[name = "gender"]:checked').value;
    let favcolor = document.getElementById("color").value;
    let activity = document.querySelector('input[type = "checkbox"]:checked').value;

    name = fname + " " + lname;

    if (nameTest(fname, lname) && emailTest(email) && phoneNumTest(phone) && bdayTest(bday) && genderTest(gender) &&favcolorTest(favcolor) && activityTest(activity)){ 
        addRow(name, email, phone, bday, favcolor, gender, activity);
    } else {
        alert("Invalid input");
    }
}

function addRow(n, e, ph, bday, fc, g, a){
    let row = document.createElement("tr");
    let name = document.createElement("td");
    let email = document.createElement("td");
    let phone = document.createElement("td");
    let birthday = document.createElement("td");
    let favcolor = document.createElement("td");
    let gender  = document.createElement("td");
    let activities  = document.createElement("td");

    row.appendChild(name);
    row.appendChild(email);
    row.appendChild(phone);
    row.appendChild(birthday);
    row.appendChild(favcolor);
    row.appendChild(gender);
    row.appendChild(activities);

    name.innerHTML = n;
    email.innerHTML = e;
    phone.innerHTML = ph;
    birthday.innerHTML = bday;
    favcolor.innerHTML = fc;
    gender.innerHTML = g;
    activities.innerHTML = a;

    document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);     
}

function nameTest(fname, lname) {
    if(fname.length > 1 && lname.length > 1) {
        return true;
    } else {
        return false;
    }
}

function emailTest(email) {
    let emailCheck = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);
    return emailCheck.test(email);
}

function phoneNumTest(phone) {
    let phoneCheck = new RegExp(/^\d{10}$/);
        return phoneCheck.test(phone);

}

function bdayTest(bday) {
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
function genderTest(gender) {
    if(gender != null) {
        return true;
    } else {
        return false;
    }
} 
function activityTest(activity){
    if(activity != null){
        return true;
    } else {
        return false;
    }
} 

/*
Create a function openDetails() which opens the details element. Invoke this function when the details’ 
summary is moused over. The details should be hidden when the mouse is removed from the summary.
*/

function openDetails(){
    document.getElementsByTagName("details")[0].open = true;
}

function closeDetails(){
    document.getElementsByTagName("details")[0].open = false;
}
document.getElementsByTagName("details")[0].addEventListener("mouseover", openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseout", closeDetails);

/*
Create a function that concatenates the inner HTML of all of the span elements and prints the
 results to the console.
*/

function innerCat(){
    let htmlspans = document.getElementsByTagName("span");
    let catTime = "";
    for(i = 0; i < htmlspans.length; i++) {
        catTime += htmlspans[i].innerHTML + " ";
    }
    console.log(catTime);
}

/*
Create a function that displays the current time on earth in the span with id “earth_time”. 
Invoke this function when “Earth time” button is clicked. 
*/
function earthTime(){
    let earthtime = new Date();
    document.getElementById("earth_time").innerHTML = earthtime;
}
document.getElementById("earth_time_check").addEventListener("click", earthTime);

/*
Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri 
Bb if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when 
their time buttons are clicked. An orbital period for Mars is 687 Earth days. Using an external api to 
get the orbital period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) 
Provide an implementation for getting this value using both AJAX and the fetch API.
*/
let baseUrl = "http://www.astropical.space/astrodb/";

document.getElementById("acb_time_check").addEventListener("click", acbTime);

function acbTime(){
    sendAjaxGet(baseUrl, displayacbTime); //don't forget to add the real input to the baseUrl
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200) {
            callback(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayacbTime(){
    document.getElementById("acb_time").innerHTML = "I could not get " +
    "the API to work, but I did find this  <a href = 'https://www.exploratorium.edu/ronh/age/'>cool resource</a>";
}

document.getElementById("acb_time_check").addEventListener("click", displayacbTime);

function marsTime(){
    let startDate = new Date('1970-01-01T00:00:00');
    let marsNow = new Date();
    let earthNow = marsNow.getTime() / 60000 / 1440 / 365;

    passedTime = (marsNow.getTime() - startDate.getTime()) / 60000 / 1440 / 687;
    document.getElementById("mars_time").innerHTML = "On earth, " + earthNow.toFixed(2) + " years have passed."
        + " On Mars, " + passedTime.toFixed(2) + " years have passed."; 
}

document.getElementById("mars_time_check").addEventListener("click", marsTime);
/*
Three seconds after a user clicks on the “Intergalactic Directory” heading, the 
background color should change to a random color. Make sure this color is never black so we can still read our 
black text! (there are other dark colors it could change to where we also couldn’t see the text but it’s enough to 
just accomodate for a black background)
*/
document.querySelector("h1").addEventListener("click", function() {
    setTimeout(randoColor, 3000);
});

function randoColor(){
   let r = randoInt(1, 255);
   let g = randoInt(1, 255);
   let b = randoInt(1, 255);

   document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";

}

function randoInt(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}

/*
When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. 
Display the result in the element with id result.
*/
document.getElementById("n1").addEventListener("input", calculator);
document.getElementById("n2").addEventListener("input", calculator);
document.getElementById("operation").addEventListener("change", calculator);
function calculator(){
    let display = document.getElementById("result");
    let operation = document.getElementById("operation");
    let n1 = document.getElementById("n1");
    let n2 = document.getElementById("n2");
    if (n1.value.search(/\D/g) > -1)
        n1.value = "";
    if (n2.value.search(/\D/g) > -1)
        n2.value = "";   
    
    if (n1.value == "" ||  n2.value == "")
        display.innerHTML = "Both fields must have a valid number";
    else
        switch(operation.value){
            case "Add":
            display.innerHTML = eval(n1.value) + eval(n2.value);
                break;
            case "Subtract":
                display.innerHTML = n1.value - n2.value;
                break;
            case "Divide":
                if (n2.value == 0)
                    alert("Cannot divide by 0");
                else
                    display.innerHTML = n1.value / n2.value;
                break;
            case "Multiply":
            display.innerHTML = n1.value * n2.value;
                break;
            default:
            display.innerHTML = "You somehow got past the dropdown options."
        }
}

/*
Define function walkTheDom(node, func)
	This function should traverse every node in the DOM. 
	Use recursion. On each node, calle func(node).
*/
function walkTheDom(node, func) {

    func(node);
    node = node.firstChild;
    while (node) {
        walkTheDOM(node, func);
        node = node.nextSibling;
    }
} 
