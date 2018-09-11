

console.log("Hello World from directory.js");

const URL = "http://localhost:8080/p1-david-calkins/reimbursement";

function onLoginClick() {
	console.log("Button Clicked");
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	console.log(username + ", " + password);
	
	let postData = "username=" + username +"&password=" + password;
	
	sendAjaxPost("http://localhost:8080/p1-david-calkins/reimbursement", postData, function (xhr) {
		console.log("Reponse.get" + xhr.responseText);
	});
}

function sendAjaxPost(url, postData, callback) {
	// Get a new XHR object, or an activeX object if the browser doesn't support XHL
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	
	
	// Set the behavior when the ready state changes.
	xhr.onreadystatechange = function () {
		// If the request is done, and it returned a successful code, handle the weather
		if (this.readyState === 4 && this.status === 200) {
			//console.log("Received XHR Response: " + xhr.responseText);
			// Call the handler, passing this xhr object in. 
			callback(this);
		}
	}
	
	// Open and send the request. 
	xhr.open("POST", url);
	// Set the content type so the server knows what to expect
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(postData);
}


// Add an event listener for the button. 
 document.getElementById("loginBtn").addEventListener("click", onLoginClick);

