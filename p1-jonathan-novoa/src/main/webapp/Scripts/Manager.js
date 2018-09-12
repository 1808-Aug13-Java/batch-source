

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


function oneReim(){
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/one_reim", displayReim);
}

function viewPendReim() {
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/pend_reim", displayReim);
}
function viewResReim(){
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/res_reim", displayReim);
}

function viewEmployees(){
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/view_emp", displayEmp);
}
function viewAllEmp() {
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/view_all_emp", displayEmp);
}



function displayReim(xhr) {
	
	//edit this for the employee table, display reimbursements
	reimbursements = JSON.parse(xhr.responseText);
	console.log(reimbursements)
	empArr=reimbursements.reimbursements;
	let table = document.getElementById("table");
	let headerRow = document.createElement("tr");
	let button = document.getElementById("pending");
	let button2 = document.getElementById("resolved");

	while(table.childNodes.length >= 2 && table.firstChild!==table.lastChild){
		table.removeChild(table.lastChild)
	}
	
	headerRow.innerHTML = `	<th> Employee Id</th> 
			<th>Status</th>
			<th> Resolution</th>
			<th>Resolved by</th>
			<th>Amount</th>
			<th >Reimbursement Id
				<th> `;
			table.appendChild(headerRow);
	
	if(table.getAttribute("hidden")==""){
		table.removeAttribute("hidden");
		button.innerHTML="Hide table";
		button2.innerHTML="Hide table";

	}else{
		table.setAttribute("hidden", "");
		button.innerHTML="View pending reimbursements";
		button2.innerHTML="View resolved reimbursements";
	}

	
	
	for(i in empArr){
		let newRow = document.createElement("tr");
		
	
	if(empArr[i].empId){
		empId = `${empArr[i].empId}`;
	} 
	
	if(empArr[i].status){
		status = `${empArr[i].status}`;
	} 
	if(empArr[i].resolution){
		reso = `${empArr[i].resolution}`;
	} 
	if(empArr[i].amount){
		amount = `${empArr[i].amount}`;
	} 
	if(empArr[i].manId){
		manId = `${empArr[i].manId}`;
	} else{
		manId=0;
	}
	if(empArr[i].reimId){
		reimId = `${empArr[i].reimId}`;
	} 

		newRow.innerHTML = `<td> ${empId}</td> 
			<td>${status} </td>
			<td> ${reso} </td>
			<td> ${manId} </td>
			<td> ${amount} </td>
			<td> ${reimId} </td> `;
			
			table.appendChild(newRow);
		 
	
	}
}


function displayEmp(xhr) {

	//edit this for the employee table, display reimbursements
	employees = JSON.parse(xhr.responseText);
	console.log(employees)
	empArr = employees.employees;
	let table = document.getElementById("table2");
	let headerRow = document.createElement("tr");
	let button = document.getElementById("employee");
	let button2 = document.getElementById("emp_man");

	while (table.childNodes.length >= 2 && table.firstChild !== table.lastChild) {
		table.removeChild(table.lastChild)
	}

	headerRow.innerHTML = `	<th>Employee ID</th>
			<th>First</th>
			<th>Last</th>
			<th>email</th>
			<th>Is Manager<th> `;
	table.appendChild(headerRow);

	if (table.getAttribute("hidden") == "") {
		table.removeAttribute("hidden");
		button.innerHTML = "Hide table";
		button2.innerHTML = "Hide table";

	} else {
		table.setAttribute("hidden", "");
		button.innerHTML = "View only Employees";
		button2.innerHTML = "View Employees and Managers";
	}

	for (i in empArr) {
		let newRow = document.createElement("tr");

		if (empArr[i].employeeID) {
			empId = `${empArr[i].employeeID}`;
		}

		if (empArr[i].firstName) {
			status = `${empArr[i].firstName}`;
		}
		if (empArr[i].lastName) {
			reso = `${empArr[i].lastName}`;
		}
		if(empArr[i].email){
			email=`${empArr[i].email}`;
		}
		// if (empArr[i].e_key) {
		// 	pass = `${empArr[i].e_key}`;
		// }
		if (empArr[i].isMan==0) {
			manId = "No";
	 	} else {
	 		manId = "yes";
	 	}
 
		newRow.innerHTML = `<td>${empId} </td>
			<td> ${status} </td>
			<td> ${reso} </td>
			<td> ${email}</td>
			<td> ${manId} </td> `;

	table.appendChild(newRow);


	}
}

		

function logout(){
	window.location="http://localhost:8082/p1-jonathan-novoa/logout"
}

function goHome(){
	window.location = "http://localhost:8082/p1-jonathan-novoa/manager";
}

function search(){
	var input, filter, table, tr, td, i;
	input = document.getElementById("userInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("table");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			if (td.innerHTML.indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}