/**
 * 
 */

//console.log("Hello world from directory.js");

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/Project1/employee", display);

function display(xhr){
	employees = JSON.parse(xhr.responseText);
	employeeArr = employees.employees;
	
	let table = document.getElementById("table");
	
	for(i in employeeArr){
		let newRow = document.createElement("tr");
		
		if(employeeArr[i].name){
			n = employeeArr[i].name;
		} else {
			n = "n/a";
		}
		
		if(employeeArr[i].position){
			pos = employeeArr[i].position;
		} else {
			pos = "n/a";
		}
		
		if(employeeArr[i].username){
			user = employeeArr[i].username;
		} else {
			pos = "n/a";
		}
		
		if(employeeArr[i].password){
			pass = employeeArr[i].password;
		} else {
			pass = "n/a";
		}
		
		if(employeeArr[i].reportsTo){
			managerId = employeeArr[i].reportsTo;
		} else {
			managerId = "n/a";
		}
	
		newRow.innerHTML = `<td>${employeeArr[i].id} </td>
		<td>${n} </td>
		<td> ${pos} </td>
		<td>${user} </td>
		<td>${pass} </td>
		<td> ${managerId} </td> `;
		
		table.appendChild(newRow);
	}	
}