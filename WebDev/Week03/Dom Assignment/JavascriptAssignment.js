

// 1. Set the anchor tags to folow their actual links. 
function setAnchorLinks() {
	// Get all the anchor tags
	let links = document.getElementsByTagName("a");

	// Go through each anchor tag and set it's link accordingly. 
	for (link of links) {
		if (link.getAttribute("name") == "google") {
			link.setAttribute("href", "https://www.google.com");
		}
		else if (link.getAttribute("name") == "twitter") {
			link.setAttribute("href", "https://www.twitter.com");
		}
		else if (link.getAttribute("name") == "slack") {
			link.setAttribute("href", "https://slack.com");
		}
		else if (link.getAttribute("name") == "javadocs") {
			link.setAttribute("href", "https://docs.oracle.com/javase/8/docs/api/");
		}
	}
} // end of setAnchorLinks






// 2. Disable Pluto as a planet of residence. 
function disablePluto() {
	// Get the form control with the planet listings
	let planets = document.getElementById("planet");

	// Search through the listings to find an option that contains "Pluto". 
	// If we find it, remove it from the planet listings. 
	for (let i=0; i<planets.childNodes.length;) {
		// If the text is "Pluto" remove the node. Also, don't increment i as 
		// it might skip if there are multiple "Pluto"s (not that it will likely 
		// be an issue). 
		if (planets.childNodes[i].innerText == "Pluto") {
			planets.removeChild(planets.childNodes[i]);
			continue;
		}
		// Increment i++ only if we didn't remove an element. 
		i++;
	}

} // disablePluto






// 3. A function that displays the alien text when another planet besides earth
// is selected in the planet listings. 
function alienText() {
	// Get the planet that is currently selected. 
	let planet = document.getElementById("planet");

	// If the curent panet isn't "Earth", unhide the alien text 
	if (planet.value != "Earth") {
		// Get the p tag with the hidden attribute. 
		let aliens = document.body.querySelector("p[hidden]");

		// If the p text was found, remove the hidden attribute
		if (aliens != null) {
			aliens.removeAttribute("hidden");
		}
	}

} // end of alientText









// 4.1. When the submit button is clicked, this function gets all of the input 
// from the form, and makes a new table row. 
function onSubmitClick() {
	// The minimum number of characters for a name
	const NAME_LENGTH = 2;
	// The number of numbers in a phone number
	const P_NUMBER_LENGTH = 10;
	// The length of a country code. 
	const P_CONTRY_CODE_LENGTH = 4;

	// Get the first name node
	let firstNameNode = document.getElementById("firstname");

	// Get the parent of the first name node, and search for the rest of the 
	// elements using that node. Saves from searching the rest of the DOM tree. 
	let parentForm = firstNameNode.parentElement;

	// Get the rest of the simple values by their id. 
	let firstNameString = document.getElementById("firstname").value;
	let lastNameString = document.getElementById("lastname").value;
	let emailString = document.getElementById("email").value;
	let phoneString = document.getElementById("phone").value;
	let birthdayString = document.getElementById("bday").value;
	let planetString = document.getElementById("planet").value;
	let colorString = document.getElementById("color").value;
	
	// Gender is a special case, as we need to pull the value from the checked radio button. 
	let genderNode = parentForm.querySelector("input[name=gender]:checked");

	// Activities are also special cases, as each checkbox needs to be examined for a value. 
	// For Boxes that are checked, add their inner text to an activities list. 
	let checkboxes = parentForm.getElementsByClassName("activity");
	let activities = [];
	for (checkbox of checkboxes) {
		if (checkbox.checked) {
			// Note: value is the most accurate value here without hard coding. 
			activities.push(checkbox.value);
		}
	}

	// If any of the values are empty, do nothing as the input is invalid
	if (firstNameString == "" || lastNameString == "" || emailString == "" || 
		phoneString == "" || birthdayString == "" || planetString == "" || 
		colorString == "" || genderNode == null) 
	{
		alert("Please fill in emptry fields");
		return;
	}
	// If either of the name fields are empty or have less than two characters, 
	// alert the user of invalid input and do not add to the table. 
	if (firstNameString.length < NAME_LENGTH ||
		lastNameString.length < NAME_LENGTH) 
	{
		alert("Names must be at least two characters long. ");
		return;
	}

	// If the email is not formatted correctly, alert the user of invalid input 
	// and do not add to the table. 
	if (!isValidEmail(emailString)) {
		alert("The email is not valid. ");
		return;
	}

	// Remove all non-digit characters from the phone number. 
	// First, remove all non number characters. 
	let nonDigitRegex = /[^0-9]+/g;
	let filteredString = phoneString.replace(nonDigitRegex, "");

	// If the phone number doesn't have 10 or 14 numbers, alert the user of 
	// invalid input. 
	if (filteredString.length < P_NUMBER_LENGTH || 
		filteredString.length > P_NUMBER_LENGTH + P_CONTRY_CODE_LENGTH) 
	{
		alert("Phone Numbers must have at between " + P_NUMBER_LENGTH +
				 " and " + (P_NUMBER_LENGTH + P_CONTRY_CODE_LENGTH) +
				  " digits.");
		return;
	}
	

	// Add the infomration to a new row in the table. 
	addNewRow(firstNameString + " " + lastNameString, emailString, filteredString, 
				birthdayString, colorString, genderNode.innerText, activities);
} // end of onSubmitClick


