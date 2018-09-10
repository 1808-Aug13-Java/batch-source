

let checkSessionUrl= "http://localhost:8082/p1-jonathan-novoa/session";

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
sendAjaxGet(checkSessionUrl, populateUser);

function populateUser(xhr){
	let response = JSON.parse(xhr.response);
	if(response.username != "null"){
		document.getElementById("user").innerHTML = "You are logged in as: "+ response.username;
	} else {
//		window.location = "http://localhost:8082/ServletLogInDemo/login";
		window.location= "http://localhost:8082/p1-jonathan-novoa/home"
	}
}




function viewAllReim() {
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/employee_info", display);
}

function display(xhr) {
	//edit this for the employee table, display reimbursements
	reimbursements = JSON.parse(xhr.responseText);
	console.log(reimbursements)
	reimArr=reimbursements.reimbursements;
	let table = document.getElementById("table");
	
	for(i in reimArr){
		let newRow = document.createElement("tr");
	
	if(reimArr[i].empId){
		empId = `${reimArr[i].empId}`;
	} 
	
	if(reimArr[i].status){
		status = `${reimArr[i].status}`;
	} 
	if(reimArr[i].resolution){
		reso = `${reimArr[i].resolution}`;
	} 
	if(reimArr[i].amount){
		amount = `${reimArr[i].amount}`;
	} 
	if(reimArr[i].manId){
		manId = `${reimArr[i].manId}`;
	} else{
		manId=0;
	}
	if(reimArr[i].reimId){
		reimId = `${reimArr[i].reimId}`;
	} 
		newRow.innerHTML = `<td>${status} </td>
			<td> ${reso} </td>
			<td> ${manId} </td>
			<td> ${amount} </td>
			<td> ${reimId} </td> `;
			
			table.appendChild(newRow);
		 
	
	}
}
	
//	employeeArr = employees.employees;
//	let table = document.getElementById("table");
//	
//	for(i in employeeArr){
//		let newRow = document.createElement("tr");
//		
//		if(employeeArr[i].location){
//			loc = `${employeeArr[i].location.city}, ${employeeArr[i].location.state}`;
//		} else {
//			loc = "n/a";
//		}
//		
//		if(employeeArr[i].department){
//			dpt = employeeArr[i].department.name;
//		} else {
//			dpt = "n/a";
//		}
//		
//		
//		newRow.innerHTML = `<td>${employeeArr[i].name} </td>
//		<td> ${loc} </td>
//		<td> ${dpt} </td> `;
//		
//		table.appendChild(newRow);
//	 }
		

function logout(){
	window.location="http://localhost:8082/p1-jonathan-novoa/logout"
}