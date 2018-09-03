/*
JavaScript Assignment Interacting with the DOM
> Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder
*/

/*
1.  Make each link direct the user to its respective website (see id)
note to self: there are 4 links with '#' and they have no ids, only names
*/

// http://www.technothirsty.com/getelementbyid-vs-getelementsbyname-vs-getelementsbytagname/

// getElementsByName returns collection, so to specify, put the index of node #s starting @ 0
// Furthermore, the easiest way to modify the content of an HTML element is by using the innerHTML property.
var newGoogleLink = document.getElementsByName('google');
newGoogleLink[0].innerHTML = '<a href="https://www.google.com/">google</a>';

var newTwitterLink = document.getElementsByName('twitter');
newTwitterLink[0].innerHTML = '<a href="https://twitter.com/">twitter</a>';

var newSlackLink = document.getElementsByName('slack');
newSlackLink[0].innerHTML = '<a href="https://slack.com/">slack</a>';

var newJavadocsLink = document.getElementsByName('javadocs');
newJavadocsLink[0].innerHTML = '<a href="https://docs.oracle.com/javase/8/docs/api/">javadocs</a>';

/*
2.  Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
*/

// Loop to gett all options within <select id='planet'>...</select>
var optionNode = document.getElementById("planet").getElementsByTagName("option");
//console.log(optionNode);
for (var i = 0; i < optionNode.length; i++) {
  // lowercase comparison for case-insensitivity
  if (optionNode[i].value.toLowerCase() == "pluto") {
    optionNode[i].disabled = true;
  }
}

/*
3.  Define a function alienText() which shows the hidden element displaying an alien message. 
When any planet other than Earth is selected, execute this function.
*/

function alienText() {
  document.getElementsByTagName("p")[5].removeAttribute("hidden");
}

var isvisible = false;
var planetSelected = document.getElementById('planet');
planetSelected.addEventListener('click', () => {
  if (isvisible) {
    if(planetSelected.value === "Mars" || planetSelected.value === "Proxima Centauri b") { // show text when others are selected
      alienText();
    }
    else if (planetSelected.value === "Earth") { // hide it when Earth selected
      document.getElementsByTagName("p")[5].setAttribute("hidden", "hidden");
    }
    isvisible = false;
  } else {
    isvisible = true;
  }
});

/*
4.  When the submit button is pressed, get the values from all of the input into a new row in the 
table below. Make sure no input is empty, check that first and last name are at least two letters 
each. Validate for valid phone number and email structure. This should continue to work for multiple 
entries and rows.
*/

// check if valid email
function isValidEmail(str){
  //the string can be at most 64 + 1 + 255 characters long
  if (str.length > 320) {
      return false;
  }

  var patt = new RegExp("[0-9a-zA-Z!#\$\.%&'*\+\/=\?\^_`{\|}~-]*[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+@[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+\.[0-9a-zA-Z!#\$%&'*\+\/=\?\^_`{\|}~-]+");
  console.log("is valid email??:  " + patt.test(str));
  return patt.test(str);
}

function isValidTelephoneNum(str) {
		// Write an implementation for this method declaration
		str = str.trim(); // get rid of leading/trailing whitespace

		// base cases
		if (str == null || str.length <= 1) {
      console.log("false because too short or null")
			return false
    }
    
		var input = str.split(""); // returns array of Strings by dividing at spaces	
    
    console.log(input); 
    console.log("input.length:  " + input.length);

    var patt = new RegExp("[*,/:-~!-']+");

    if (patt.test(str)) {
      console.log("odd chars entered, false");
      return false;
    }

    var count = 0;
    var digit = 0;
    while (count <=input.length) {
      if (input[digit] != '(' || input[digit] != ')' || input[digit] != ' ' || input[digit] != '-' || input[digit] != '.') {
      count++;
      }
      digit++;
    }

    console.log("count:  " + count);

    if (count > 12 || count < 9) { // number too long or too short
      console.log("false because not enough nums or too many nums");
			return false;
    }
    
    console.log("valid #, true");
    return true;
  }

