/**
 * 
 */

function sendAjaxGet(url, func){
	
	let xhr = new XMLHttpRequest || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/P1-Cirey-Francis/employeesTable", displayEmployees);

function displayEmployees(xhr){
	
	console.log("Im running!");
	employees = JSON.parse(xhr.responseText);
	console.log(employees.employees);
	
	employeeArr = employees.employees;
	
	let table = document.getElementById("table");
	
	for(i in employeeArr){
		let newRow = document.createElement("tr");
		
		newRow.innerHTML = `<td>${employeeArr[i].id}</td>
		<td>${employeeArr[i].name}</td>
		<td>${employeeArr[i].startDate}</td>
		<td>${employeeArr[i].monthlySalary}</td>
		<td>${employeeArr[i].isManager}</td>`;
		table.appendChild(newRow);
	}
}