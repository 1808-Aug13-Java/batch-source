/**
 * 
 */
console.log("Main Page Script Loaded");


const URL = window.location.href;

const REQUEST = "resourceRequest"
	
var lastXHR = undefined;

// Used to render the page differently if the user is a manager. 
// Any possible exposed controls still have validation on the server side. 
var isManager = false;

// ============================================================
// Request Functions 
// ============================================================

// Attempts to logout, sending client to login page. 
function logout() {
	console.log("Requested Logout");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "logout", function () {
		console.log("Logged Out");
		window.location.reload(true);
	});
}

// Attempts to get pending reimbursements
function getPending() {
	console.log("Requested Pending");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "pending", function (xhr) {
		
		// Display the reimbursements
		let reimbursements = JSON.parse(xhr.responseText);
//		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}

// Manager Function. Gets all pending requests. 
function getAllPending() {
	console.log("Requested All Pending");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "allPending", function (xhr) {
		
		// Display the reimbursements
		let reimbursements = JSON.parse(xhr.responseText);
//		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}


//Attempts to get resolved reimbursements
function getResolved() {
	console.log("Requested Resolved");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "resolved", function (xhr) {
		
		// Display the reimbursements
		let reimbursements = JSON.parse(xhr.responseText);
//		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}

//Manager Function. Gets all resolved requests. 
function getAllResolved() {
	console.log("Requested All Resolved");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "allResolved", function (xhr) {
		
		// Display the reimbursements
		let reimbursements = JSON.parse(xhr.responseText);
//		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}


//Manager Function. Gets all employees. 
function getAllEmployees() {
	console.log("Requested All Employees");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "allEmployees", function (xhr) {
		
		// Display the reimbursements
		let employees = JSON.parse(xhr.responseText);
		console.log(employees);
		displayEmployees(employees);
	});
	return false;
}


function getEmployeeReimbursements (empId) {
	console.log("Clicked Employee: " + empId);
	sendAjaxGet(URL + "?" + REQUEST + "=" + "empPending&id=" + empId, function (xhr) {
		// Display the reimbursements
		let reimbursements = JSON.parse(xhr.responseText);
		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}



//Attempts to get the user's profile
function getProfile() {
	console.log("Requested Profile");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "profile", function (xhr) {
		// Display the profile
		let profile = JSON.parse(xhr.responseText);
//		console.log(profile);
		displayProfile(profile);
	});
	return false;
}


// Attempts to update the user's profile
function updateProfile() {
	console.log("Requested UpdateProfile");
	
	// Get the new name
	let newName = document.getElementById("nameInput").value;
	
	sendAjaxGet(URL + "?" + REQUEST + "=" + "profile&name=" + newName, function (xhr) {
		// Display the profile
		let profile = JSON.parse(xhr.responseText);
//		console.log(profile);
		displayProfile(profile);
	});
	return false;
}


// Attempts to submit a reimbursement request
function submitReimRequest() {
console.log("Requested New Reimbursement");
	
	// Get the user values for the new reimbursement request
	let amount = document.getElementById("ammountId").value;
	let description = document.getElementById("descriptionId").value;
	
	sendAjaxGet(URL + "?" + REQUEST + "=" + "newReim&amount=" + amount 
				+ "&description=" + description, function (xhr) 
	{
		displaySubmitted();
	});
	return false;
}


// Approves or denies the reimbursement based on the reimId and the boolean 
// which specifies if it is approved or denied. 
// Manager Function
function approveReimbursement(remId, approve) {
	console.log("Requested Approve/Deny");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "approveDeny&remId=" + remId + "&bool=" + approve, function (xhr) {
		
		// Display the reimbursements
		console.log("Successfully Approved or Denied");
		
	});
	
	// Remove the buttons so they can't be clicked again. 
	document.getElementById("approve"+remId).remove();
	document.getElementById("deny"+remId).remove();
	return false;
}








// ============================================================================
// Display Functions 
// ============================================================================

// Displays a message saying something was submitted successfully 
function displaySubmitted() {
	// Get the container for the output
	let container = document.getElementById("outputContainer");
	// Clear the old output, and display message
	container.innerHTML = "<h4>Successfully Submitted</h4>";
} // end of displaySubmitted


// Displays a form for submitting a reimbursement request
function displaySubmitReimbursement() {
	// Get the container for the output
	let container = document.getElementById("outputContainer");
	// Clear the old output 
	container.innerHTML = "";
	
	// Add a header for this form
	let header = document.createElement("h4");
	header.innerHTML = "Submit Reimbursement Request";
	container.appendChild(header);
	
	// Create new elements for the form
	let label1 = document.createElement("label");
	let amountInput = document.createElement("input");
	let label2 = document.createElement("label");
	let descriptionInput = document.createElement("input");
	let submitButton = document.createElement("button");
	
	// Set the inner html of the labels and button
	label1.innerHTML = "Amount: ";
	label2.innerHTML = "Description: ";
	submitButton.innerHTML = "Submit Request";
	
	// Apply attributes, id's for getting input, input type, and an event handler
	amountInput.setAttribute("id", "ammountId");
	amountInput.setAttribute("type", "number");
	descriptionInput.setAttribute("id", "descriptionId");
	descriptionInput.setAttribute("type", "text");
	submitButton.setAttribute("onclick", "submitReimRequest()");
	
	// Append the new elements to the HTML page.
	container.appendChild(label1);
	container.appendChild(amountInput);
	container.appendChild(document.createElement("br"));
	container.appendChild(label2);
	container.appendChild(descriptionInput);
	container.appendChild(document.createElement("br"));
	container.appendChild(submitButton);
	
} // end of displaySubmitReimbursement


// Displays a profile, along with a form to update it. 
function displayProfile(profile) {
	// Get the container for the output
	let container = document.getElementById("outputContainer");
	// Clear the old output 
	container.innerHTML = "";
	
	// Create new header elements to display the profile information
	let idHeader = document.createElement("h4");
	let nameHeader = document.createElement("h3");
	let usernameHeader = document.createElement("h3");
	
	// Set the contents of the elements to the profile info
	idHeader.innerHTML = "ID: " + profile["id"];
	nameHeader.innerHTML = "Name: " + profile["name"];
	usernameHeader.innerHTML = "Username: " + profile["username"];
	
	// Add the headers to the output container
	container.appendChild(idHeader);
	container.appendChild(nameHeader);
	container.appendChild(usernameHeader);
	
	
	// Create a simple input area for updating the user's name
	let label = document.createElement("label");
	label.innerHTML = "Change Name: ";
	let input = document.createElement("input");
	input.setAttribute("id", "nameInput");
	let inputButton = document.createElement("button");
	inputButton.setAttribute("onclick", "updateProfile()");
	inputButton.innerHTML = "Update Name";
	
	container.appendChild(label);
	container.appendChild(input);
	container.appendChild(inputButton);
} // end of displayProfile



// Displays the specified employees (in JSON) as a table. 
function displayEmployees(employees) {
	// Create the layout of the table
	let table = document.createElement("table");
	let thead = document.createElement("thead");
	let tbody = document.createElement("tbody");
	
	// Create 9 columns
	let trHead = document.createElement("tr");
	
	// Creates a new column and ads it to the trHead
	function addColumn (newColumnText) {
		let th = document.createElement("th");
		th.innerHTML = newColumnText;
		trHead.appendChild(th);
	}
	
	addColumn("ID");
	addColumn("Name");
	addColumn("Username");
	addColumn("");
	
	
	// Add them to the table head. 
	thead.appendChild(trHead);
	table.appendChild(thead);
	
	
	
	// A function to facilitate adding elements to a row
	function addToRow(tableRow, newData) {
		let td = document.createElement("td");
		td.innerHTML = newData;
		tableRow.appendChild(td);
	}
	// For each reimbursement, create a row and add it to the table
	for (let i=0; i < employees.length; i++) {
		let trBody = document.createElement("tr");
		
		addToRow(trBody, employees[i]["id"])
		addToRow(trBody, employees[i]["name"]);
		addToRow(trBody, employees[i]["username"]);
		
		// Create a button that views an employee's reimbursements when clicked. 
		let button = document.createElement("button");
		button.innerHTML = "View Reimbursements";
		button.addEventListener("click", function() {getEmployeeReimbursements(employees[i]["id"]);});
		
		// Put the button in the table
		let td = document.createElement("td");
		td.appendChild(button);
		trBody.appendChild(td);
		
		tbody.appendChild(trBody);
	}
	
	
	// Add the elements to the table
	table.appendChild(tbody);
	
	// Remove the old output. 
	document.getElementById("outputContainer").innerHTML = "";
	
	// Add the table to the output. 
	document.getElementById("outputContainer").appendChild(table);
} // end of displayEmployees



// Displays the specified reimbursements (in JSON) as a table. 
function displayReimbursements(reimbursements) {
	// Create the layout of the table
	let table = document.createElement("table");
	let thead = document.createElement("thead");
	let tbody = document.createElement("tbody");
	
	// Create 9 columns
	let trHead = document.createElement("tr");
	
	// Creates a new column and ads it to the trHead
	function addColumn (newColumnText) {
		let th = document.createElement("th");
		th.innerHTML = newColumnText;
		trHead.appendChild(th);
	}
	
	addColumn("Requster Name");
	addColumn("Requster Username");
	addColumn("Status");
	addColumn("Amount");
	addColumn("Submit Date");
	addColumn("Description");
	addColumn("Resolved By");
	addColumn("Resolve Date");
	addColumn("Reason");
	
	// If this is a manager requesting, add another column for buttons
	if (isManager) {
		addColumn("Approve/Deny");
	}
	
	// Add them to the table head. 
	thead.appendChild(trHead);
	table.appendChild(thead);
	
	
	
	// A function to facilitate adding elements to a row
	function addToRow(tableRow, newData) {
		let td = document.createElement("td");
		td.innerHTML = newData;
		tableRow.appendChild(td);
	}
	
	
	// For each reimbursement, create a row and add it to the table
	for (let i=0; i < reimbursements.length; i++) {
		let trBody = document.createElement("tr");
		
		addToRow(trBody, reimbursements[i]["requester"]["name"]);
		addToRow(trBody, reimbursements[i]["requester"]["username"]);
		
		// Get the status
		let status = reimbursements[i]["status"];
		addToRow(trBody, reimbursements[i]["status"]);
		addToRow(trBody, reimbursements[i]["amount"]);
		addToRow(trBody, reimbursements[i]["submitDate"]);
		addToRow(trBody, reimbursements[i]["description"]);
		
		// If the request is pending, none of the other fields are applicable
		if (status === "Pending") {
			for (let i=0; i<3; i++) {
				addToRow(trBody, "N/A");
			}
			// If this is a manager, in the last column, add two buttons, one for
			// approve, one for deny. 
			if (isManager) {
				let approveButton = document.createElement("button");
				approveButton.addEventListener("click", function(){approveReimbursement(reimbursements[i]["id"], true);});
				approveButton.innerHTML = "Approve";
				approveButton.setAttribute("id", "approve"+reimbursements[i]["id"])
				let denyButton = document.createElement("button");
				denyButton.addEventListener("click", function(){approveReimbursement(reimbursements[i]["id"], false);});
				denyButton.innerHTML = "Deny";
				denyButton.setAttribute("id", "deny"+reimbursements[i]["id"])
				
				// Put the buttons in the table
				let td = document.createElement("td");
				td.appendChild(approveButton);
				td.appendChild(denyButton);
				trBody.appendChild(td);
				
				
				// Put the button in the table
//				td = document.createElement("td");
//				td.appendChild(denyButton);
//				trBody.appendChild(td);
			}
		}
		// Otherwise, add he applicable table rows. 
		else {
			
			addToRow(trBody, reimbursements[i]["resolvedBy"]["name"]);
			addToRow(trBody, reimbursements[i]["resolveDate"]);
			addToRow(trBody, reimbursements[i]["reason"]);
		}
		
		tbody.appendChild(trBody);
	}
	
	
	// Add the elements to the table
	table.appendChild(tbody);
	
	// Remove the old output. 
	document.getElementById("outputContainer").innerHTML = "";
	
	// Add the table to the output. 
	document.getElementById("outputContainer").appendChild(table);
} // end of displayReimbursements






function sendAjaxGet(url, callback) {
	// Get a new XHR object, or an activeX object if the browser doesn't support XHL
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	// Set the behaviour when the ready state changes.
	xhr.onreadystatechange = function () {
		// If the request is done, and it returned a successful code, handle the weather
		if (this.readyState === 4 && this.status === 200) {
			//console.log("Recieved XHR Response: " + xhr.responseText);
			// Call the handler, passing this xhr object in. 
			callback(this);
		}
	}
	
	// Cancel any previous pending request
	if (lastXHR != undefined) {
		lastXHR.abort();
	}
	
	// Open and send the request. 
	xhr.open("GET", url);
	xhr.send();
	
	// Set the last Request that we made. 
	lastXHR = xhr;
}