function addRow(name1, email, phone, birthday, favoriteColor, gender, activities){
  let row = document.createElement("tr");

  let cell1 = document.createElement("td");
  let cell2 = document.createElement("td");
  let cell3 = document.createElement("td");
  let cell4 = document.createElement("td");
  let cell5 = document.createElement("td");
  let cell6 = document.createElement("td");
  let cell7 = document.createElement("td");

  row.appendChild(cell1);
  row.appendChild(cell2);
  row.appendChild(cell3);
  row.appendChild(cell4);
  row.appendChild(cell5);
  row.appendChild(cell6);
  row.appendChild(cell7);

  cell1.innerHTML = name1;
  cell2.innerHTML = email;
  cell3.innerHTML = phone;
  cell4.innerHTML = birthday;
  cell5.innerHTML = favoriteColor;
  cell6.innerHTML = gender;
  cell7.innerHTML = activities;

  document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);
}

document.getElementById("form-sub").addEventListener("click", addNew);

let errorDisplayed = false;

function addNew(){

    // check length of name && validity of email
    if (document.getElementById("firstname").value.length >= 2 && document.getElementById("lastname").value.length >= 2 
    && isValidEmail(document.getElementById("email").value ) && isValidTelephoneNum(document.getElementById("phone").value) 
    && document.getElementById("bday").value !== null && document.getElementById("color").value != null 
    && document.querySelector('input[name = "gender"]:checked') && document.querySelector('input[type = "checkbox"]:checked')) {
      let name = document.getElementById("firstname").value + " " + document.getElementById("lastname").value;
      let email = document.getElementById("email").value;
      let phone = document.getElementById("phone").value;
      let birthday = document.getElementById("bday").value;
      let favoriteColor = document.getElementById("color").value;
      let gender = document.querySelector('input[name = "gender"]:checked').value;
      let activities = document.querySelector('input[type = "checkbox"]:checked').value;

      console.log(document.querySelector('input[type = "checkbox"]:checked'));

      // console.log(`name is ${name} and email is ${email} and fav color is ${favoriteColor}
      // and gender is ${gender} and activity is ${activities} and birthday is ${birthday}`);

      addRow(name, email, phone, birthday, favoriteColor, gender, activities);
    }
    // if length of name is NOT at least 2 letters, and email is NOT valid, do the following...
    else {
      alert("Invalid input.");
    }

}

/*
5.  Create a function openDetails() which opens the details element. Invoke this function when the 
details’ summary is moused over. The details should be hidden when the mouse is removed from the summary.
*/

function openDetails() {
  document.getElementsByTagName("details")[0].open = true;
}

function closeDetails() {
  document.getElementsByTagName("details")[0].open = false;
}

document.getElementsByTagName("details")[0].addEventListener("mouseover", openDetails);

document.getElementsByTagName("details")[0].addEventListener("mouseout", closeDetails);

/*
6.  Create a function that concatenates the inner HTML of all of the span elements and prints the results to 
the console.
*/

// note:  The <span> tag is used to group inline-elements in a document.
//        The <span> tag provides no visual change by itself.
//        The <span> tag provides a way to add a hook to a part of a text or a part of a document.
//https://www.w3schools.com/jsref/dom_obj_span.asp

// collection of all things with span tag
var textBlock = document.getElementsByTagName("span"); 

var completeConcat = "";
for (var i = 0; i < textBlock.length; i++) {
  completeConcat += textBlock[i].innerHTML + " ";
}
console.log(completeConcat);

/*
7.  Create a function that displays the current time on earth in the span with id “earth_time”. Invoke this 
function when “Earth time” button is clicked. 
*/

//https://www.w3schools.com/js/js_dates.asp
function displayCurrentEarthTime() {
  var d = new Date();
  document.getElementById("earth_time_check").innerHTML = d;
}

