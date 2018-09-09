/**
 * 
 */

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

sendAjaxGet("http://localhost:8082/Project-1-jeremiah-laforge/GetEmployees", display);

function display(xhr){
	let employees = JSON.parse(xhr.responseText);
	
	let employeeArr = employees.employees;
	
	let table = document.getElementById("table");
	
	for(i in employeeArr){
		let newRow = document.createElement("tr");
		
		newRow.innerHTML = `<td>${employeeArr[i].fName} ${employeeArr[i].lName}</td>
                                <td>${employeeArr[i].userName}</td>`;
		
		table.appendChild(newRow);
	}
}