// 1. Make each link direct the user to its respective website (see id)
document.getElementsByName('google')[0].href = 'https://www.google.com';
document.getElementsByName('twitter')[0].href = 'https://www.twitter.com';
document.getElementsByName('slack')[0].href = 'https://www.slack.com';
document.getElementsByName('javadocs')[0].href = 'https://docs.oracle.com/javase/8/docs/api/';

// 2. Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!) (RIP Pluto)
function deletePluto() {
    var select = document.getElementById('planet');

    for (i  = 0; i < select.length; i++) {
        if (select.options[i].value === 'Pluto') {
            select.remove(i);
        }
    }
}

deletePluto();

/*
3.Define a function alienText() which shows the hidden element displaying
an alien message. When any planet other than Earth is selected, execute
this function.
*/
let selectPlanet = document.getElementById('planet');

function alientText() {
    let container = document.getElementsByClassName("container")[0];
    let alienMessage = container.getElementsByTagName("p")[5];
    
    
    if (selectPlanet.value != "Earth") {
        alienMessage.hidden = false;
    } else {
        alienMessage.hidden = true;
    }
  }
  
  selectPlanet.addEventListener("click", alientText);

/*
4. When the submit button is pressed, get the values from all of the input into
a new row in the table below.  Make sure no input is empty, check that first
and last name are at least two letters each. Validate for valid phone number
and email structure. This should continue to work for multiple entries and rows.
*/

// main method to perform all other helper methods
// adds data to row/table after validation
function validateAndAppend() {
    let minNameLength = 2;
    let minPhoneNumberLength = 10;
    let minCountryCodeLength = 4;
    let firstNameNode = document.getElementById('firstname');
    let firstNameParent = firstNameNode.parentElement;
    let getFirstName = document.getElementById("firstname").value;
	let getLastName = document.getElementById("lastname").value;
	let getEmail = document.getElementById("email").value;
	let getTelephone = document.getElementById("phone").value;
	let getBirthday = document.getElementById("bday").value;
	let getPlanet = document.getElementById("planet").value;
    let getColor = document.getElementById("color").value;
    let getGender = firstNameParent.querySelector('input[name=gender]:checked');
    // get activities
    let checkbox = firstNameNode.getElementsByClassName('activity');
    let getActivities = [];

    for (cb of checkbox) {
        if (cb.checked) {
            getActivities.push(cb.value);
        }
    }

    // don't allow empty input and display alert if so
	if (getFirstName == "" || getLastName == "" || getEmail == "" || 
    getTelephone == "" || getBirthday == "" || getPlanet == "" || 
    getColor == "" || getGender == null) {

        alert("Field(s) are empty. Invalid input detected.");
        return;
    }

if (getFirstName.length < minNameLength || getLastName.length < minNameLength) {
    alert("Name is < 2 characters. Please add a longer name.");
    return;
}

if (validateEmail(getEmail) == false) {
    alert("Invalid email.");
    return;
}


let isNum = /[^0-9]+/g;
let replaceNonNum = getTelephone.replace(isNum, '');

if (replaceNonNum.length < minPhoneNumberLength || replaceNonNum.length > minPhoneNumberLength + minCountryCodeLength) {
    alert("Invalid phone number, please try again.");
    return;
}


// finally append everything to the table.
addTr(getFirstName + " " + getLastName, getEmail, replaceNonNum, getBirthday,
        getColor, getGender.innerText, activities);
}

function addRow(name, email, phone, birthday, favoriteColor, gender, activities) {
    let newTr = document.createElement('tr');
    newRow.setAttribute('scope', 'row');

    appendToTable(newTr, name);
    appendToTable(newTr, email);
    appendToTable(newTr, phone);
    appendToTable(newTr, birthday);
    appendToTable(newTr, favoriteColor);
    appendToTable(newTr, gender);

    let activitiesTd = document.createElement('td');
    let activitiesUl = document.createElement('ul');

    for (activity of activities) {
        let activityLi = document.createElement('li');
        activityLi.innerHTML = activity;
        activitiesUl.appendChild(activityLi);
    }

    activitiesTd.appendChild(activityLi);
    let getTable = document.getElementsByTagName('table');
    getTable[0].getElementsByTagName('tbody')[0].appendChild(newTr);
}


// email validation
function validateEmail(email) {
    if (email.length === 0) {
        return false;
    }
    let regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return regex.test(email);
}

// append input to corresponding row
function appendToTable(tr, input) {
    let td = document.createElement('td');
    td.innerText = input;
    tr.appendChild(td);
}


/*
5. Create a function openDetails() which opens the details element. Invoke this function when
the details’ summary is moused over. The details should be hidden when the mouse is removed
 from the summary.
 */
let summary = document.querySelector('summary');
let details = document.querySelector('details');

function openDetails(event, callback) {
    details.setAttribute('open', true);
}

function closeDetails(event, callback) {
  details.removeAttribute('open');
}

summary.addEventListener('mouseover', openDetails);
details.addEventListener('mouseleave', closeDetails);