document.getElementById("earth_time_check").addEventListener("click", displayCurrentEarthTime);

/*
8.  Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb 
if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when their time 
buttons are clicked. An orbital period for Mars is 687 Earth days. Using an external api to get the orbital 
period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) Provide an 
implementation for getting this value using both AJAX and the fetch API.
*/

function calculateTimeMars() {
  var startingDate = new Date('1970-01-01T00:00:00');
  var endingDate = new Date();
  //var newYear = endingDate.getFullYear(); // milliseconds -> minutes = # ms /60000
  // minutes -> days = # min / 1440
  var timePassed = ((endingDate.getTime() - startingDate.getTime()) / 60000) / 1440 / 687;

  console.log("the starting date:  " + startingDate);
  console.log("the ending date:  " + endingDate);
  console.log("the time passed: " + timePassed);
  document.getElementById("mars_time_check").innerHTML = timePassed;
}

document.getElementById("mars_time_check").addEventListener("click", calculateTimeMars);

/*
9.  Three seconds after a user clicks on the “Intergalactic Directory” heading, the background color should 
change to a random color. Make sure this color is never black so we can still read our black text! (there 
are other dark colors it could change to where we also couldn’t see the text but it’s enough to just 
accomodate for a black background)
*/

// get random # helper function
function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// function that waits before calling the function that actually changes the bg color
function wait() {
  setTimeout(changeBGColor, 3000);
}

// function that actually changes the bg color to a random color
function changeBGColor(){

  // generate random colors using rgb values
  var r = getRandomInt(1, 255); // nums > 0 so never black
  var g = getRandomInt(1, 255);
  var b = getRandomInt(1, 255);

  document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
}

var headingNode = document.getElementsByTagName("h1")[0];
headingNode.addEventListener("click", wait);

/*
10. When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the 
select. Display the result in the element with id result.
*/

//FIX ME CHECK FOR GOOD #s

function doOperation(operationSelectedValue) {
  var patt = new RegExp("\D+"); // non-Digit
  // if (patt.test(document.getElementById("n1").value.toString()) || patt.test(document.getElementById("n2").value.toString())) {
  //   console.log("invalid #");
  //   alert("Invalid input.");
  // }

  if (isNaN(document.getElementById("n1").value) || isNaN(document.getElementById("n2").value)) {
    alert("Invalid input.");
  }

  //console.log("is valid number??:  " + patt.test(str));

  var firstNum  = parseInt(document.getElementById("n1").value);
  var secondNum = parseInt(document.getElementById("n2").value);
  console.log(firstNum);
  console.log(secondNum);

  let finalResult = 0;

  if(operationSelectedValue == "Add") {
    finalResult = firstNum + secondNum;
    console.log("result from adding:  " + finalResult);
  }
  if(operationSelectedValue == "Subtract") {
    finalResult = firstNum - secondNum;
  }
  if(operationSelectedValue == "Divide") {
    finalResult = firstNum / secondNum;
  }
  if(operationSelectedValue == "Multiply") {
    finalResult = firstNum * secondNum;
  }

  console.log("operationSelected " + operationSelectedValue);

  console.log(finalResult);

  document.getElementById("result").innerHTML = finalResult;

  //return finalResult;
}

function myFuncToCallOperation() {
var operationSelected = document.getElementById('operation');
operationSelected.addEventListener('keydown', doOperation(operationSelected.value));
}

var btn = document.createElement("BUTTON");
var t = document.createTextNode("Perform Operation");
btn.appendChild(t); 
document.body.appendChild(btn).addEventListener("click", myFuncToCallOperation);

/*
11. Define function walkTheDom(node, func)
This function should traverse every node in the DOM. 
Use recursion. On each node, calle func(node).
*/

function walkTheDom(node, func) {

    func(node);
    node = node.firstChild;
    while (node) {
        walkTheDOM(node, func);
        node = node.nextSibling;
    } // end while
  }