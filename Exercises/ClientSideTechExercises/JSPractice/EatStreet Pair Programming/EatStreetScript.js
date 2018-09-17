/**
 * 
 */

let baseUrl = "https://api.eatstreet.com/publicapi/v1/restaurant/";
let apiKey = "88779e1f17a9547c";

document.getElementById("submitBtn").addEventListener("click", returnMenu);

function returnMenu() {
	let input = document.getElementById("zipcode").value;
	sendAjaxGet(baseUrl + input, displayMenu);
}

function sendAjaxGet(url, callback) {
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Mircrosoft.HTTPRequest"));
	xhr.onreadystatechange = function() {
		if (this.readyState === 4 && this.status) {
			callback(this);
		}
	}
	xhr.open("GET", "https://api.eatstreet.com/publicapi/v1/restaurant/search?method=both&street-address=316+W.+Washington+Ave.+Madison,+WI");
	xhr.setRequestHeader("X-Access-Token", apiKey);
	xhr.send();	
}

function displayMenu(xhr) {
	let restaurants = JSON.parse(xhr.responseText);
	for (restaurant of restaurants) {
		addRow(restaurant.name, restaurant.number);
	}
}

function addRow(name, number) {
	let row = document.createElement("tr");
	
	let cell1 = document.createElement("td");
	let cell2 = document.createElement("td");
	
	row.appendChild(cell1);
	row.appendChild(cell2);

	cell1.innerHTML = name;
	cell2.innerHTML = number;
	
	document.getElementById("restaurant").appendChild(row);
}