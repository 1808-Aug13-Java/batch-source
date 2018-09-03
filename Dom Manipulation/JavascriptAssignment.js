
/*
 1. Make each link direct the user to its respective website (see id)
*/
let goog = document.getElementsByName("google")[0];
let twit = document.getElementsByName("twitter")[0];
let slac = document.getElementsByName("slack")[0];
let java = document.getElementsByName("javadocs")[0];

goog.setAttribute("href", "https://www.google.com");
goog.innerHTML = "Google";
	

	
twit.href = "https://www.twitter.com";
twit.innerHTML = "Twitter";
	
slac.href = "https://slack.com";
slac.innerHTML = "Slack";
	
java.href = "https://javadocs.com";
java.innerHTML = "JavaDocs";


// 2. Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)

let planet = document.getElementById("planet");//.remove("Pluto");
let pluto = null;
for(plan of planet.children){
	if(plan.innerHTML == "Pluto"){
		console.log("Pluto Detected '" + plan.innerHTML + "'");
		pluto = plan;
		break;
	}
}
if(pluto != null){planet.removeChild(pluto)};


/*
 3. Define a function alienText() which shows the hidden element displaying an alien message.
	When any planet other than Earth is selected, execute this function.
*/

function alienText(){
	let paras = document.getElementsByTagName("p");
	
	let alien = null;
	for(ele of paras){
		if(ele.innerHTML == "Beep boop"){
			alien = ele;
			break;
		}
	}
	
	if(alien == null)
		return;
	
	alien.hidden = false;
	
}

function checkIfAlien(){
	let plan = document.getElementById("planet").value;
	console.log("Planets Changed: '" + plan + "'");
	if(plan != "Earth"){
		alienText();
	}
}

document.getElementById("planet").addEventListener("input", checkIfAlien);

/*
 4. When the submit button is pressed, get the values from all of the input into a new row in the
	table below. Make sure no input is empty, check that first and last name are at least two 
	letters each. Validate for valid phone number and email structure. This should continue to 
	work for multiple entries and rows.
*/

let inputFirstName = document.getElementById("firstname");
	inputFirstName.required = true;
let inputLastName = document.getElementById("lastname");
	inputLastName.required = true;
let inputEmail = document.getElementById("email");
	inputEmail.required = true;
let inputPhone = document.getElementById("phone");
	inputPhone.required = true;
let inputBirthday = document.getElementById("bday");
	inputBirthday.required = true;
let inputPlanet = document.getElementById("planet");
	inputPlanet.required = true;

let inputGender	= document.getElementsByName("gender");

let inputGenderM = inputGender[0];
let inputGenderF = inputGender[1];
let inputGenderO = inputGender[2];

let inputColor = document.getElementById("color");

let checkBoxes = document.getElementsByClassName("activity");
var checkHike, checkStamp, checkBird, checkBasket;
for(cb of checkBoxes){
	if(cb.value == "hiking"){
		checkHike = cb;
		continue;
	}
	if(cb.value == "stamp"){
		checkStamp = cb;
		continue;
	}
	if(cb.value == "birding"){
		checkBird = cb;
		continue;
	}
	if(cb.value == "basket"){
		checkBasket = cb;
	}
}