/*
6. Create a function that concatenates the inner HTML of all of the span elements
and prints the results to the console.
*/
function concatSpans() {
    let getSpans = document.getElementsByTagName('span');
    let spansArr = [];

    for (let i = 0; i < getSpans.length; i++) {
        spansArr.push(getSpans.innerHTML);
    }

    console.log(spansArr.join(' '));
}

concatSpans();

/*
7.Create a function that displays 
the current time on earth in the span 
with id “earth_time”. Invoke this function 
when “Earth time” button is clicked.
*/
function showEarthTime() {
    document.getElementById("earth_time_check").addEventListener("click", function() {
        document.getElementById("earth_time").innerHTML = new Date().toLocaleTimeString();
    });
}

showEarthTime();

/*
8. Create two other functions which calculates and displays the time passed on Mars
and Alpha Centauri Bb if the onset of January 1st, 1970 occured at the same time.
Invoke the respective functions when their time buttons are clicked. An orbital
period for Mars is 687 Earth days. Using an external api to get the orbital
period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php)
Provide an implementation for getting this value using both AJAX and the fetch API.
*/
function calculateMarsTimePassed() {
    let currentEarthTime = Date.now();
    let seconds = currentEarthTime/1000;
    let minutes = seconds/60;
    let hours = minutes/24;
    let days = hours/60;
    let yearsOnMars = days/687;

    document.getElementById('mars_time').innerHTML = `Time passed since epoch time on mars: ${yearsOnMars} years`;
}

document.getElementById('mars_time_check').addEventListener('click', calculateMarsTimePassed);

// XMLHttpRequest ACB
// fetch implementation is the one that is actually implemented, this is just an alternative using XMLHttpRequest
function XhrACB(url, callback) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let parsed = JSON.parse(this.responseText);
            document.getElementById('acb_time_check').innerHTML += parsed;
            callback(this);
        }
    }

    xhr.open("GET", url);
    xhr.send();
}

// if you wanted to use XHR instead of Fetch API
// XhrACB('http://www.astropical.space/astrodb/api-exo.php?which=name&limit=prox&format=json', callback)

// fetch ACB
function fetchACB() {
    fetch('http://www.astropical.space/astrodb/api-exo.php?which=name&limit=prox&format=json')
        .then(function(response) {
            return response.json()
                .then(function(value) {
                    let currentEarthTime = Date.now();
                    let seconds = currentEarthTime/1000;
                    let minutes = seconds/60;
                    let hours = minutes/24;
                    let days = hours/60;
                    let proximaCentauriOrbitalPeriod = value.exoplanets[0].per;
                    let yearsSinceProximaCentauriEpoch = days / proximaCentauriOrbitalPeriod;

                    document.getElementById('acb_time').innerHTML = `Years since PCB epoch: ${yearsSinceProximaCentauriEpoch} years`;

                })
        })
}

document.getElementById('acb_time_check').addEventListener('click', fetchACB);



/*
9. Three seconds after a user clicks on the “Intergalactic Directory” heading, the
background color should change to a random color. Make sure this color is never
black so we can still read our black text! (there are other dark colors it could
change to where we also couldn’t see the text but it’s enough to just
accomodate for a black background)
*/

let firstDiv = document.getElementsByClassName('container')[0];
let firsth1 = firstDiv.getElementsByTagName('h1');

function getRandomColor() {
    var hexLetters = '0123456789ABCDEF';
    var hexColor = '#';

    for (var i = 0; i < 6; i++) {
      hexColor += hexLetters[Math.floor(Math.random() * 16)];
    }

    return hexColor;
};
  
function randomize() {
    document.body.style.background = getRandomColor();
};

firsth1[0].addEventListener("click", function() {
    setTimeout(randomize, 3000);
});

/*
10. When inputs with id n1 and n2 have valid numerical input, perform the
operation specified in the select. Display the result in the element with
id result.
*/
function calculateInput() {
    const n1 = document.getElementById('n1').value;
    const n2 = document.getElementById('n2').value;

    if (!isNaN(n1) || !isNaN(n2)) {
        let getSelectedOption = document.getElementById('operation').options[document.getElementById("operation").selectedIndex].value;
        if (getSelectedOption === 'Add') {
            document.getElementById('result').innerHTML = parseInt(n1) + parseInt(n2);
        }

        if (getSelectedOption === 'Subtract') {
            document.getElementById('result').innerHTML = parseInt(n1) - parseInt(n2);
        }

        if (getSelectedOption === 'Multiply') {
            document.getElementById('result').innerHTML = parseInt(n1) * parseInt(n2);
        }

        if (getSelectedOption === 'Divide') {
            if (n2 === 0) {
                document.getElementById('result').innerHTML = "You cannot divide a number by zero! Please try again."
            } else {
                document.getElementById('result').innerHTML = parseInt(n1) / parseInt(n2);
            }

        }
        
    }
}

document.getElementById('operation').addEventListener('click', calculateInput);

/*
11. Define function walkTheDom(node, func)
This function should traverse every node in the DOM. 
Use recursion. On each node, call func(node).
*/

function walkTheDom(node, func) {
    func(node);
    node = node.firstChild;
    while (node) {
        var next = node.nextSibling
      walkTheDom(node, func);
      node = next;
    }
    func(node);
  }