function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200)
			func(this);
	}
	xhr.open("GET",url);
	xhr.send();
}

function sendAjaxPost(url, fun) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200)
			fun(this);
	}
	xhr.open("POST",url);
	xhr.send();
}

function filterEmployees(element){
    switch(element.value){
        case("Accepted"): case("Denied"):
            document.getElementById("searchById").disabled = true;
        break;
        case("Enter Employee ID"):
        case("Enter Manager ID"):
            document.getElementById("searchById").disabled = false;
        break;
        case("Select a Filter"):
        default:
            document.getElementById("searchById").disabled = true;
    }
}

function updateFilter(element){
    if (element.value)
        {}//okay cool, search for people and populate the table
}

function enableManager(){
    let check = document.getElementById("manager");
    document.getElementById("managerRow").hidden = !check.checked;
    document.getElementById("managerRow2").hidden = !check.checked;
}

function populatePending(self) {
	var body = self.childNodes[2].childNodes[3];
	
	if(body.innerHTML === ""){
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/employeeData/?directive=" + self.id,
		function(xhr){
		  let empList = JSON.parse(xhr.responseText).employees;
		  for (emp of empList){
			  let newRow = document.createElement("tr");
			  requestID = emp.requestID;
			  amount = (emp.amount * 100 | 0) / 100 + "";
			  compare = amount.length - amount.indexOf(".");
			  amount = compare == 3?amount:compare == 2?amount+"0":amount+".00";
			  description = emp.description.length > 50? emp.description.slice(0,47) + "..." : emp.description;
			  newRow.innerHTML = `<td>${requestID}</td><td>$${amount}</td><td>${description}</td>`;
			  body.appendChild(newRow);
	      }
		  if(body.innerHTML === "")
			  body.innerHTML = "<tr>None available</tr>"
		});
	}
}

function populateReviewed(self) {
	var body = self.childNodes[2].childNodes[3];
	
	if(body.innerHTML === ""){
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/employeeData/?directive=" + self.id,
		function(xhr){
		  let empList = JSON.parse(xhr.responseText).employees;
		  console.log(xhr.responseText);
		  for (emp of empList){
			  manName=emp[1];
			  emp=emp[0];
			  let newRow = document.createElement("tr");
			  requestID = emp.requestID;
			  amount = (emp.amount * 100 | 0) / 100 + "";
			  compare = amount.length - amount.indexOf(".");
			  amount = compare == 3?amount:compare == 2?amount+"0":amount+".00";
			  status = emp.status > 0? "Approved":emp.status < 0? "Denied":"Further Review"; 
			  description = emp.description.length > 50? emp.description.slice(0,47) + "..." : emp.description;
			  newRow.innerHTML = `<td>${requestID}</td><td>$${amount}</td><td>${status}</td>
				  		<td>${manName}</td><td>${description}</td>`;
			  body.appendChild(newRow);
	      }
		  if(body.innerHTML === "")
			  body.innerHTML = "<tr>None available</tr>"
		});
	}
}

function populateManPending(self) {
	var body = self.childNodes[2].childNodes[3];
	
	if(body.innerHTML === ""){
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/employeeData/?directive=" + self.id,
		function(xhr){
		  let empList = JSON.parse(xhr.responseText).employees;
		  for (emp of empList){
			  empName = emp[1];
			  emp = emp[0];
			  let newRow = document.createElement("tr");
			  requestID = emp.requestID;
			  amount = (emp.amount * 100 | 0) / 100 + "";
			  compare = amount.length - amount.indexOf(".");
			  amount = compare == 3?amount:compare == 2?amount+"0":amount+".00";
			  description = emp.description.length > 50? emp.description.slice(0,47) + "..." : emp.description;
			  newRow.innerHTML = `<td>${requestID}</td><td>$${amount}</td><td>${description}</td>
				  		<td><button class="btn btn-success" onclick=approve(this)>Approve</button>
				  			<button class="btn btn-danger" onclick=deny(this)>Deny</button></td><td>${empName}</td>`;
			  body.appendChild(newRow);
	      }
		  if(body.innerHTML === "")
			  body.innerHTML = "<tr>None available</tr>"
		});
	}
}

function populateManReviewed(self) {
	var body = self.childNodes[2].childNodes[3];
	
	if(body.innerHTML === ""){
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/employeeData/?directive=" + self.id,
		function(xhr){
		  let empList = JSON.parse(xhr.responseText).employees;
		  for (emp of empList){
			  empName = emp[1];
			  manName = emp[2];
			  emp = emp[0];
			  let newRow = document.createElement("tr");
			  requestID = emp.requestID;
			  amount = (emp.amount * 100 | 0) / 100 + "";
			  compare = amount.length - amount.indexOf(".");
			  amount = compare == 3?amount:compare == 2?amount+"0":amount+".00";
			  status = emp.status > 0? "Approved":emp.status < 0? "Denied":"Further Review";
			  description = emp.description.length > 50? emp.description.slice(0,47) + "..." : emp.description;
			  newRow.innerHTML = `<td>${requestID}</td><td>$${amount}</td><td>${status}</td>
				  		<td>${manName}</td><td>${description}</td><td>${empName}</td>`;
			  body.appendChild(newRow);
	      }
		  if(body.innerHTML === "")
			  body.innerHTML = "<tr>None available</tr>"
		});
	}
}

function enableEdit() {
	document.getElementById("cancel").removeAttribute("disabled")
	document.getElementById("save").removeAttribute("disabled")
	document.getElementById("edit").setAttribute("disabled", true)
}

function disableEdit() {
	document.getElementById("cancel").setAttribute("disabled", true)
	document.getElementById("save").setAttribute("disabled", true)
	document.getElementById("edit").removeAttribute("disabled")
}

function changePassword() {}

function sendAlert(msg){
	alert(msg);
}

function approve(self) {
	id = self.parentNode.parentNode.children[0].innerHTML;
	body = self.parentNode.parentNode.parentNode;
	detail = self.parentNode.parentNode.parentNode.parentNode.parentNode;
	detail.removeAttribute("open");
	
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/requestData/?id=" + id + 
				"&do=approve",
			function(xhr){
		  	  console.log(xhr.responseText);
			});
}

function deny(self){
	id = self.parentNode.parentNode.children[0].innerHTML;
	body = self.parentNode.parentNode.parentNode;
	detail = self.parentNode.parentNode.parentNode.parentNode.parentNode;
	detail.removeAttribute("open");
	sendAjaxGet("http://localhost:8080/P1-Charles-Crandall/requestData/?id=" + id +
				"&do=deny",
			function(xhr){
			  console.log(xhr.responseText);
			});
}

function returnLanding() {
	alert(document.getElementById("manager").outerHTML);
}