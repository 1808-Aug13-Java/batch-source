function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveObject("Microsoft.HTTPRquest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/p1-alexander-huerta/GetEmployees?username=" + 
		document.getElementById("username").innerHTML, display);

function display(xhr){
	let employees = JSON.parse(xhr.responseText);
	let table = document.getElementById("table");
	let newRow = document.createElement("tr");
		
	newRow.innerHTML = `<td>${employees.fName} ${employees.lName}</td>
                                <td>${employees.userName}</td>`;	
	table.appendChild(newRow);
}