// 4.2. Creates a new table row in the table withthe given parameters. 
// @param {string} name - The full name of the person. 
// @param {string} email - An email address
// @param {string} phone - A phone number
// @param {string} birthday - The date of birth
// @param {string} favColor - A text version of a color
// @param {string} gender - The person's gender. 
// @param {array of string} activites - The activities the person partakes in. 
function addNewRow(name, email, phone, birthday, favColor, gender, activities) {
	// Create a new row element, with row scope
	let newRow = document.createElement("tr");
	newRow.setAttribute("scope", "row");

	// Append the name to the new row. 
	appendTableData(newRow, name);

	// Append the email to the new row. 
	appendTableData(newRow, email);
	
	// Append the phone to the new row. 
	appendTableData(newRow, phone);
	
	// Append the birthday to the new row. 
	appendTableData(newRow, birthday);

	// Append the favorite color to the new row. 
	appendTableData(newRow, favColor);

	// Append the gender to the new row. 
	appendTableData(newRow, gender);

	// Append the activities in an unordered list in a new TD element
	let activitiesTD = document.createElement("td");
	let activitiesList = document.createElement("ul");

	// Add each activity to the list in the TD. 
	for (activity of activities) {
		let activityLI = document.createElement("li");
		activityLI.innerHTML = activity;
		activitiesList.appendChild(activityLI);
	}
	activitiesTD.appendChild(activitiesList);

	// Add the activities TD to the table row. 
	newRow.appendChild(activitiesTD);

	// Add the newRow to the body of the table. The table is not specified by 
	// ID, but there is only one table, so we can search by table tag. 
	document.getElementsByTagName("table")[0].getElementsByTagName("tbody")[0].appendChild(newRow);
} // end of addNewRow


// 4.3. A helper function that appends the specified string to the specified row
// inside of a table data element. 
function appendTableData(row, string) {
	// Create a new table data element for the string
	let tableData = document.createElement("td");
	tableData.innerText = string;
	// Add it to the row element
	row.appendChild(tableData);
} // end of appendTableData


// 4.4 A helper function for the validation of emails. 
// Returns true if the email format is valid. False otherwise. 
function isValidEmail(email) {
	// Handle special case when string is empty
	if (email.length == 0) {
		return false;
	}
	var regexp = /[^@]+@[^@]+/g;
	//Assert that there is only one '@' symbol, and only allowed characters for the 
	// local name, and one or more groups of letters surrounding a '.' after the '@'
	return ((email.match(regexp)||[""])[0].length == email.length) &&
			(email.match("[!#$%&'*+\-/=?^_`{|}~.a-zA-Z0-9]+@([a-zA-Z]+\\.[a-zA-Z]+)+") != null);
} // end of isValidEmail









