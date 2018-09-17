/**
 * 
 */

let checkSessionUrl = "http://localhost:8082/ServletLoginDemo/session";

function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Mircrosoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState===4 && this.status===200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet(checkSessionUrl, populateUser);

function populateuser(xhr) {
	let reponse = JSON.parse(xhr.reponse);
	if (response.username != "null") {
		document.getElementById("user").innerHTML = "You are logged in as: "+response.username;
	} else {
		window.location = "http://localhost:8082/ServletLoginDemo/login";
	}
}