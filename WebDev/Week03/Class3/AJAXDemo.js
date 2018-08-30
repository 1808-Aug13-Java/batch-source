
let baseURL = "http://api.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q="


document.getElementById("submitBtn").addEventListener("click", searchWeather);

function searchWeather() {
	let input = document.getElementById("zipcode").value;
	console.log(input);
	
	if (input.length !== 5 | input<0) {
		document.getElementById("alert").innerHTML = "Invalid Input";
	}
	else{
		document.getElementById("alert").innerHTML = "";
		sendAjaxGet(baseURL + input, handleWeatherResponse);
	}
}

function handleWeatherResponse(xhr) {
	console.log(xhr);
	let weather = JSON.parse(xhr.responseText);
	console.log(weather);
	console.log(document.getElementById("image"));
	document.getElementById("location").innerHTML = 
		`Weather for ${weather.location.name}, ${weather.location.region}`;
	
	console.log(document.getElementById("image"));// Returns Null for some reason.
	document.getElementById("image").setAttribute("src", "HTTP:" + weather.curent.condition.icon);
	document.getElementById("status").innerHTML = weather.current.condition.text;
	document.getElementById("temperature").innerHTML = 
		`${weather.current.temp_f} F feels like `;
}

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







