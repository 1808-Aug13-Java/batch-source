/**
 * 
 */
console.log("Main Page Script Loaded");


const URL = "http://localhost:8080/p1-david-calkins/reimbursement";

const REQUEST = "resourceRequest"

// Attempts to logout, sending client to login page. 
function logout() {
	console.log("Requested Logout");
	sendAjaxGet(URL + "?" + REQUEST + "=" + "logout", function () {
		console.log("Logged Out");
		window.location.reload(true);
	});
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
	
	// Open and send the request. 
	xhr.open("GET", url);
	xhr.send();
}
