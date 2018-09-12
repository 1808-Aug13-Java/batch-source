

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
function viewResReim(){
	sendAjaxGet("http://localhost:8082/p1-jonathan-novoa/resolved_reim", display);
}
function goToNew(){
	window.location="http://localhost:8082/p1-jonathan-novoa/newreim";
}

function display(xhr) {
	
	//edit this for the employee table, display reimbursements
	reimbursements = JSON.parse(xhr.responseText);
	console.log(reimbursements)
	reimArr=reimbursements.reimbursements;
	let table = document.getElementById("table");
	let headerRow = document.createElement("tr");
	let button = document.getElementById("pending");
	let button2 = document.getElementById("resolved");

	while(table.childNodes.length >= 2 && table.firstChild!==table.lastChild){
		table.removeChild(table.lastChild)
	}
	
	headerRow.innerHTML = `	<th>Status</th>
			<th> Resolution</th>
			<th>Approved by</th>
			<th>Amount</th>
			<th id="last">Reimbursement Id</th> `;
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

		

function logout(){
	window.location="http://localhost:8082/p1-jonathan-novoa/logout"
}

function goHome(){
	window.location = "http://localhost:8082/p1-jonathan-novoa/employee";
}