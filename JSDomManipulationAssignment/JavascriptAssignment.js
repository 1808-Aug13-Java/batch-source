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
    for (let i = 0; i < spans.length; i++){
        output += spans[i].innerHTML;
    }
    console.log(output);
}
printSpans();

// 7