function getFormData(){
	inputFirstName.value = inputFirstName.value.replace(" ", "");
	if(inputFirstName.value.length < 2){return;}
	
	inputLastName.value = inputLastName.value.replace(" ", "");
	if(inputLastName.value.length < 2){return;}
	
	var gender = -1;
	if(inputGenderF.checked){ gender = 1;}
	if(inputGenderM.checked){ gender = 2;}
	if(inputGenderO.checked){ gender = 3;}
	
	switch(gender){
		case 1:
			var genderStr = "Female";
			break;
		case 2:
			var genderStr = "Male";
			break;
		case 3:
			var genderStr = "Other";
			break;
		default:
			
			return;
	}
	
	let newRow = document.createElement("tr");
	
	let nameData = document.createElement("td");
	nameData.innerHTML = inputFirstName.value + " " + inputLastName.value;
	newRow.appendChild(nameData);
	
	let emailData = document.createElement("td");
	emailData.innerHTML = inputEmail.value;
	newRow.appendChild(emailData);
	
	let phoneData = document.createElement("td");
	phoneData.innerHTML = inputPhone.value;
	newRow.appendChild(phoneData);
	
	let birthData = document.createElement("td");
	birthData.innerHTML = inputBirthday.value;
	newRow.appendChild(birthData);
	
	let colorData = document.createElement("td");
	colorData.innerHTML = inputColor.value;
	newRow.appendChild(colorData);
	
	let genderData = document.createElement("td");
	genderData.innerHTML = genderStr;
	newRow.appendChild(genderData);
	
	let listData = document.createElement("td");
	let uList = document.createElement("ul");
	
	if(checkHike.checked) { uList.innerHTML += "<li>hiking</li>"; }
	if(checkStamp.checked) { uList.innerHTML += "<li>stamp collecting</li>"; }
	if(checkBird.checked) { uList.innerHTML += "<li>birding</li>"; }
	if(checkBasket.checked) { uList.innerHTML += "<li>underwater basket weaving</li>"; }
	
	listData.appendChild(uList);
	newRow.appendChild(listData);
	
	let tableBody = document.getElementsByTagName("tbody");
	tableBody = tableBody[0];
	
	tableBody.appendChild(newRow);
	
	inputFirstName.value = "";
	inputLastName.value = "";
	inputEmail.value = "";
	inputPhone.value = "";
	
	for(gen of inputGender){
			gen.checked = false;
	}
	
	for(hob of checkBoxes){
		hob.checked = false;
	}
}

document.getElementById("form-sub").addEventListener("click", getFormData);


/*
 5. Create a function openDetails() which opens the details element. Invoke this function when the
	details’ summary is moused over. The details should be hidden when the mouse is removed from
	the summary.
*/
let detailsObj = document.getElementsByTagName("details")[0];
function openDetails(){
	detailsObj.open = true;
}

function closeDetails(){
	detailsObj.open = false;
}
detailsObj.addEventListener("mouseover", openDetails);
detailsObj.addEventListener("mouseleave", closeDetails);


/*
 6. Create a function that concatenates the inner HTML of all of the span elements and prints the
	results to the console.
*/
function logSpan(){
	let spans = document.getElementsByTagName("span");
	let snapText = "";
	for(sp of spans){
		snapText += sp.innerHTML + "\n";
	}
	console.log(snapText);
}


/*
 7. Create a function that displays the current time on earth in the span with id “earth_time”.
	Invoke this function when “Earth time” button is clicked. 
*/
function displayTime(){
	console.log("Displaying Time");
	let eTime = document.getElementById("earth_time");
	let curTime = new Date();
	let seconds = curTime.getSeconds().toString();
	if(seconds.length == 1){
		seconds = "0" + seconds;
	}
	eTime.innerHTML = "<p>" + curTime.getHours() + ":"+curTime.getMinutes() + ":" + seconds + "</p>";
}

document.getElementById("earth_time_check").addEventListener("click", displayTime);

/*
 8. Create two other functions which calculates and displays the time passed on Mars and Alpha
	Centauri Bb if the onset of January 1st, 1970 occured at the same time. Invoke the respective
	functions when their time buttons are clicked. An orbital period for Mars is 687 Earth days.
	Using an external api to get the orbital period for Alpha Centauri Bb. 
	(try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting
	this value using both AJAX and the fetch API.
*/
let baseUrl = "http://www.astropical.space/astrodb/api-exo.php?format=json";

function getOrbitalAjax(){
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 & this.status == 200){
			parseData(this.responseText, true);
		}
	}
	xhr.open("GET", baseUrl);
	xhr.send();
}

function getOrbitalFetch(){
	fetch(baseUrl,{
		method: "GET"
	}).then(function(response){
		return response.json();
	}).then(function(data){
		parseData(data);
	});
}

