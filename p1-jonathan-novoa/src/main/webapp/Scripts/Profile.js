/**
 * 
 */

let url="http://localhost:8082/p1-jonathan-novoa/employee_profile";

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}//standard XML ajax request

sendAjaxGet(url,profileInfo);

function profileInfo(xhr){
	//edit this for the employee table, display reimbursements
	employee = JSON.parse(xhr.responseText);
//	console.log(employee)
//	var employeeArr= employee.employee;
	
	let table = document.getElementById("table");	
		let newRow = document.createElement("tr");
		firstName = employee.employee.firstName;
		console.log(firstName);
		lastName = employee.employee.lastName;
		username = employee.employee.email;
		password = employee.employee.e_key;
	
	
	
		newRow.innerHTML = `<td> ${firstName} </td>
			<td> ${lastName} </td>
			<td> ${username} </td>
			<td> ${password} </td> `;

			
			table.appendChild(newRow);
		 
	// document.getElementById("fname").setAttribute("placeholder",`${firstName}`);
	document.getElementById("fname").value=`${firstName}`;
	document.getElementById("lname").value = `${lastName}`;
	document.getElementById("email").value = `${username}`;
	document.getElementById("password").value = `${password}`;
	}

	function goHome(){
		window.location ="http://localhost:8082/p1-jonathan-novoa/employee";
	}
	
