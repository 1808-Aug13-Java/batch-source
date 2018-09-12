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

sendAjaxGet("http://localhost:8082/Project-1-jeremiah-laforge/GetEmployeeByUserName", display);

function display(xhr){
	let employees = JSON.parse(xhr.responseText);
	
	// let employeeArr = employees.employees;
	
	let nav = document.getElementById("navBar");
	let uname = document.getElementById("usernamedisplay");
	
	// for(i in employeeArr){
		let newRow = document.createElement("li");
		newRow.className = "nav-item";
		
		newRow.innerHTML = `<a class="nav-link" href=#>${employees.userName}</a>`;
		nav.appendChild(newRow);
		uname.innerHTML = `Profile Name: ${employees.userName} Name: ${employees.fName} ${employees.lName}`;
	// }
}