function parseData(blob, isString = false){
	
	//return;
	//blob = blob.replace(': ,', ': " ",');
	
	
	if(isString){
		var data = JSON.parse(blob);
	}else{
		var data = blob;
	}
	
	let targetPlanet = null;
	for(planet of data.exoplanets){
		if(planet.name == ("Alpha Centauri b" || "Alpha Centauri Bb")){
			targetPlanet = planet;
			break;
		}
	}
	
	let centauriTime = document.getElementById("acb_time");
	
	if(targetPlanet == null){
		centauriTime.innerHTML = "Planet Not Found";
	}else{
		let planDate = new Date();
		let earthYears = marsDate.getFullYear() - 1970;
		
		let num = Number(targetPlanet.per);
		if(isNaN(num)){
			centauriTime.innerHTML = "No ProperTime Detected";
		}else{
			let planYears = earthYears / 365.25 * num;
			centauriTime.innerHTML = planYears + "Alpha Centauri years";
		}
	}
	
	
}

function getMarsTime(){
	let marsDate = new Date();
	let earthYears = marsDate.getFullYear() - 1970;
	console.log(earthYears);
	let marsYears = earthYears / (365.25) * 687;
	
	let mTime = document.getElementById("mars_time");
	mTime.innerHTML = marsYears + " Mars Years";
}
document.getElementById("mars_time_check").addEventListener("click", getMarsTime);

document.getElementById("acb_time_check").addEventListener("click", getOrbitalAjax);

//getOrbitalAjax();
//getOrbitalFetch();

/*
 9. Three seconds after a user clicks on the “Intergalactic Directory” heading, the     background
	color should change to a random color. Make sure this color is never black so we can still
	read our black text! (there are other dark colors it could change to where we also couldn’t
	see the text but it’s enough to just accomodate for a black background)
*/
function setRandomBackgroundColor(){
	let r = Math.floor(Math.random() * 255); 
	let g = Math.floor(Math.random() * 255); 
	let b = Math.floor(Math.random() * 255); 
	let a = Math.random(); 
	
	if(r < 10){	r += 20};
	if(g < 10){	g += 20};
	if(b < 10){	b += 20};
	let color = "rgba(" + r + "," + g +","+b+","+a+")";
	
	document.getElementsByTagName("body")[0].style.backgroundColor = color;
}

function timeSetColor(){
	setTimeout(setRandomBackgroundColor, 3000);
}
document.getElementsByTagName("h1")[0].addEventListener("click", timeSetColor);

/*
 10.When inputs with id n1 and n2 have valid numerical input, perform the operation specified
	in the select. Display the result in the element with id result.
*/
let num1 = document.getElementById("n1");
let num2 = document.getElementById("n2");
let numRes = document.getElementById("result");
let op = document.getElementById("operation");


function performOp(number1, number2){
	switch(op.value){
		case "Add":
			numRes.innerHTML = number1 + number2;
			break;
		case "Subtract":
			numRes.innerHTML = number1 - number2;
			break;
		case "Divide":
			if(number2 != 0){
				numRes.innerHTML = number1 / number2;
			}else{
				numRes.innerHTML = "N/A";
			}
			break;
		case "Multiply":
			numRes.innerHTML = number1 * number2;
			break;
		default:
			numRes.innerHTML = "Operation not Specified";
	}
}

function attemptOperation(){
	//console.log("num1: '" + num1.value + "' and num2: '" + num2.value + "'");
	
	let number1 = Number(num1.value);
	let number2 = Number(num2.value);
	if(isNaN(number1) || isNaN(number2)){ 
		numRes.innerHTML = "Invalid Input Detected";
		return;
	}
	
	performOp(Number(num1.value), Number(num2.value));
}

num1.addEventListener("input", attemptOperation);
num2.addEventListener("input", attemptOperation);
op.addEventListener("change", attemptOperation);



/*
 11. Define function walkTheDom(node, func)
    This function should traverse every node in the DOM. 
    Use recursion. On each node, calle func(node).
*/

function walkTheDom(node, func){
	func(node);
	
	for(ch of node.children){
		walkTheDom(node, func);
	}
}