// 5.1 Opens the details tag for the specified node. 
function openDetails(node) {
	node.setAttribute("open", "");
} // end of openDetails

// 5.2 Closes the details tag
function closeDetails(node) {
	node.removeAttribute("open");
} // end of closeDetails






// 6.1 Concatenates all the span elements' innerHTML, and prints them to 
// the console. 
function concatSpans () {
	spans = document.getElementsByTagName("span");

	let spanHTML = "";

	for (let i=0; i<spans.length; i++) {
		spanHTML += spans[i].innerHTML;
	}

	console.log(spanHTML);
} // end of concatSpans




// 7.1 Displays the current time. 
function displayEarthTime() {
	document.getElementById("earth_time").innerHTML = new Date();
} // end of displayEarthTime



// 8.1 Displays the current time on mars relative to mars days / earth days 
// since Jan 1st, 1970.
function displayMarsTime() {
	document.getElementById("mars_time").innerHTML = 
				new Date(Math.floor(new Date().getTime() * (365.25/687)));
}


// 8.2 Sends a query to get the orbital period of Proxima Centauri b. For 
// use when the Alpha Centauri b button is clicked. 
function displayAlphaCentauriTime() {
	const QUERY_URL = "http://www.astropical.space/astrodb/api-exo.php" +
						"?which=name&limit=Proxima%20Centauri%20b&format=json";
	
	// sendAjaxGet(QUERY_URL, handleExoPlanetResponse);
	fetchQuery(QUERY_URL);
} // end of displayAlphaCentauriTime

// 8.3 Handles the respons from the planet by displaying the resulting time on
// proxima centauri b relative to p.c.b. days / earth days 
// since Jan 1st, 1970.
function handleExoPlanetResponse(xhr) {
	console.log(xhr);
	let planets = JSON.parse(xhr.responseText);
	console.log(planets);
	let period = planets["exoplanets"][0]["per"];

	document.getElementById("acb_time").innerHTML = "Proxima Centauri B Time: " +
				new Date(Math.floor(new Date().getTime() * (365.25/period)));
}

