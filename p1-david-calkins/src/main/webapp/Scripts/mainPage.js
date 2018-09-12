/**
 * 
 */
console.log("Main Page Script Loaded");


const URL = "http://localhost:8080/p1-david-calkins/reimbursement";

const REQUEST = "resourceRequest"
	
var lastXHR = undefined;


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
		console.log(reimbursements);
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
		console.log(reimbursements);
		displayReimbursements(reimbursements);
	});
	return false;
}



// ============================================================
// Display Functions 
// ============================================================

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
}






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
	LastXHR = xhr;
}


