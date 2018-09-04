// Make each link direct the user to its respective website (see id)
document.getElementsByName("google")[0].href = "https://google.com";
document.getElementsByName("twitter")[0].href = "https://twitter.com";
document.getElementsByName("slack")[0].href = "https://slack.com";
document.getElementsByName("javadocs")[0].href = "https://javadocs.com";



//------------------------------------------------------------------------
// Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
document.getElementById("planet")[3].disabled = true;



//------------------------------------------------------------------------
// Define a function alienText() which shows the hidden element displaying 
// an alien message. When any planet other than Earth is selected, execute 
// this function.

// find hidden element
var hidden_ele;
var allP = document.getElementsByTagName("p");
for (i in allP){
    if (allP[i].hidden == true){
        hidden_ele = allP[i];
    }
}

// Listen for click on planet field
document.getElementById("planet").addEventListener("click", alienText);
console.log(hidden_ele.innerHTML);

function alienText(){
    if(document.getElementById("planet").value != "Earth"){
        hidden_ele.hidden = false;
    } else {
        hidden_ele.hidden = true;
    }
}


//------------------------------------------------------------------------
// When the submit button is pressed, get the values from all of the input 
// into a new row in the table below.  Make sure no input is empty, check 
// that first and last name are at least two letters each. Validate for valid 
// phone number and email structure. This should continue to work for multiple
// entries and rows.
document.getElementById("form-sub").addEventListener("click", insertTable);

function addRow(name, email, phone, birth, color, gender, activ){
    let row = document.createElement("tr");

    let cell1 = document.createElement("td")
    let cell2 = document.createElement("td")
    let cell3 = document.createElement("td")
    let cell4 = document.createElement("td")
    let cell5 = document.createElement("td")
    let cell6 = document.createElement("td")
    let cell7 = document.createElement("td")

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);

    cell1.innerHTML = name;
    cell2.innerHTML = email;
    cell3.innerHTML = phone;
    cell4.innerHTML = birth;
    cell5.innerHTML = color;
    // gender and activ need more processing
    for (i in gender){
        if (gender[i].checked){
            cell6.innerHTML = gender[i].value;
        }
    }
    for (j in activ){
        if (activ[j].checked){
            cell7.innerHTML += activ[i].value;
        }
    }

    // This isn't getting the table for some reason and I can't append to it
    let doc = document.getElementsByTagName("tbody")[0];
    doc.appendChild(row);
    console.log(doc);
}

// Regex for email validation from chromium
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validatePhone(phone) {
    if (phone.length != 10){
        alert("Invalid input - phone number must be 10 digits");
        return false;
    }
    var regex = /^\d+$/;
    return regex.test(phone);
}

function insertTable(){
    let first = document.getElementById("firstname").value;
    let last =  document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    let color = document.getElementById("color").value;
    let gender = document.getElementsByName("gender");
    let activ = document.getElementsByClassName("activity");

    // console.log(gender);

    if(first.length < 2){
        alert("Invalid input - first name must be at least 2 characters");
    } else if (last.length < 2){
        alert("Invalid input - last name must be at least 2 characters");
    } else if (!validateEmail(email)){
        alert("Invalid input - email is not of proper format");
    } else if (!validatePhone(phone)) {
        alert("Invalid input - phone number must contain no punctuation, letters, and spaces");
    } else if (first && last && email && phone && bday && color) {
        addRow(first+last, email, phone, bday, color, gender, activ);
    }
}

//------------------------------------------------------------------------
// Create a function openDetails() which opens the details element. Invoke 
// this function when the details’ summary is moused over. The details 
// should be hidden when the mouse is removed from the summary.
document.getElementsByTagName("details")[0].addEventListener("mouseover", openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseleave", hideDetails);

// console.log(document.getElementsByTagName("details")[0].childNodes[3]);
document.getElementsByTagName("details")[0].childNodes[3].hidden = true;

function openDetails(){
    document.getElementsByTagName("details")[0].childNodes[3].hidden = false;
}
function hideDetails(){
    document.getElementsByTagName("details")[0].childNodes[3].hidden = true;
}



//------------------------------------------------------------------------
// Create a function that concatenates the inner HTML of all of the span
// elements and prints the results to the console.
function concatSpan(){
    let span = document.getElementsByTagName("span");
    let concat;
    for (i in span){
        concat += span[i].innerHTML;
    }
    console.log(concat);
}


//------------------------------------------------------------------------
// Create a function that displays the current time on earth in the h4 tag
// with id “earth_time”. Invoke this function when “Earth time” button is clicked. 
document.getElementById("earth_time_check").addEventListener("click", earthTime);

function earthTime(){
    var date = new Date();
    document.getElementById("earth_time").innerHTML = `${date.getHours()}:${date.getMinutes()}`;
}


//------------------------------------------------------------------------
// Create two other functions which calculates and displays the time passed
// on Mars and Alpha Centauri Bb if the onset of January 1st, 1970 occured
// at the same time. Invoke the respective functions when their time buttons
// are clicked. An orbital period for Mars is 687 Earth days. Using an external
// api to get the orbital period for Alpha Centauri Bb.
// (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation
// for getting this value using both AJAX and the fetch API.

// Ok I'm not a scientist and I really don't understand what this prompt is asking
// me to do, nor how to do it. So I'm just gonna query data using the API

// Using AJAX
document.getElementById("mars_time_check").addEventListener("click", marsTime);

function marsTime(){
    sendAjaxGet("http://www.astropical.space/astrodb/api-ephem.php?lat=35&lon=139", displayTime);
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function(){
        // console.log(xhr);
        if (this.readyState === 4 && this.status == 200){
            callback(this);
            // console.log(xhr.responseText);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayTime(xhr){
    // console.log(xhr);
    let planets = JSON.parse(xhr.responseText);
    // console.log(planets.response[2]);
    document.getElementById("mars_time").innerHTML = planets.response[2].lt_hms;

}

// Using fetch api
document.getElementById("acb_time_check").addEventListener("click", acbTime);

function acbTime(){
    fetch('http://www.astropical.space/astrodb/api-exo.php?which=name&limit=gj%20625%20b&format=json')
        .then(response => {
            return response.json();
        }).then(data => {
            // console.log(data);
            document.getElementById("acb_time").innerHTML= data.exoplanets[0].ra;
        }).catch(err => {
            console.log(err);
        });
}


//------------------------------------------------------------------------
// Three seconds after a user clicks on the “Intergalactic Directory” heading,
// the background color should change to a random color. Make sure this color
// is never black so we can still read our black text! (there are other dark
// colors it could change to where we also couldn’t see the text but it’s enough
// to just accomodate for a black background)
document.getElementsByTagName("h1")[0].addEventListener("click", setTimeout(headingColor, 3000));

function headingColor(){
    
}