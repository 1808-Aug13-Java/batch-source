/**
 * 
 */

// console.log("Hello from the other side ");

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

sendAjaxGet("http://localhost:8082/JDBCDEMO/employees", display);

function display(xhr) {
	employees = JSON.parse(xhr.responseText);
	//console.log(employees)
	employeeArr = employees.employees;
	
	let table = document.getElementById("table");
	
	for(i in employeeArr){
		let newRow = document.createElement("tr");
		
		if(employeeArr[i].location){
			loc = `${employeeArr[i].location.city}, ${employeeArr[i].location.state}`;
		} else {
			loc = "n/a";
		}
		
		if(employeeArr[i].department){
			dpt = employeeArr[i].department.name;
		} else {
			dpt = "n/a";
		}
		
		
		newRow.innerHTML = `<td>${employeeArr[i].name} </td>
		<td> ${loc} </td>
		<td> ${dpt} </td> `;
		
		table.appendChild(newRow);
	 }
		
}