

console.log("Hello World from directory.js");

function onLoginClick() {
	console.log("Button Clicked");
}

function sendAjaxPost(url, postData, callback) {
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
	xhr.open("POST", url);
	xhr.send(postData);
}



// document.getElementById("loginBtn").addEventListener("click", onLoginClick);