// 8.4 Sends AJAX Requests. Calls the callback function provided when the request 
// successfully returns. 
function sendAjaxGet(url, callback) {
	// Get a new XHR object, or an activeX object if the browser doesn't support XHL
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	// Set the behaviour when the ready state changes.
	xhr.onreadystatechange = function () {
		// If the request is done, and it returned a successful code, handle the weather
		if (this.readyState === 4 && this.status === 200) {
			console.log("Recieved XHR Response: " + xhr.responseText);
			callback(this);
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}


// 8.5 An implementation to get the rotational period using the fetch api 
function fetchQuery(URL) {
	fetch(URL).then(function(response) {
		return response.json();
	}).then(function(planets) {
		console.log(planets);
		let period = planets["exoplanets"][0]["per"];

		document.getElementById("acb_time").innerHTML = "Proxima Centauri B Time: " +
					new Date(Math.floor(new Date().getTime() * (365.25/period)));
	});
}




// 9.1 Changes the background color of the provied node after three seconds
function delayedChangeBackground(node) {
	// Define a callback function that changes the color of the background. 
	let changeBackground = function (localNode) {
		// Define the range of possible rgb values
		const MAX_COLOR = 255;
		const MIN_COLOR = 64;

		// Get random integer RGB values within the specified range. 
		let red = Math.floor(Math.random() * (MAX_COLOR - MIN_COLOR) + MIN_COLOR + 1);
		let green =Math.floor(Math.random() * (MAX_COLOR - MIN_COLOR) + MIN_COLOR + 1);
		let blue = Math.floor(Math.random() * (MAX_COLOR - MIN_COLOR) + MIN_COLOR + 1);


		localNode.setAttribute("style", `background-color: rgb(${red}, ${green}, ${blue})`);
	}

	setTimeout(function () {changeBackground(node);}, 3000);
} // end of delayedChandedBackground






// 10.1 Validated input from 'n1' and 'n2' and them performed the operation 
// that is specified in the select, 'operation'. 
function validateGetAndDoOperation() {
	// Get the two numbers, and the operation to perform. 
	let num1Str = document.getElementById("n1").value;
	let num2Str = document.getElementById("n2").value;
	let opStr = document.getElementById("operation").value;

	let result = undefined;


	// Validate that the input is numberical, and not empty. 
	if (!isNaN(num1Str) && !isNaN(num2Str) && num1Str.length !== 0 && 
			num2Str.length !== 0) 
	{
		result = performOperation(Number(num1Str), Number(num2Str), opStr);
	}
	else {
		console.log("Invalid Numbers: '" + num1Str + "' '" + num2Str);
	}

	// If we received a result, display the result. 
	if (result !== undefined) {
		document.getElementById("result").innerHTML = result;
	}
	else {
		document.getElementById("result").innerHTML = NaN;
	}
} // end of validateGetAndDoOperation


// 10.2 Takes two numbers and a string where the string is the textual representation
// of the arithmetic operation to perform. Returns undefined if the operation is 
// invalid. 
function performOperation(num1, num2, operation) {
	operation = operation.toLowerCase();

	switch(operation) {
		case "add": 
			return num1 + num2;
		case "subtract":
			return num1 - num2;
		case "multiply":
			return num1 * num2;
		case "divide":
			return num1 / num2;
		default:
			console.log("Invalid Operation in 'performOperation': " + operation);
	};
	return;
} // end of performOperation



// 11. Walks all the children nodes from the provided node performing the given 
// callback function upon each node. This uses a 'pre order' traversal. 
function walkTheDom(node, func) {
	// Call the function on the given node. 
	func(node);

	// Then, traverse any childnren this node may have. 
	for (let i=0; i<node.childNodes.length; i++) {
		walkTheDom(node.childNodes[i], func);
	}
} // end of walkTheDom




// Call functions to perform changes to the page or create console output
setAnchorLinks();
disablePluto();
concatSpans();


// Add event listeners for when the submit button is clicked. 
document.getElementById("form-sub").addEventListener("click", alienText);

// Add event listeners for when the mouse goes over a details tag
detailsTags = document.getElementsByTagName("details");
for (let i=0; i<detailsTags.length; i++) {
	detailsTags[i].setAttribute("onmouseenter", "openDetails(this)");
	detailsTags[i].setAttribute("onmouseleave", "closeDetails(this)");
}

// Add event listeners that trigger when the time buttons are clicked. 
document.getElementById("earth_time_check").addEventListener("click", displayEarthTime);
document.getElementById("mars_time_check").addEventListener("click", displayMarsTime);
document.getElementById("acb_time_check").addEventListener("click", displayAlphaCentauriTime);

// Get the first h1 element and add a listener, that when clicked, changes the 
// background color after three seconds. Not very robust, but can't refer to it 
// by ID since it doesn't have one. 
document.getElementsByTagName("h1")[0].addEventListener("click", function() {
	delayedChangeBackground(document.getElementsByTagName("h1")[0]);
});

// Add event listeners for n1, n2, and operation HTML elements. These should 
// perform the operation specified on the two numbers on any change to the 
// fields. 
document.getElementById("n1").addEventListener("input", validateGetAndDoOperation);
document.getElementById("n2").addEventListener("input", validateGetAndDoOperation);
document.getElementById("operation").addEventListener("change",
												validateGetAndDoOperation);


// Walks the DOM, printing out the name of each element, and the number of 
// direct children it possesses. 
// Commented out as it floods the console.  
// walkTheDom(document, function(node) {
// 	console.log(node.nodeName + ": " + node.childNodes.length + " children. ");
// });




