
//Step 1: Set up the four links 
// document.getElementsByTagName("a")[0].setAttribute("href", "https://www.google.com");
// document.getElementsByTagName("a")[1].setAttribute("href", "https://twitter.com/?lang=en");
// document.getElementsByTagName("a")[2].setAttribute("href", "https://slack.com/");
// document.getElementsByTagName("a")[3].setAttribute("href", "https://www.oracle.com/");

document.getElementsByName("google")[0].href = "https://www.google.com";
document.getElementsByName("twitter")[0].href = "https://twitter.com/?lang=en";
document.getElementsByName("slack")[0].href = "https://slack.com/";
document.getElementsByName("javadocs")[0].href = "https://www.oracle.com/";

//Step 2: Disable the pluto planet of residenct option
document.getElementsByTagName("option")[3].setAttribute("disabled", "true");

//Step 3: Define a function alienText() which shows the hidden element displaying an alien message. 
// When any planet other than Earth is selected, execute this function.

function alientText(){
    if(document.getElementById("planet").value != "Earth")
    {
        for(i = 0; i<document.getElementsByTagName("p").length; i++)
        {      
            if(document.getElementsByTagName("p")[i].hidden)
            {
                console.log(document.getElementsByTagName("p")[i].innerText);
                document.getElementsByTagName("p")[i].hidden = false;
            }
        }
    }
    else
    {
        for(i = 0; i<document.getElementsByTagName("p").length; i++)
        {      
            if(!document.getElementsByTagName("p")[i].hidden)
            {
                console.log(document.getElementsByTagName("p")[i].innerText);
                document.getElementsByTagName("p")[i].hidden = true;
            }
        }
    }
}

document.getElementById("planet").addEventListener("change", alientText);

/*Step 4: When the submit button is pressed, get the values from all of the input into a new row in the table below.  
Make sure no input is empty, check that first and last name are at least two letters each. Validate for valid phone number and 
email structure. This should continue to work for multiple entries and rows.
*/

document.getElementById("form-sub").addEventListener("click", addRow);

function addRow(){
    
    let row = document.createElement("tr");
    let nameCell = document.createElement("td");
    let emailCell = document.createElement("td");
    let phoneCell = document.createElement("td");
    let birthdayCell = document.createElement("td");
    let colorCell = document.createElement("td");
    let genderCell = document.createElement("td");
    let activityCell = document.createElement("td");
    const activities = [];
    activities.push("<ul>");
    for(activity of document.getElementsByClassName("activity"))
    {
        activities.push(`
        <li> 
        ${activity.value}
        </li>`)
    }
    activities.push("</ul>");

    row.appendChild(nameCell);
    row.appendChild(emailCell);
    row.appendChild(phoneCell);
    row.appendChild(birthdayCell);
    row.appendChild(colorCell);
    row.appendChild(genderCell);
    row.appendChild(activityCell);

    document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);

    nameCell.innerText = document.getElementById("firstname").value + " " + document.getElementById("lastname").value;
    emailCell.innerText = document.getElementById("email").value; 
    phoneCell.innerText = document.getElementById("phone").value;
    birthdayCell.innerText = document.getElementById("bday").value;
    colorCell.innerText = document.getElementById("color").value; 
    genderCell.innerText = document.querySelector('input[name = "gender"]:checked').value;
    activityCell.innerHTML = activities.join(""); 
}

/* Create a function openDetails() which opens the details element. Invoke this function when the details’ summary is moused over.
 The details should be hidden when the mouse is removed from the summary.
*/

document.getElementsByTagName("details")[0].addEventListener("mouseover", openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseleave", closeDetails);

function openDetails(){
    document.getElementsByTagName('details')[0].open = true;
}

function closeDetails()
{
    document.getElementsByTagName('details')[0].open = false;
}

// Step 6: Create a function that concatenates the inner HTML of all of the span elements and prints the results to the console.

function concatHTML(){
    let spans = document.getElementsByTagName("span");
    let concatString = "";
    for(s of spans)
    {
        concatString += s.innerHTML;
    }
    console.log(concatString);
}

concatHTML();

// Step 7: Create a function that displays the current time on earth in the span with id “earth_time”. 
// Invoke this function when “Earth time” button is clicked. 

document.getElementById("earth_time_check").addEventListener("click", calculateEarthTime);

function calculateEarthTime(){
    now = new Date();
    document.getElementById("earth_time").innerText = now;
}


/* 
Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb if the onset of 
January 1st, 1970 occured at the same time. Invoke the respective functions when their time buttons are clicked. 
An orbital period for Mars is 687 Earth days. Using an external api to get the orbital period for Alpha Centauri Bb. 
(try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting this value using both AJAX and the 
fetch API.
*/


/*Step 9: Three seconds after a user clicks on the “Intergalactic Directory” heading, the background color should change to a 
random color. Make sure this color is never black so we can still read our black text! 
(there are other dark colors it could change to where we also couldn’t see the text but it’s enough to just accomodate for a 
black background)*/
document.getElementsByTagName("h1")[0].onclick = makeDelayed(idColorChange);

function makeDelayed(colorChange) {
    return function(event)
    {
      setTimeout( function()
      {
        colorChange(event);
      }, 3000);        
    };
}

function idColorChange(){
    let c = getRandomColor();
    while(c === "#000000")
    {
        c = getRandomColor();
    }
    document.getElementsByTagName("h1")[0].style.backgroundColor = c;
}

//Generates a random color
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

/* 
    Step 10: When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. 
    Display the result in the element with id result.
*/

document.getElementById("n2").addEventListener("change", calculate);

function calculate(){
    let num1 = document.getElementById("n1").value;
    let num2 = document.getElementById("n2").value;
    let operator = document.getElementById("operation").value;
    let result = document.getElementById("result");

    if(operator === "Add"){
        result.innerHTML = eval(num1) + eval(num2);
    }else if(operator === "Subtract"){
        result.innerHTML = num1 - num2;
    }else if(operator === "Multiply"){
        result.value = num1 * num2;
    }else if(operator === "Divide"){
        result.value = num1 / num2;
    }
}

/*
    Define function walkTheDom(node, func)
	This function should traverse every node in the DOM. 
	Use recursion. On each node, calle func(node).
*/

function walkTheDom(node, func){
    func(node)
    for(n  in node.childNodes)
    {
        n.walkTheDom(n, func);
    }
}

function testFunction(node)
{
    console.log(node);
}

walkTheDom(document.getElementsByTagName("div"), testFunction);