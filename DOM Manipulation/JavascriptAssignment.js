document.getElementById("firstname").setAttribute("placeholder", "Please enter your first name. (Must be longer than 1 letter)");
document.getElementById("lastname").setAttribute("placeholder", "Please enter your last name. (Must be longer than 1 letter)");
document.getElementById("email").setAttribute("placeholder", "myemail@example.com");
document.getElementById("phone").setAttribute("placeholder", "(xxx) xxx-xxxx or xxxxxxxxxxx");

document.getElementsByClassName("activity")[1].setAttribute("value", "stamp collecting");
document.getElementsByClassName("activity")[3].setAttribute("value", "underwater basket weaving");

document.getElementsByName("gender")[0].setAttribute("value", "Male");
document.getElementsByName("gender")[1].setAttribute("value", "Female");
document.getElementsByName("gender")[2].setAttribute("value", "Other");

let visitLinks = document.getElementsByTagName("a");
visitLinks[0].setAttribute("href", "https://www.google.com");
visitLinks[1].setAttribute("href", "https://twitter.com/");
visitLinks[2].setAttribute("href", "https://slack.com/");
visitLinks[3].setAttribute("href", "https://docs.oracle.com/en/");

let selectOptions = document.getElementsByTagName("option");
selectOptions[3].setAttribute("disabled", "");

let inputOptions = document.getElementsByTagName("input");
inputOptions[5].checked = true;

let errorDisplayed = false;
function getFirstName() {
    let firstName = document.getElementById("firstname").value;
    if(firstName == "" || firstName.length < 2) {
        document.getElementById("firstname").style.borderColor = "red";
    } else {
        document.getElementById("firstname").style.borderColor = "lightgray";
        return firstName;
    }
}

function getLastName() {
    let lastName = document.getElementById("lastname").value;
    if(lastName == "" || lastName.length < 2) {
        document.getElementById("lastname").style.borderColor = "red";
    } else {
        document.getElementById("lastname").style.borderColor = "lightgray";
        return lastName;
    }
}

function getEmail() {
    let email = document.getElementById("email").value;
    if((email != "") && (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))) {
        document.getElementById("email").style.borderColor = "lightgray";
        return email;
    } else {
        document.getElementById("email").style.borderColor = "red";
    }
}

function getPhone() {
    let phone = document.getElementById("phone").value;
    let onlyNumbers = phone.toString().split(/\D/);
    let digits = "";
    //onlyNumber = onlyNumbers
    for(i = 0; i < onlyNumbers.length; i++) {
        if(!(isNaN(onlyNumbers[i]))) {
            digits += onlyNumbers[i];
        }
    }
    if((digits != "") && (digits.length == 10)) {
        document.getElementById("phone").style.borderColor = "lightgray";
        return digits;
    } else {
        document.getElementById("phone").style.borderColor = "red";
    }
}

function getBday() {
    var options = {year: 'numeric', month:'2-digit', day: '2-digit'};
    let bday = document.getElementById("bday").value;
    console.log(bday);
    if(bday == "") {//&& /(0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])[- \/.](19|20)\d\d/.test(bday)) {
        document.getElementById("bday").style.borderColor = "red";
    } else {
        document.getElementById("bday").style.borderColor = "lightgray";
        bday = new Date(bday);
        bday.setDate(bday.getDate() + 1);
        bday = bday.toLocaleDateString("en-US", options);
        return bday;
    }
}

function getGender() {
    let gender = document.getElementsByName("gender")
    for(i = 0; gender.length; i++) {
        if(gender[i].checked) {
            return gender[i].value;
        }
    }
}

function getColor() {
    let color = document.getElementById("color").value;
    return color;
}

function getActivities() {
    var checkValue = [];
    var inputElements = document.getElementsByClassName("activity");
    for(var i=0; i < inputElements.length; i++) {
        if(inputElements[i].checked) {
            checkValue.push(inputElements[i].value);
        }
    }
    return checkValue;
}

document.getElementById("form-sub").onclick = function() {
    let firstName = getFirstName();
    let lastName = getLastName();
    let email = getEmail();
    let phone = getPhone();
    let bDay = getBday();
    let planets = document.getElementById("planet");
    let gender = getGender();
    let color = getColor();
    let activities = getActivities();

    console.log(firstName, lastName, email, phone, bDay, gender, color, activities);
    //let gender = document.getElementsByName("gender");
    if(firstName && lastName && email && phone && bday) {
        addRow(firstName, email, phone, bDay, color, gender, activities);
    } else {
            alert(`Please ensure that all marked fields are corrected.
            \nRemember first and last names must be more than one letter
            \nPhone numbers should be in the (xxx) xxx-xxxx or xxxxxxxxxxx format
            \nEmails should be in the 'myemail@example.com' format`);   
        }
    //console.log(planets.options[planets.selectedIndex].text);
    if((planets.options[planets.selectedIndex].text) != "Earth") {
        alienText();
    } else {
         wordsOfWisdom = document.getElementsByTagName("p");
         wordsOfWisdom[5].hidden = true;
    }
}

