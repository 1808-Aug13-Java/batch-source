function sendAjaxGet(url, func){
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	xhr.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			func(this);
		}
	}
	xhr.open("GET", url)
	xhr.send();
}

//Get id from url
var str = ""+window.location.href;
var index = str.search("id=");
console.log(str.slice(index+3));

function viewPending(){
	sendAjaxGet("http://localhost:8082/p1-newton-hoac/reimbursement", displayAllPending);
}

function viewResolved(){
	sendAjaxGet("http://localhost:8082/p1-newton-hoac/reimbursement", displayAllResolved);
}

function viewEmployees(){
	sendAjaxGet("http://localhost:8082/p1-newton-hoac/profile", displayProfiles);
}

function searchEmployee(){
	let id = document.getElementById("empId").value;
	sendAjaxGet("http://localhost:8082/p1-newton-hoac/reimbursement?id=" + id, displayAllReimbursements);
}

// Display all pending reimbursements
function displayAllPending(xhr){
	reimbs = JSON.parse(xhr.responseText);	
	let table = document.getElementById("reimbTable");
	reimbArr = reimbs.reimbursement;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in reimbArr){
		let newRow = document.createElement("tr");
		
		id = `${reimbArr[i].rid}`;
		
		eid = `${reimbArr[i].empId}`;
		
		if(reimbArr[i].manId){
			mid = `${reimbArr[i].manId}`;
		} else {
			mid = "n/a";
		}
		
		status = `${reimbArr[i].status}`;
		
		descript = `${reimbArr[i].description}`;
		
		if(reimbArr[i].action){
			action = `${reimbArr[i].action}`;
		} else {
			action = "n/a";
		}
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${eid}</td>
		<td>${mid}</td>
		<td>${status}</td>
		<td>${descript}</td>
		<td>${action}</td>`;
		
		// Append if pending
		if(status == "PENDING"){
			table.appendChild(newRow);
		}
	}
}

// Display all Resolved Reimbursements
function displayAllResolved(xhr){
	reimbs = JSON.parse(xhr.responseText);	
	let table = document.getElementById("reimbTable");
	reimbArr = reimbs.reimbursement;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in reimbArr){
		let newRow = document.createElement("tr");
		
		id = `${reimbArr[i].rid}`;
		
		eid = `${reimbArr[i].empId}`;
		
		if(reimbArr[i].manId){
			mid = `${reimbArr[i].manId}`;
		} else {
			mid = "n/a";
		}
		
		status = `${reimbArr[i].status}`;
		
		descript = `${reimbArr[i].description}`;
		
		if(reimbArr[i].action){
			action = `${reimbArr[i].action}`;
		} else {
			action = "n/a";
		}
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${eid}</td>
		<td>${mid}</td>
		<td>${status}</td>
		<td>${descript}</td>
		<td>${action}</td>`;
		
		// Append if resolved
		if(status == "RESOLVED"){
			table.appendChild(newRow);
		}
	}
}

// Disply all employees
function displayProfiles(xhr){
	profiles = JSON.parse(xhr.responseText);	
	let table = document.getElementById("profTable");
	profileArr = profiles.profiles;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in profileArr){
		let newRow = document.createElement("tr");
		
		id = `${profileArr[i].id}`;
		
		name = `${profileArr[i].firstname} ${profileArr[i].lastname}`;
		
		if(profileArr[i].locId){
			loc = `${profileArr[i].locId.street}, ${profileArr[i].locId.city}, ${profileArr[i].locId.state} ${profileArr[i].locId.zip}`;
		} else {
			loc = "n/a";
		}
		
		phone = `${profileArr[i].phone}`;
		
		email = `${profileArr[i].email}`;
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${name}</td>
		<td>${loc}</td>
		<td>${phone}</td>
		<td>${email}</td>`;
		
		table.appendChild(newRow);
	}
}

function displayAllReimbursements(xhr){
	reimbs = JSON.parse(xhr.responseText);	
	let table = document.getElementById("reimbTable");
	reimbArr = reimbs.reimbursement;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in reimbArr){
		let newRow = document.createElement("tr");
		
		id = `${reimbArr[i].rid}`;
		
		eid = `${reimbArr[i].empId}`;
		
		if(reimbArr[i].manId){
			mid = `${reimbArr[i].manId}`;
		} else {
			mid = "n/a";
		}
		
		status = `${reimbArr[i].status}`;
		
		descript = `${reimbArr[i].description}`;
		
		if(reimbArr[i].action){
			action = `${reimbArr[i].action}`;
		} else {
			action = "n/a";
		}
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${eid}</td>
		<td>${mid}</td>
		<td>${status}</td>
		<td>${descript}</td>
		<td>${action}</td>`;
		
		
		table.appendChild(newRow);
		
	}
}