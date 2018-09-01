

// 1. Set the anchor tags to folow their actual links. 
function setAnchorLinks() {
	// Get all the anchor tags
	let links = document.getElementsByTagName("a");

	// Go through each anchor tag and set it's link accordingly. 
	for (link of links) {
		console.log("Link: " + link.getAttribute("name"));
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
			// console.log("Checked: " + checkbox.value);
			activities.push(checkbox.innerText);
		}
		else {
			// For testing
			// console.log("Not Checked: " + checkbox.value);
		}
	}

	// If any of the values are empty, do nothing as the input is invalid
	//TODO: Possibly implement adding red outline to individual invalid fields. 
	if (firstNameString == "" || lastNameString == "" || emailString == "" || 
		phoneString == "" || birthdayString == "" || planetString == "" || 
		colorString == "" || genderNode == null) 
	{
		console.log("Invalid Input");
		return;
	}

	// Add the infomration to a new row in the table. 
	addNewRow(firstNameString + lastNameString, emailString, phoneString, 
				birthdayString, colorString, genderNode.value, activities);
} // end of onSubmitClick


// 4.2. Creates a new table row in the table
//TODO: Add parameter contents 
function addNewRow(name, email, phone, birthday, favColor, gender, activities) {

} // end of addNewRow







setAnchorLinks();
disablePluto();
// Add event listeners for when the submit button is clicked. 
document.getElementById("form-sub").addEventListener("click", alienText);
document.getElementById("form-sub").addEventListener("click", onSubmitClick);


