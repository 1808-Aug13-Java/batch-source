// 1
const google = document.getElementsByName("google");
const twitter = document.getElementsByName("twitter");
const slack = document.getElementsByName("slack");
const javadocs = document.getElementsByName("javadocs");
google[0].innerHTML = "Google";
twitter[0].innerHTML = "Twitter";
slack[0].innerHTML = "Slack";
javadocs[0].innerHTML = "Javadocs";
google[0].setAttribute("href", "https://www.google.com");
twitter[0].setAttribute("href", "https://twitter.com/");
slack[0].setAttribute("href", "https://slack.com/");
javadocs[0].setAttribute("href", "https://docs.oracle.com/javase/8/docs/api/");

// 2
let planet = document.getElementById("planet");
planet.options[3].disabled = true;

// 3
planet.addEventListener("click", alienText);

function alienText() {
    let paragraphs = document.getElementsByTagName("p");
    if (planet.value !== "Earth") {
        for (let i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].innerText == "Beep boop") {
                paragraphs[i].removeAttribute("hidden");
            }
        }
    } else {
        for (let i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].innerText == "Beep boop") {
                paragraphs[i].hidden = true;
            }
        }
    }
}

// 4
document.getElementById("form-sub").addEventListener("click", insertRow);

function insertRow() {
    let name = document.createElement("td");
    let firstName = document.getElementById("firstname").value;
    let lastName = document.getElementById("lastname").value;
    if (firstName.length >= 2 && lastName.length >= 2) {
        name.innerHTML = firstName + " " + lastName;
    } else {
        name.innerHTML = "Invalid input";
    }
    let email = document.createElement("td");
    let emailValue = document.getElementById("email").value;
    let emailRegex = /\w+@\w+.\w+/;
    if (emailValue.match(emailRegex)) {
        email.innerHTML = emailValue;
    } else {
        email.innerHTML = "Invalid input";
    }
    let phone = document.createElement("td");
    let phoneValue = document.getElementById("phone").value;
    if (phoneValue.match(/^[0-9]{10}$/)) {
        phone.innerHTML = phoneValue;
    } else {
        phone.innerHTML = "Invalid input";
    }
    let birthday = document.createElement("td");
    let birthdayValue = new Date(document.getElementById("bday").value);
    birthday.innerHTML = formatBirthday(birthdayValue);
    let gender = document.createElement("td");
    gender.innerHTML = document.querySelector('input[name="gender"]:checked').nextSibling.nodeValue;
    let favoriteColor = document.createElement("td");
    favoriteColor.innerHTML = document.getElementById("color").value;
    let activities = document.createElement("td");
    let individualActivities = document.getElementsByClassName("activity");
    let activitiesContent = `<ul>`;
    for (let i = 0; i < individualActivities.length; i++) {
        if (individualActivities[i].checked == true) {
            activitiesContent += `<li>${individualActivities[i].nextSibling.nodeValue}</li>`;
        }
    }
    activities.innerHTML = activitiesContent;
    let row = document.createElement("tr");
    row.append(firstName);
    row.append(email);
    row.append(phone);
    row.append(birthday);
    row.append(favoriteColor);
    row.append(gender);
    row.append(activities);
    let tbody = document.getElementsByTagName("tbody");
    tbody[0].appendChild(row);
}

function formatBirthday(birthday) {
    const month = birthday.getMonth() + 1;
    let zeroMonth = "";
    if (month < 10) {
        zeroMonth = "0";
    }
    const day = birthday.getDate() + 1;
    let zeroDay = "";
    if (day < 10) {
        zeroDay = "0";
    }
    const year = birthday.getFullYear();
    return zeroMonth + month + "/" + zeroDay + day + "/" + year;
}

// 5
const summary = document.getElementsByTagName("summary");
summary[0].addEventListener("mouseover", openDetails);
summary[0].addEventListener("mouseout", closeDetails);

function openDetails() {
    const details = document.getElementsByTagName("details");
    details[0].open = true;
}

function closeDetails() {
    const details = document.getElementsByTagName("details");
    details[0].open = false;
}

// 6
function printSpans() {
    const spans = document.getElementsByTagName("span");
    let output = "";
    for (let i = 0; i < spans.length; i++) {
        output += spans[i].innerHTML;
    }
    console.log(output);
}
printSpans();

// 7
document.getElementById("earth_time_check").addEventListener("click", updateEarthTime);

function updateEarthTime() {
    const today = new Date();
    const getHour = today.getHours();
    const getMinutes = today.getMinutes();
    let getMinutesZero = "";
    if (getMinutes < 10) {
        getMinutesZero = "0";
    }
    const getSeconds = today.getSeconds();
    let getSecondsZero = "";
    if (getSeconds < 10) {
        getSecondsZero = "0";
    }
    document.getElementById("earth_time").innerHTML = getHour + ":" + getMinutesZero + getMinutes + ":" + getSecondsZero + getSeconds;
}

// 8
document.getElementById("mars_time_check").addEventListener("click", updateMarsTime);

function updateMarsTime() {
    const now = Date.now();
    const earthSeconds = now / 1000;
    const earthMinutes = earthSeconds / 60;
    const earthHours = earthMinutes / 24;
    const earthDays = earthHours / 60;
    const marsYears = earthDays / 687;
    document.getElementById("mars_time").innerHTML = "Mars years: " + marsYears;
}

document.getElementById("acb_time_check").addEventListener("click", searchPlanet);

function searchPlanet() {
    fetch('http://www.astropical.space/astrodb/api-exo.php?which=name&limit=prox&format=json')
        .then(function (response) {
            return response.json().then(function (val) {
                const proximaCentauriPeriod = val.exoplanets[0].per;
                const now = Date.now();
                const earthSeconds = now / 1000;
                const earthMinutes = earthSeconds / 60;
                const earthHours = earthMinutes / 24;
                const earthDays = earthHours / 60;
                const proximaCentauriYears = earthDays / proximaCentauriPeriod;
                document.getElementById("acb_time").innerHTML = "Proxima Centauri b years: " + proximaCentauriYears;
            })
        })
}

// 9
const spanIGHeadings = document.getElementsByTagName("span");
spanIGHeadings[0].addEventListener("click", changeRandomColor);

function changeRandomColor() {
    setTimeout(function () {
        const letters = '123456789ABCDEF';
        let color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 15)];
        }
        document.body.style.background = color;
    }, 3000);
}

// 10
document.getElementById("operation").addEventListener("click", function () {
    const n1 = document.getElementById("n1").value;
    const n2 = document.getElementById("n2").value;
    if (!isNaN(n1) && !isNaN(n2)) {
        const opts = document.getElementById("operation");
        selectedOpt = opts.options[opts.selectedIndex].value;
        console.log(selectedOpt);
        switch (selectedOpt) {
            case "Add":
                document.getElementById("result").innerHTML = parseInt(n1) + parseInt(n2);
                break;
            case "Subtract":
                document.getElementById("result").innerHTML = parseInt(n1) - parseInt(n2);
                break;
            case "Multiply":
                document.getElementById("result").innerHTML = parseInt(n1) * parseInt(n2);
                break;
            case "Divide":
                if (n2 != 0) {
                    document.getElementById("result").innerHTML = parseInt(n1) / parseInt(n2);
                } else {
                    document.getElementById("result").innerHTML = "Cannot divide by 0";
                }
                break;
        }
    }
}, { passive: true });

// 11
function walkTheDOM(node, func) {
    func(node);
    node = node.firstChild;
    while (node) {
        walkTheDOM(node, func);
        node = node.nextSibling;
    }
}
walkTheDOM(window.document, function (node) {
    console.log(node);
});
