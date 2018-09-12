/**
 * 
 */

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

sendAjaxGet("http://localhost:8082/Project1/allpendingreimb", display);

function display(xhr){
	reimbursements = JSON.parse(xhr.responseText);
	reimbursementArr = reimbursements.reimbursements;
	
	let table = document.getElementById("table");
	
	for(i in reimbursementArr){
		let newRow = document.createElement("tr");
		
		if(reimbursementArr[i].reason){
			reas = reimbursementArr[i].reason;
		} else {
			reas = "n/a";
		}
		
		if(reimbursementArr[i].status){
			stat = reimbursementArr[i].status;
		} else {
			stat = "n/a";
		}
		
		if(reimbursementArr[i].amount){
			amt = reimbursementArr[i].amount;
		} else {
			amt = "n/a";
		}
		
		if(reimbursementArr[i].submitDate){
			sdate = reimbursementArr[i].submitDate;
		} else {
			sdate = "n/a";
		}
		
		if(reimbursementArr[i].requestHandlerId){
			man = reimbursementArr[i].requestHandlerId;
		} else {
			man = "n/a";
		}
		
		if(reimbursementArr[i].empId){
			emplid = reimbursementArr[i].empId;
		} else {
			emplid = "n/a";
		}
		
		newRow.innerHTML = `<td>${reimbursementArr[i].id} </td>
		<td> ${reas} </td>
		<td> ${stat} </td>
		<td> ${amt} </td>
		<td> ${sdate} </td>
		<td> ${man} </td>
		<td> ${emplid} </td> `;
		
		table.appendChild(newRow);
		
	}	
}