function alienText() {
    var wordsOfWisdom = document.getElementsByTagName("p");
    wordsOfWisdom[5].hidden = false;
}

function addRow(firstName, email, phone, bDay, color, gender, activities) { 
    const listOfActivities = [];
    listOfActivities.push("<ul>");
    for(activity of activities) {
        listOfActivities.push(`
            <li>
                ${activity}
            </li>
        `);
    }
    listOfActivities.push("</ul>");
    
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

    cell1.innerHTML = firstName;
    cell2.innerHTML = email;
    cell3.innerHTML = phone;
    cell4.innerHTML = bDay;
    cell5.innerHTML = color;
    cell6.innerHTML = gender;
    cell7.innerHTML = listOfActivities.join("");

    document.getElementsByTagName("table")[0].appendChild(row);
}

var detailSummary = document.getElementsByTagName("details");
// document.getElementsByTagName("summary").onmouseover = function() {
//     openDetails();
// }
detailSummary[0].addEventListener("mouseenter", openDetails);
function openDetails() {
    detailSummary[0].open = true;
}

detailSummary[0].addEventListener("mouseleave", closeDetails);
function closeDetails() {
    detailSummary[0].open = false;
}

concatSpan();

function concatSpan(){
    let spanString = "";
    let canOfSpan = document.getElementsByTagName("span");
    for(i = 0; i < canOfSpan.length; i++) {
        spanString += canOfSpan[i].innerHTML;
    }
    console.log(spanString);
}

document.getElementById("earth_time_check").onclick = function earthTime() {
    var today = new Date();
    console.log(today);
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    console.log(time);
    document.getElementById("earth_time").innerHTML = time;
}

document.getElementById("n1").setAttribute("type", "number");
document.getElementById("n2").setAttribute("type", "number");

// let selectOptions = document.getElementsByTagName("option");
// selectOptions[3].setAttribute("disabled", "");

document.getElementById("operation").addEventListener("change", spaceMath);
document.getElementById("n1").addEventListener("input", spaceMath);
document.getElementById("n2").addEventListener("input", spaceMath);
function spaceMath() {
    let operations = document.getElementById("operation");
    let operand1 = document.getElementById("n1");
    let operand2 = document.getElementById("n2");
    switch(operations.value) {
        case "Add":
            if(operand1.value == "") {
                operand1 = 0;
            }
            if(operand2.value == "") {
                operand2 = 0;
            }
            document.getElementById("result").innerHTML = eval(operand1.value) +eval(operand2.value);
            break;
        case "Subtract":
            if(operand1.value == "") {
                operand1 = 0;
            }
            if(operand2.value == "") {
                operand2 = 0;
            }
            document.getElementById("result").innerHTML = eval(operand1.value) - eval(operand2.value);
            break;
        case "Divide":
            if(operand1.value == "") {
                operand1 = 0;
            }
            if(operand2.value == "") {
                operand2 = 0;
            }
            document.getElementById("result").innerHTML = eval(operand1.value) / eval(operand2.value);
            break;
        case "Multiply":
            if(operand1.value == "") {
                operand1 = 0;
            }
            if(operand2.value == "") {
                operand2 = 0;
            }
            document.getElementById("result").innerHTML = eval(operand1.value) * eval(operand2.value);
            break;
    }
}

var intergalactic = document.getElementsByTagName("h1");
intergalactic[0].onclick = makeDelayedHanderler(colorChange, 3000);

function makeDelayedHanderler(f, time) {
    return function( e )
    {
      var ev = e; //|| window.event;
      setTimeout( function()
      {
        f( ev );
      }, time );        
    };
}

function colorChange(e) {
    //var click = (e && e.target) || (event && event.srcElement);  
    var colorValues = '0123456789ABCDEF';
    var color = '#';
    for(i = 0; i < 6; i++) {
        color += colorValues[Math.floor(Math.random() * 16)];
    }

    if(color == '#000000') {
        colorChange(e);
    } else {
        intergalactic[0].style.backgroundColor = color;
    }
    clearTimeout();
}

document.getElementById("mars_time_check").addEventListener("click", marsTime);
function marsTime() {
let d = new Date();
let epoch = new Date(01/01/1970);
let yearsOnEarth = d.getFullYear() - 1970;
let daysOnEarth = (yearsOnEarth*365.25) + d.getDay();
let yearsOnMars = daysOnEarth / 687;
let daysOnMars = daysOnEarth % 687;
let currentMarsYear = 1970 + yearsOnMars;
document.getElementById("mars_time").innerText = "It is currently " + Math.floor(currentMarsYear) + " on Mars\n and " + daysOnMars + " days after January 1st";
}

function walkTheDom(node, func) {
    func(node);
    for(n1 in node.childNodes)
    walkTheDom(n1,func);
}
//testFunc(document.getElementsByTagName("body"));
function testFunc(node) {
    console.log(node.outerHTML);
}