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
	console.log(employee)
//	reimArr=reimbursements.reimbursements;
//	let table = document.getElementById("table");
//	let headerRow = document.createElement("tr");
//	let button = document.getElementById("pending");
//	let button2 = document.getElementById("resolved");
//
//	while(table.childNodes.length >= 2 && table.firstChild!==table.lastChild){
//		table.removeChild(table.lastChild)
//	}
//	
//	headerRow.innerHTML = `	<th>Status</th>
//			<th> Resolution</th>
//			<th>Approved by</th>
//			<th>Amount</th>
//			<th id="last">Reimbursement Id</th> `;
//			table.appendChild(headerRow);
//	
//	if(table.getAttribute("hidden")==""){
//		table.removeAttribute("hidden");
//		button.innerHTML="Hide table";
//		button2.innerHTML="Hide table";
//
//	}else{
//		table.setAttribute("hidden", "");
//		button.innerHTML="View pending reimbursements";
//		button2.innerHTML="View pending reimbursements";
//	}
//
//	
//	
//	for(i in reimArr){
//		let newRow = document.createElement("tr");
//	
//	if(reimArr[i].empId){
//		empId = `${reimArr[i].empId}`;
//	} 
//	
//	if(reimArr[i].status){
//		status = `${reimArr[i].status}`;
//	} 
//	if(reimArr[i].resolution){
//		reso = `${reimArr[i].resolution}`;
//	} 
//	if(reimArr[i].amount){
//		amount = `${reimArr[i].amount}`;
//	} 
//	if(reimArr[i].manId){
//		manId = `${reimArr[i].manId}`;
//	} else{
//		manId=0;
//	}
//	if(reimArr[i].reimId){
//		reimId = `${reimArr[i].reimId}`;
//	} 
//		newRow.innerHTML = `<td>${status} </td>
//			<td> ${reso} </td>
//			<td> ${manId} </td>
//			<td> ${amount} </td>
//			<td> ${reimId} </td> `;
//			
//			table.appendChild(newRow);
//		 
//	
//	}
}