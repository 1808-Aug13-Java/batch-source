/**
 * Chandrika Sinha
 */

// QUESTION 1
function question1() {
	document.getElementsByName("google")[0].innerText = "Google";
	document.getElementsByName("google")[0].setAttribute("href", "https://www.google.com/");
	
	document.getElementsByName("twitter")[0].innerText = "Twitter";
	document.getElementsByName("twitter")[0].setAttribute("href", "https://twitter.com/")

	document.getElementsByName("slack")[0].innerText = "Slack";
	document.getElementsByName("slack")[0].setAttribute("href", "https://slack.com/");

	document.getElementsByName("javadocs")[0].innerText = "Javadocs";
	document.getElementsByName("javadocs")[0].setAttribute("href", "https://javadocs.com/");
}
question1();

// QUESTION 2
function question2() {
	document.getElementsByTagName("option")[3].setAttribute("disabled", "true");
}
question2();

// QUESTION 3
function alienText() {
	let elements = document.getElementsByTagName("p");
	for (var i = 0; i < elements.length; i++) {
		elements[i].hidden = false;
	}
}
function question3() {
	// When any planet other than Earth is selected, execute this function
	alienText();
}
question3();

// QUESTION 4
document.getElementById("form-sub").addEventListener("click", showValues);
function showValues() {
	let first = document.getElementById("firstname");
	let last = document.getElementById("lastname");
	let email = document.getElementById("email");
	let phone = document.getElementById("phone");
	let bday = document.getElementById("bday");
	let gender = document.querySelector('input[name = "gender"]:checked');
	let color = document.getElementById("color");
	let activities = document.querySelectorAll('input[type = "checkbox"]:checked');

	addRow(first, last, email, phone, bday, gender, color, activities);
}

function addRow(first, last, email, phone, birthday, gender, color, activities) {
	console.log(first);
	let row = document.createElement("tr");
	let cell1 = document.createElement("td");
	let cell2 = document.createElement("td");
	let cell3 = document.createElement("td");
	let cell4 = document.createElement("td");
	let cell5 = document.createElement("td");
	let cell6 = document.createElement("td");
	let cell7 = document.createElement("td");
	
	cell1.innerText = first+' '+last;
	cell2.innerText = email;
	cell3.innerText = phone;
	cell4.innerText = birthday;
	cell5.innerText = color;
	cell6.innerText = gender;
	
	let act = document.createElement("ul");
	for (var i = 0; i < activities.length; i++) {
		let activity = document.createElement("li");
		activity.innerHTML = activities[i];
		act.appendChild(activity);
	}

	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell6);
	row.appendChild(cell7);
	cell7.appendChild(act);
	
	document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);
}

// QUESTION 5
function openDetails() {
	this.open = true;
}
function closeDetails() {
	this.open = false;
}
document.getElementsByTagName("details")[0].addEventListener("mouseenter", openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseleave", closeDetails);

// QUESTION 6
function question6() {
	let spans = document.getElementsByTagName("span");
	let concatentations = '';
	for (var i = 0; i < spans.length; i++) {
		concatentations+=spans[i].innerHTML;
	}
	console.log(concatentations);
}
question6();

// QUESTION 7
function question7() {
	var d = new Date();
	document.getElementById("earth_time").innerHTML = d.getHours() + ':' + d.getMinutes();
}
document.getElementById("earth_time_check").addEventListener("click", question7);

// QUESTION 8
function timePassedOnMars() {
	var x = new Date();
	let earthYearsPassed = x.getFullYear() - 1970;
	let earthDays = (earthYearsPassed*365.25) + x.getDay();
	let marsYears = earthDays / 687;
	let marsDays = earthDays % 687;
	document.getElementById("mars_time").innerText = Math.floor(marsYears) + " years and " + marsDays + " days";
}
document.getElementById("mars_time_check").addEventListener("click", timePassedOnMars);

let baseUrl = "http://www.astropical.space/astrodb/api.php?table=nearby&which=distance&limit=10&format=json";
function timePassedOnACB() {
	sendAjaxGet(baseUrl, displayACBTime);
}

function displayACBTime(xhr) {
	var stars = xhr.responseText.replace(/\"hd\": ,/g, "");
	stars = stars.replace(/\"sao\": ,/g, "");
	stars = stars.replace(/\"hip\": ,/g, "");
	stars = stars.replace(/\"rad\": ,/g, "");
	stars = stars.replace(/\"hd\": ,/g, "");
	
	
	let info = JSON.parse(stars);
	
	console.log(info.hipstars[2]);
	// let acbDaysPerYear = 0; `Weather for ${weather.location.name}, ${weather.location.region}`
	let x = new Date();
	let earthYearsPassed = x.getFullYear() - 1970;
	let earthDays = (earthYearsPassed*365.25) + x.getDay();
	let acbYears = earthDays / 687;
	let acbDays = earthDays % 687;
	document.getElementById("acb_time").innerText = Math.floor(acbYears) + " years and " + acbDays + " days";
}

function sendAjaxGet(url, callback) {
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Mircrosoft.HTTPRequest"));
	xhr.onreadystatechange = function() {
		if (this.readyState === 4 && this.status) {
			callback(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();	
}

document.getElementById("acb_time_check").addEventListener("click", timePassedOnACB);

// QUESTION 9
function question9() {
	var r = Math.floor(Math.random() * 255);
	var g = Math.floor(Math.random() * 255);
	var b = Math.floor(Math.random() * 255);
	document.getElementsByTagName("h1")[0].style.backgroundColor = 'rgb(' + r + ',' + g + ',' + b + ')';
}
document.getElementsByTagName("h1")[0].addEventListener("click", function() {
	setTimeout(question9, 3000);
});

// QUESTION 10
function question10() {
	var n1 = document.getElementById("n1");
	var n2 = document.getElementById("n2");
	console.log(n1.innerText);

	if (isNaN(n1.nodeValue)) {

	}
}
question10();

// QUESTION 11